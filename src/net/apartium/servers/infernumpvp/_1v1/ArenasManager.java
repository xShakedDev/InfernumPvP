package net.apartium.servers.infernumpvp._1v1;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;

public class ArenasManager {

	private static ArenasManager a;

	static {
		a = new ArenasManager();
	}

	public static ArenasManager getArenas() {
		return a;
	}

	InfernumPvP main = InfernumPvP.getInstance();
	public static ArrayList<Arena> arenas = new ArrayList<Arena>();

	public void addArena(Arena arena) {
		if (!arenas.contains(arena)) {
			arenas.add(arena);
		}
	}

	public void delArena(Arena arena) {
		if (arenas.contains(arena)) {
			arenas.remove(arena);
		}
	}

	public boolean isExist(String name) {
		if ((arenas == null) || (arenas.isEmpty())) {
			return false;
		}
		for (Arena arena : arenas) {
			if (arena.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Arena> getList() {
		return arenas;
	}

	public boolean isReady(Arena arena) {
		if ((arena.getPos1() == null) || (arena.getPos2() == null) || (arena.getPos1() == arena.getPos2())) {
			return false;
		}
		return true;
	}

	public Arena getArenaByName(String name) {
		for (Arena arena : arenas) {
			if (arena.getName().equalsIgnoreCase(name)) {
				return arena;
			}
		}
		return null;
	}

	public Arena getArenaByPlayer(Player p) {
		for (Arena arena : arenas) {
			if (arena.getPlayerinarena() == p || arena.getOpponentinarena() == p) {
				return arena;
			}
		}
		return null;
	}

	public Arena getArenaByTempPlayer(Player p) {
		for (Arena arena : arenas) {
			if (arena.getTempplayer() == p) {
				return arena;
			}
		}
		return null;
	}

	public Arena randomArena() {
		for (Arena a : arenas) {
			if (!a.isInuse()) {
				return a;
			}
		}
		return null;
	}

	public void saveArenas() {
		for (String key : main.ac.getKeys(true)) {
			main.ac.set(key, null);
		}
		for (Arena arena : arenas) {

			main.ac.set(arena.getName() + ".pos1.world", arena.getPos1().getWorld().getName());
			main.ac.set(arena.getName() + ".pos1.x", Double.valueOf(arena.getPos1().getX()));
			main.ac.set(arena.getName() + ".pos1.y", Double.valueOf(arena.getPos1().getY()));
			main.ac.set(arena.getName() + ".pos1.z", Double.valueOf(arena.getPos1().getZ()));
			main.ac.set(arena.getName() + ".pos1.pitch", Float.valueOf(arena.getPos1().getPitch()));
			main.ac.set(arena.getName() + ".pos1.yaw", Float.valueOf(arena.getPos1().getYaw()));

			main.ac.set(arena.getName() + ".pos2.world", arena.getPos2().getWorld().getName());
			main.ac.set(arena.getName() + ".pos2.x", Double.valueOf(arena.getPos2().getX()));
			main.ac.set(arena.getName() + ".pos2.y", Double.valueOf(arena.getPos2().getY()));
			main.ac.set(arena.getName() + ".pos2.z", Double.valueOf(arena.getPos2().getZ()));
			main.ac.set(arena.getName() + ".pos2.pitch", Float.valueOf(arena.getPos2().getPitch()));
			main.ac.set(arena.getName() + ".pos2.yaw", Float.valueOf(arena.getPos2().getYaw()));
		}
		try {
			this.main.ac.save(main.a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadArenas() {
		for (String key : main.ac.getKeys(false)) {
			Location pos1 = null;
			Location pos2 = null;

			String name = key;

			double x1 = main.ac.getDouble(key + ".pos1.x");
			double y1 = main.ac.getDouble(key + ".pos1.y");
			double z1 = main.ac.getDouble(key + ".pos1.z");
			float pitch1 = Float.parseFloat(main.ac.getString(key + ".pos1.pitch"));
			float yaw1 = Float.parseFloat(main.ac.getString(key + ".pos1.yaw"));

			pos1 = new Location(Bukkit.getWorld(main.ac.getString(key + ".pos1.world")), x1, y1, z1, yaw1, pitch1);

			double x2 = main.ac.getDouble(key + ".pos2.x");
			double y2 = main.ac.getDouble(key + ".pos2.y");
			double z2 = main.ac.getDouble(key + ".pos2.z");
			float pitch2 = Float.parseFloat(main.ac.getString(key + ".pos2.pitch"));
			float yaw2 = Float.parseFloat(main.ac.getString(key + ".pos2.yaw"));

			pos2 = new Location(Bukkit.getWorld(main.ac.getString(key + ".pos2.world")), x2, y2, z2, yaw2, pitch2);

			@SuppressWarnings("unused")
			Arena arena = new Arena(name, pos1, pos2);
		}
	}
}
