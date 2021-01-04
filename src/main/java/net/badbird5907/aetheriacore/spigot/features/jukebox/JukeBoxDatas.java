package net.badbird5907.aetheriacore.spigot.features.jukebox;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Database;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class JukeBoxDatas {

    private static final String DB_TABLE = "`jukebox_players`";

    private Map<UUID, PlayerData> players = new HashMap<>();

    private Database db;
    private Database.JBStatement getStatement;
    private Database.JBStatement insertStatement;
    private Database.JBStatement updateStatement;
    private Database.JBStatement deleteStatement;

    public JukeBoxDatas(List<Map<?, ?>> mapList, Map<String, Song> tmpSongs) {
        for (Map<?, ?> m : mapList) {
            PlayerData pdata = PlayerData.deserialize((Map<String, Object>) m, tmpSongs);
            players.put(pdata.getID(), pdata);
        }
    }

    public JukeBoxDatas(Database db) throws SQLException {
        this.db = db;
        db.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " + DB_TABLE + "("
                + "`player_uuid` VARCHAR(32) NOT NULL,"
                + "`join` TINYINT(1) NOT NULL,"
                + "`shuffle` TINYINT(1) NOT NULL,"
                + "`particles` TINYINT(1) NOT NULL,"
                + "`repeat` TINYINT(1) NOT NULL,"
                + "`volume` SMALLINT(3) NOT NULL, "
                + "`favorites` VARCHAR(8000) NOT NULL, "
                + "PRIMARY KEY (`player_uuid`)"
                + ")");
        getStatement = db.new JBStatement("SELECT * FROM " + DB_TABLE + " WHERE `player_uuid` = ?");
        insertStatement = db.new JBStatement("INSERT INTO " + DB_TABLE + " (`join`, `shuffle`, `particles`, `repeat`, `volume`, `favorites`, `player_uuid`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        updateStatement = db.new JBStatement("UPDATE " + DB_TABLE + " SET `join` = ?, `shuffle`= ?, `particles` = ?, `repeat` = ?, `volume` = ?, `favorites` = ? WHERE `player_uuid` = ?");
        deleteStatement = db.new JBStatement("DELETE FROM " + DB_TABLE + " WHERE `player_uuid` = ?");
    }

    public PlayerData getDatas(UUID uuid) {
        return players.get(uuid);
    }

    public PlayerData getDatas(Player p) {
        return players.get(p.getUniqueId());
    }

    public Collection<PlayerData> getDatas() {
        return players.values();
    }

    public Object getSerializedList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PlayerData pdata : players.values()) {
            if (pdata.songPlayer != null) pdata.stopPlaying(true);
            if (!pdata.isDefault(Noteblock.defaultPlayer)) list.add(pdata.serialize());
        }
        return list;
    }

    public void joins(Player p) {
        UUID id = p.getUniqueId();
        if (db == null) {
            PlayerData pdata = players.get(id);
            if (pdata == null) {
                pdata = PlayerData.create(id);
                players.put(id, pdata);
            }
            pdata.playerJoin(p, !Noteblock.worlds || Noteblock.worldsEnabled.contains(p.getWorld().getName()));
        }else {
            PlayerData pdata = PlayerData.create(id);
            players.put(id, pdata);
            Bukkit.getScheduler().runTaskAsynchronously(AetheriaCore.getInstance(), () -> {
                synchronized (getStatement) {
                    try {
                        PreparedStatement statement = getStatement.getStatement();
                        statement.setString(1, id.toString().replace("-", ""));
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            pdata.created = false;
                            pdata.setJoinMusic(resultSet.getBoolean("join"));
                            pdata.setShuffle(resultSet.getBoolean("shuffle"));
                            pdata.setParticles(resultSet.getBoolean("particles"));
                            pdata.setRepeat(resultSet.getBoolean("repeat"));
                            pdata.setVolume(resultSet.getInt("volume"));
                            String favorites = resultSet.getString("favorites");
                            if (!favorites.isEmpty()) pdata.setFavorites(Arrays.stream(favorites.split("\\|")).map(Noteblock::getSongByInternalName).toArray(Song[]::new));
                        }
                        resultSet.close();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    pdata.playerJoin(p, !Noteblock.worlds || Noteblock.worldsEnabled.contains(p.getWorld().getName()));
                }
            });
        }
    }

    public void quits(Player p) {
        UUID id = p.getUniqueId();
        PlayerData pdata = players.get(id);
        if (pdata != null) {
            pdata.playerLeave();
            if (db == null) {
                if (!Noteblock.savePlayerDatas) players.remove(id);
            }else {
                boolean isDefault = pdata.isDefault(Noteblock.defaultPlayer);
                if (!pdata.created || !isDefault) {
                    Bukkit.getScheduler().runTaskAsynchronously(AetheriaCore.getInstance(), () -> {
                        if (isDefault) {
                            synchronized (deleteStatement) {
                                try {
                                    PreparedStatement statement = deleteStatement.getStatement();
                                    statement.setString(1, id.toString().replace("-", ""));
                                    statement.executeUpdate();
                                }catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {
                            synchronized (updateStatement) {
                                try {
                                    int i = 1;
                                    PreparedStatement statement = pdata.created ? insertStatement.getStatement() : updateStatement.getStatement();
                                    statement.setBoolean(i++, pdata.hasJoinMusic());
                                    statement.setBoolean(i++, pdata.isShuffle());
                                    statement.setBoolean(i++, pdata.hasParticles());
                                    statement.setBoolean(i++, pdata.isRepeatEnabled());
                                    statement.setInt(i++, pdata.getVolume());
                                    statement.setString(i++, pdata.getFavorites() == null ? "" : pdata.getFavorites().getSongList().stream().map(Noteblock::getInternal).collect(Collectors.joining("|")));
                                    statement.setString(i++, id.toString().replace("-", ""));
                                    statement.executeUpdate();
                                }catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

}
