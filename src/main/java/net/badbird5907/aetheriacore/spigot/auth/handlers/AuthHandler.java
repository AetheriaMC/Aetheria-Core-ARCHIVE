package net.badbird5907.aetheriacore.spigot.auth.handlers;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.spigot.auth.events.PlayerStateChangeEvent;
import net.badbird5907.aetheriacore.spigot.auth.utils.ImageRender;
import net.badbird5907.aetheriacore.shared.storage.StorageType;
import net.badbird5907.aetheriacore.shared.storage.json.JSONStorage;
import net.badbird5907.aetheriacore.shared.storage.mongodb.MongoDBStorage;
import net.badbird5907.aetheriacore.shared.storage.mysql.MySQLStorage;
import net.badbird5907.aetheriacore.shared.utils.hash.Hash;
import net.badbird5907.aetheriacore.shared.utils.hash.NoHash;
import net.badbird5907.aetheriacore.shared.utils.hash.SHA256;
import net.badbird5907.aetheriacore.shared.utils.hash.SHA512;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.SpigotConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AuthHandler extends net.badbird5907.aetheriacore.shared.handlers.AuthHandler {

    /**
     * This class was originally written by Connor Linfoot (https://github.com/ConnorLinfoot/MC2FA).
     * This class was edited by Liel Amar to add Bungeecord, JSON, MySQL, and MongoDB support.
     */

    protected final AetheriaCore main;
    protected final AutoAuthHandler autoAuthHandler;
    protected Hash hash;

    public AuthHandler(AetheriaCore main) {
        this.main = main;
        this.autoAuthHandler = new AutoAuthHandler();

        loadHashType();
        loadStorageHandler();
    }

    @Override
    public String createKey(UUID uuid) {
        String key = super.createKey(uuid);

        // If the player is online it gives them a QR code
        Player player = Bukkit.getPlayer(uuid);
        if(player != null && player.isOnline())
            giveQRCodeItem(player);

        return key;
    }

    @Override
    public boolean validateKey(UUID uuid, Integer code) {
        boolean valid = super.validateKey(uuid, code);

        Player player = Bukkit.getPlayer(uuid);
        if(player != null && player.isOnline()) {
            if(valid) {
                removeQRItem(player);
                updatePlayerIP(player);
            }
        }
        return valid;
    }

    @Override
    public boolean approveKey(UUID uuid, Integer code) {
        boolean approved = super.approveKey(uuid, code);

        if(approved) {
            Player player = Bukkit.getPlayer(uuid);
            if(player != null) {
                removeQRItem(player);
                updatePlayerIP(player);
            }
        }

        return approved;
    }

    @Override
    public void playerJoin(UUID uuid) {
        super.playerJoin(uuid);

        Player player = Bukkit.getPlayer(uuid);
        if(player == null || !player.isOnline()) {
            return;
        }

        // If the player doesn't have permission to use 2fa
        if(!player.hasPermission("2fa.use")) {
            changeState(uuid, AuthState.DISABLED);
            return;
        }

        // If the player has a key, wait for an authentication, otherwise, set them as 2fa disabled
        if(getStorageHandler().getKey(uuid) != null) {
            changeState(uuid, AuthState.PENDING_LOGIN);
        } else {
            changeState(uuid, AuthState.DISABLED);
        }

        // If the player doesn't need to authenticate (they don't have a key)
        if(!needsToAuthenticate(player.getUniqueId())) {
            if(player.hasPermission("2fa.demand")) {
                TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&6You are required to enable 2FA!");
                createKey(player.getUniqueId());
            } else {
                if(TwoFactorAuthentication.getInstance().getConfigHandler().is2FAAdvised()) {
                    TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&6This server supports two-factor authentication and is highly recommended");
                    TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&6Get started by running \"/2fa enable\"");
                }
            }
        } else {
            // try to auto authenticate the player
            autoAuthHandler.autoAuthenticate(player);
        }
    }

    @Override
    public void changeState(UUID uuid, AuthState authState) {
        if(authState == getAuthState(uuid))
            return;

        Player player = Bukkit.getPlayer(uuid);
        if(player != null) {
            PlayerStateChangeEvent event = new PlayerStateChangeEvent(player, authState);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;

            authState = event.getAuthState();
        }

        authStates.put(uuid, authState);
        if(player != null)
            autoAuthHandler.setBungeecordAuthState(player, authState);
    }

    @Override
    public String getQRCodeURL(String urlTemplate, UUID uuid) {
        String encodedPart = "%%label%%?secret=%%key%%&issuer=%%title%%";

        Player player = Bukkit.getPlayer(uuid);
        String label = (player == null) ? "player" : player.getName();
        String title = TwoFactorAuthentication.getInstance().getConfigHandler().getServerName();
        String key = getPendingKey(uuid);
        encodedPart = encodedPart.replaceAll("%%label%%", label).replaceAll("%%title%%", title).replaceAll("%%key%%", key);

        try {
            return urlTemplate + URLEncoder.encode(encodedPart, StandardCharsets.UTF_8.toString());
        } catch(UnsupportedEncodingException e) {
            return urlTemplate + encodedPart;
        }
    }


    /**
     * Loads the hash type (SHA-256, SHA-512, No Hash)
     */
    private void loadHashType() {
        //String hashType = TwoFactorAuthentication.getInstance().getConfigHandler().getHashType();
        String hashType = AetheriaCore.getInstance().getConfig().getString("IP Hash");

        switch (hashType.toUpperCase()) {
            case "SHA256":
                this.hash = new SHA256();
                break;
            case "SHA512":
                this.hash = new SHA512();
                break;
            default:
                this.hash = new NoHash();
                break;
        }
    }

    /**
     * Loads the storage handler (JSON, MySQL, MongoDB)
     */
    private void loadStorageHandler() {
        //StorageType storageType = TwoFactorAuthentication.getInstance().getConfigHandler().getStorageType();
        StorageType storageType = StorageType.valueOf(AetheriaCore.getInstance().getConfig().getString("Storage Type", "JSON").toUpperCase());

        if(storageType == StorageType.MYSQL) {
            ConfigurationSection mysql = main.getConfig().getConfigurationSection("MySQL");

            if(mysql != null) {
                String host = mysql.getString("credentials.host");
                String database = mysql.getString("credentials.database");
                String username = mysql.getString("credentials.auth.username");
                String password = mysql.getString("credentials.auth.password");
                int port = mysql.getInt("credentials.port");

                this.storageHandler = new MySQLStorage(host, database, username, password, port);
            }
        } else if(storageType == StorageType.MONGODB) {
            ConfigurationSection mongodb = main.getConfig().getConfigurationSection("MongoDB");

            if(mongodb != null) {
                String uri = mongodb.getString("credentials.uri");
                String host = mongodb.getString("credentials.host");
                String database = mongodb.getString("credentials.database");
                String username = mongodb.getString("credentials.auth.username");
                String password = mongodb.getString("credentials.auth.password");
                int port = mongodb.getInt("credentials.port");
                boolean requiredAuth = mongodb.getBoolean("credentials.auth.required");

                this.storageHandler = new MongoDBStorage(uri, host, database, username, password, port, requiredAuth);
            }
        }

        if(this.storageHandler == null) {
            this.storageHandler = new JSONStorage(main.getDataFolder().getAbsolutePath());
        }
    }


    /**
     * Updates a player's ip in the database
     *
     * @param player   Player to update the ip of
     */
    public void updatePlayerIP(Player player) {
        if(player.getAddress() != null && player.getAddress().getAddress() != null) {
            String ip = this.hash.hash(player.getAddress().getAddress().getHostAddress());
            getStorageHandler().setIP(player.getUniqueId(), ip);
        }
    }

    /**
     * Sends a clickable message to a player
     *
     * @param player    Player to send message to
     * @param message   Message to send
     * @param url       URL to open on click
     */
    public void sendClickableMessage(Player player, String message, String url) {
        TextComponent component = new TextComponent(message);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        player.spigot().sendMessage(component);
    }

    /**
     * Gives a player a Map item with the QR code if their code
     *
     * @param player   Player to give item to
     */
    @SuppressWarnings("deprecation")
    public void giveQRCodeItem(Player player) {
        String url = getQRCodeURL(TwoFactorAuthentication.getInstance().getConfigHandler().getQrCodeURL(), player.getUniqueId());

        if(url == null)
            return;

        new BukkitRunnable() {
            final MapView view = Bukkit.createMap(player.getWorld());

            @Override
            public void run() {
                view.getRenderers().forEach(view::removeRenderer);
                try {
                    ImageRender renderer = new ImageRender(url);
                    view.addRenderer(renderer);

                    String versionRaw = Bukkit.getVersion().split("MC: 1.")[1];
                    if(versionRaw.contains(".")) versionRaw = versionRaw.split("\\.")[0];
                    if(versionRaw.contains(")")) versionRaw = versionRaw.split("\\)")[0];
                    int version = Integer.parseInt(versionRaw);

                    ItemStack mapItem = new ItemStack(Material.MAP);
                    if(version >= 13) {
                        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
                        if(mapMeta != null) {
                            mapMeta.setMapId(view.getId());
                            mapItem.setItemMeta(mapMeta);
                        }
                    } else {
                        mapItem = new ItemStack(Material.MAP, 1, getMapID(view));
                    }

                    ItemMeta meta = mapItem.getItemMeta();
                    if(meta != null) {
                        meta.setDisplayName(ChatColor.GRAY + "QR Code");
                        mapItem.setItemMeta(meta);
                    }

                    // If inventory is not full
                    if(player.getInventory().firstEmpty() != -1) {
                        ItemStack oldItem = null;

                        if(player.getInventory().firstEmpty() != 0)
                            oldItem = player.getInventory().getItem(0);
                        player.getInventory().setItem(0, mapItem);

                        if(oldItem != null)
                            player.getInventory().addItem(oldItem);
                        player.getInventory().setHeldItemSlot(0);
                    }

                    sendClickableMessage(player, TwoFactorAuthentication.getInstance().getMessageHandler().getMessage("&aPlease click here to open the QR code"), url.replaceAll("128x128", "256x256"));
                    TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&aPlease use the QR code given to setup two-factor authentication");
                    TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&aPlease validate by entering your code: /2fa <code>");
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                    player.sendMessage(ChatColor.RED + "An error occurred! Is the URL correct?");
                }
            }
        }.runTaskAsynchronously(main);
    }

    /**
     * Removes all QR Code items from the player's inventory
     *
     * @param player   Player to remove QR Code items of
     */
    public void removeQRItem(Player player) {
        player.getInventory().forEach(itemStack -> {
            if(isQRCodeItem(itemStack))
                player.getInventory().remove(itemStack);
        });
    }

    /**
     * Checks if the provided item is a QR item
     *
     * @param item   Item to check
     * @return       Whether it's a QR item
     */
    public boolean isQRCodeItem(ItemStack item) {
        return item != null && item.getType() != Material.AIR && item.hasItemMeta() && item.getItemMeta() != null && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "QR Code");
    }

    /**
     * Returns the ID of a MapView
     *
     * @param view   MapView to get the ID of
     * @return       ID of view
     */
    public static short getMapID(MapView view) {
        try {
            return (short) view.getId();
        } catch (NoSuchMethodError e) {
            try {
                Class<?> MapView = Class.forName("org.bukkit.map.MapView");
                Object mapID = MapView.getMethod("getId").invoke(view);
                return (short) mapID;
            } catch (Exception e1) {
                return 1;
            }
        }
    }


    /**
     * Inner class to handle BungeeCord communication
     */
    public class AutoAuthHandler {

        private final boolean isBungeecord;

        public AutoAuthHandler() {
            this.isBungeecord = SpigotConfig.bungee && !Bukkit.getServer().getOnlineMode();
        }

        /**
         * Handles BungeeCord authentication
         *
         * @param player   Player to update BungeeCord authentication
         * @param state    State to set the player's BungeeCord authentication to
         */
        public void setBungeecordAuthState(Player player, AuthState state) {
            if(isBungeecord) {
                TwoFactorAuthentication.getInstance().getPluginMessageListener().setBungeeCordAuthState(player.getUniqueId(), state);
            }
        }

        public void autoAuthenticate(Player player) {
            if(isBungeecord) {
                bungeecordAutoAuthenticate(player);
            } else {
                spigotAutoAuthenticate(player);
            }
        }

        private void bungeecordAutoAuthenticate(Player player) {
            if(!needsToAuthenticate(player.getUniqueId())) return;

            Bukkit.getScheduler().runTaskLater(main, () -> TwoFactorAuthentication.getInstance().getPluginMessageListener().getBungeeCordAUthState(player.getUniqueId()), 1L);
            Bukkit.getScheduler().runTaskLater(main, () -> spigotAutoAuthenticate(player), 2L);
        }

        private void spigotAutoAuthenticate(Player player) {
            if(!needsToAuthenticate(player.getUniqueId())) return;

            if(player.getAddress() != null && player.getAddress().getAddress() != null) {
                String ip = hash.hash(player.getAddress().getAddress().getHostAddress());

                boolean hasIPChanged = getStorageHandler().getIP(player.getUniqueId()) == null
                        || !getStorageHandler().getIP(player.getUniqueId()).equalsIgnoreCase(ip);

                boolean isRequiredDueToIPChange = TwoFactorAuthentication.getInstance().getConfigHandler().isRequiredOnIPChange() && hasIPChanged;
                boolean isRequiredOnEveryJoin = TwoFactorAuthentication.getInstance().getConfigHandler().isRequiredOnEveryLogin();

                if(!isRequiredDueToIPChange && !isRequiredOnEveryJoin) {
                    changeState(player.getUniqueId(), AuthState.AUTHENTICATED);
                    TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&aYou were authenticated automatically");
                    return;
                }
            }

            player.setWalkSpeed(0);
            player.setFlySpeed(0);

            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&cTwo-factor authentication is enabled on this account");
            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&cPlease authenticate using /2fa <code>");
        }
    }
    @Override
    public boolean needsToAuthenticate(UUID uuid) {
        Boolean a = is2FAEnabled(uuid) && !authStates.get(uuid).equals(AuthState.AUTHENTICATED);
        DebugLogger.DebugLog(a.toString());
        return a;
    }
}