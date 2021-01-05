package net.badbird5907.aetheriacore.spigot.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static org.bukkit.Bukkit.*;

public class NPC {
	private static final List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();

	public static void createNPC(Player player, String name) {
		MinecraftServer server = ((CraftServer) getServer()).getServer();
		WorldServer world = ((CraftWorld) requireNonNull(getWorld(player.getWorld().getName()))).getHandle();
		GameProfile gameProfile = new GameProfile(randomUUID(), name);
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		npc.setLocation(player.getLocation().getX(), player.getLocation().getBlockY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
		addNPCPacket(npc);
		NPC.add(npc);
	}

	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}

	public static void addJoinPacket(Player player) {
		for (EntityPlayer npc : NPC) {
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}

	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}
}