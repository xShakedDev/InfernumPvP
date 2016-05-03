package net.apartium.servers.infernumpvp.regions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.IOUtil;

public class Region {
	private static InfernumPvP plugin=InfernumPvP.getInstance();
	private static Map<String,Region> regions = new HashMap<>();
	
	public static Region byName(String name) {
		return regions.get(name);
	}
	public static Region createNew(String name) {
		Region ret = new Region(name);
		regions.put(ret.getName(), ret);
		return ret;
	}

	private File f;
	private FileConfiguration fc;
	private String name;
	
	private Region(String name) {
		this.name = name;
		if(plugin.rgFiles.containsKey(name))
			this.f=plugin.rgFiles.get(name);
		else
			this.f=IOUtil.handleYML(plugin.rgFolder, name+".yml").getKey();
		
		this.fc=YamlConfiguration.loadConfiguration(f);
	}

	public enum Flags {

		BUILD, DESTROY, BLOCK_CMD, JOINCOMBAT, PVP, ENTRY, ALLOW_SOUPDROP, ALLOW_SOUPSSIGN, ITEMDROP, ITEMPICKUP, DAMAGE, MOB_SPAWNING, EXPLODE, HUNGER

	}

	public enum Info {
		CREATOR, OWNER
	}

	public enum Flag_String {
		ENTER_MSG, LEAVE_MSG
	}

	public enum Flag_List {
		COMMAND_ONENTER, COMMAND_ONLEAVE, BLOCKED_CMD

	}

	public boolean getFlag(Flags flag) {
		File f = plugin.rgFiles.get(name);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);

		if (flag == Flags.BUILD && fc.getBoolean("flags.build")) {
			return true;
		} else if (flag == Flags.DESTROY && fc.getBoolean("flags.destroy")) {
			return true;
		} else if (flag == Flags.BLOCK_CMD && fc.getBoolean("flags.disallowcmd")) {
			return true;
		} else if (flag == Flags.JOINCOMBAT && fc.getBoolean("flags.joincombatarray")) {
			return true;
		} else if (flag == Flags.PVP && fc.getBoolean("flags.pvp")) {
			return true;
		} else if (flag == Flags.ENTRY && fc.getBoolean("flags.entry")) {
			return true;
		} else if (flag == Flags.ALLOW_SOUPDROP && fc.getBoolean("flags.soupdrop")) {
			return true;
		} else if (flag == Flags.ALLOW_SOUPSSIGN && fc.getBoolean("flags.soupsign")) {
			return true;
		} else if (flag == Flags.ITEMDROP && fc.getBoolean("flags.itemdrop")) {
			return true;
		} else if (flag == Flags.ITEMPICKUP && fc.getBoolean("flags.itempickup")) {
			return true;
		} else if (flag == Flags.DAMAGE && fc.getBoolean("flags.damage")) {
			return true;
		} else if (flag == Flags.MOB_SPAWNING && fc.getBoolean("flags.mobspawning")) {
			return true;
		} else if (flag == Flags.EXPLODE && fc.getBoolean("flags.explode")) {
			return true;
		} else if (flag == Flags.HUNGER && fc.getBoolean("flags.hunger")) {
			return true;
		} else {
			return false;
		}
	}
	public void setupGlobal() {
		FileConfiguration c = YamlConfiguration.loadConfiguration(plugin.rgGlobal);
		c.set("name", name);
		c.set("creator", "plugin");

		c.set("flags.build", false);
		c.set("flags.destroy", false);
		c.set("flags.disallowcmd", false);
		c.set("flags.joincombatarray", false);
		c.set("flags.pvp", false);
		c.set("flags.enter", false);
		c.set("flags.soupdrop", false);
		c.set("flags.soupsign", false);
		c.set("flags.itemdrop", false);
		c.set("flags.itempickup", false);
		c.set("flags.damage", false);
		c.set("flags.mobspawning", false);
		c.set("flags.exploision", false);
		c.set("flags.hunger", false);
		
		try {
			c.save(plugin.rgGlobal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getFlagString(Flag_String fs) {
		File f = plugin.rgFiles.get(name);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);

		if (fs == Flag_String.ENTER_MSG) {
			return fc.getString("values.entermsg");
		} else if (fs == Flag_String.LEAVE_MSG) {
			return fc.getString("values.leavemsg");
		} else {
			return null;
		}
	}

	public List<String> getFlagsList(Flag_List fl) {
		File f = plugin.rgFiles.get(name);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);

		if (fl == Flag_List.COMMAND_ONENTER) {
			return fc.getStringList("values.cmdonenter");
		} else if (fl == Flag_List.COMMAND_ONLEAVE) {
			return fc.getStringList("values.cmdonleave");

		} else if (fl == Flag_List.BLOCKED_CMD) {
			return fc.getStringList("values.blockcmd");

		} else {
			return null;
		}

	}

	public void setFlagsList(Flag_List fl, List<String> value) {
		File f = plugin.rgFiles.get(name);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);

		if (fl == Flag_List.COMMAND_ONENTER) {
			fc.set("values.cmdonenter", value);

		} else if (fl == Flag_List.COMMAND_ONLEAVE) {
			fc.set("values.cmdonleave", value);

		} else if (fl == Flag_List.BLOCKED_CMD) {
			fc.set("values.blockcmd", value);

		}
	}

	public void debug(String s) {
		Bukkit.broadcastMessage(s);
	}

	@SuppressWarnings("unused")
	public void remove(Player p) {
		if (plugin.rgFiles.containsKey(name)) {
			File f = plugin.rgFiles.get(name);
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			plugin.rgFiles.remove(name);
			f.delete();
			p.sendMessage(plugin.REGIONS + "§aRemoved region called: " + name);
		} else {
			p.sendMessage(plugin.REGIONS + "§aThere is no region called: " + name);
		}
	}

	public void create(Player p, World w, double fromx, double tox, double fromz, double toz) {
		if (!plugin.rgFiles.containsKey(name)) {
			File f = new File(plugin.getDataFolder() + "/regions/" + name + ".yml");
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			plugin.rgFiles.put(name, f);
			fc.set("name", name);
			fc.set("creator", p.getName());
			fc.set("location.fromX", fromx);
			fc.set("location.toX", tox);
			fc.set("location.fromZ", fromz);
			fc.set("location.toZ", toz);
			fc.set("location.world", w.getName());

			fc.set("flags.build", false);
			fc.set("flags.destroy", false);
			fc.set("flags.disallowcmd", false);
			fc.set("flags.joincombatarray", false);
			fc.set("flags.pvp", false);
			fc.set("flags.enter", false);
			fc.set("flags.soupdrop", false);
			fc.set("flags.soupsign", false);
			fc.set("flags.itemdrop", false);
			fc.set("flags.itempickup", false);
			fc.set("flags.damage", false);
			fc.set("flags.mobspawning", false);
			fc.set("flags.exploision", false);
			fc.set("flags.hunger", false);

			try {
				fc.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(plugin.REGIONS + "§aCreated region called: " + name + "§l ALL Flags Set To False");
		} else {
			p.sendMessage(plugin.REGIONS + "§aThere is already region called: " + name);
		}
	}

	public boolean Exists() {
		if (!plugin.rgFiles.containsKey(name)) {
			return false;
		} else {
			return true;
		}
	}

	public void setFlag(Flags flag, boolean value) {

		if (flag == Flags.BUILD) {
			fc.set("flags.build", value);
		} else if (flag == Flags.DESTROY) {
			fc.set("flags.destroy", value);
		} else if (flag == Flags.JOINCOMBAT) {
			fc.set("flags.joincombatarray", value);
		} else if (flag == Flags.PVP) {
			fc.set("flags.pvp", value);
		} else if (flag == Flags.ENTRY) {
			fc.set("flags.entry", value);
		} else if (flag == Flags.ALLOW_SOUPDROP) {
			fc.set("flags.soupdrop", value);
		} else if (flag == Flags.ALLOW_SOUPSSIGN) {
			fc.set("flags.soupsign", value);
		} else if (flag == Flags.BLOCK_CMD) {
			fc.set("flags.disallowcmd", value);
		} else if (flag == Flags.ITEMDROP) {
			fc.set("flags.itemdrop", value);
		} else if (flag == Flags.ITEMPICKUP) {
			fc.set("flags.itempickup", value);
		} else if (flag == Flags.DAMAGE) {
			fc.set("flags.damage", value);
		} else if (flag == Flags.MOB_SPAWNING) {
			fc.set("flags.mobspawning", value);
		} else if (flag == Flags.EXPLODE) {
			fc.set("flags.explode", value);
		} else if (flag == Flags.HUNGER) {
			fc.set("flags.hunger", value);
		}
		try {
			fc.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFlagString(Flag_String fs, String value) {
		File f = plugin.rgFiles.get(name);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);

		if (fs == Flag_String.ENTER_MSG) {
			fc.set("values.entermsg", value);
		} else if (fs == Flag_String.LEAVE_MSG) {
			fc.set("values.leavemsg", value);

		}
	}

	public String getName() {
		return name;
	}

}