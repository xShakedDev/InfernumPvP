package net.apartium.servers.infernumpvp.commands;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import mkremins.fanciful.FancyMessage;
import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;

public class MultiWorld_Command implements CommandExecutor {
	private static InfernumPvP plugin = InfernumPvP.getInstance();

	private FileConfiguration fc;

	public MultiWorld_Command() {
		fc = YamlConfiguration.loadConfiguration(plugin.multiWorld);
	}

	public static HashMap<String, World> loaded = new HashMap<String, World>();

	public static void loadWorlds(File MultiWorld) {
		FileConfiguration MultiWorldC = YamlConfiguration.loadConfiguration(MultiWorld);
		for (String s : MultiWorldC.getStringList("worlds")) {
			if (MultiWorldC.getBoolean(s + ".enabled") && new File(Bukkit.getWorldContainer(), s).isDirectory()) {
				Bukkit.getServer().createWorld(new WorldCreator(s));
				System.out.println("The world " + s + " loaded successfully");

			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PlayerData pd = new PlayerData(p);

		if (label.equalsIgnoreCase("mw") && pd.hasPermission("cubedpvp.multiworld", true)) {
			if (args.length == 0) {
				p.sendMessage(plugin.USAGE + "§4Usage: /mw *[Action] *[World] -[Args]");
			} else if (args.length == 1) {
				if (args[0].equals("list")) {

				}
			} else if (args.length == 3) {
				if (args[0].equals("do")) {
					WorldCreator dwc = new WorldCreator(args[1]);
					dwc.environment(Environment.NORMAL);
					if (WorldType.getByName(args[2]) != null) {
						dwc.type(WorldType.getByName(args[2]));
						World w = Bukkit.getServer().createWorld(dwc);
						if (!fc.contains(args[1])) {
							List<String> worlds = fc.getStringList("worlds");
							worlds.add(args[1]);
							fc.set("worlds", worlds);
							saveConfig();
							fc.set(args[1] + ".enabled", true);
							saveConfig();
							p.teleport(new Location(w, 0, 50, 0));
						}
						p.sendMessage(plugin.USAGE + "§3You created/loaded a new world named " + args[1]);
						loaded.put(w.getName(), w);
						System.out.println("The world " + args[1] + " loaded successfully");
					} else {
						p.sendMessage(plugin.USAGE + "§cUnknown World Type! (FLAT,NORMAL,LARGE_BIOMES,AMPLIFIED)");
					}
				}
			} else if (args.length == 2) {
				if (args[0].equals("tp")) {
					World w = Bukkit.getWorld(args[1]);
					if (w != null && loaded.containsKey(w.getName())) {
						p.teleport(new Location(w, 0, 50, 0));
					} else {
						p.sendMessage(plugin.USAGE + "§cThere is no world called " + args[1] + "");
					}
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("unload")) {
					loaded.remove(args[1]);
					World w = Bukkit.getWorld(args[1]);
					if (w != null) {
						Bukkit.unloadWorld(w, true);
						for (Player ps : Bukkit.getOnlinePlayers()) {
							PlayerData pda = new PlayerData(ps);
							pda.spawn();
						}
						p.sendMessage(plugin.USAGE + "§3You unloaded " + args[1]);
					}
				}
			} else if (args.length == 3) {

				if (args[0].equals("delete")) {
					loaded.remove(args[1]);
					World w = Bukkit.getWorld(args[1]);
					Bukkit.unloadWorld(w, true);
					if (w != null) {

						World delete = Bukkit.getWorld(args[1]);
						File deleteFolder = delete.getWorldFolder();
						deleteFolder.delete();
						for (Player ps : Bukkit.getOnlinePlayers()) {
							PlayerData pda = new PlayerData(ps);
							pda.spawn();
						}
						p.sendMessage(plugin.USAGE + "§3You unloaded and deleted " + args[1]);
					}
				} else if (args[0].equals("copy")) {
					new FancyMessage("§4§lYou sure you want to copy " + args[0] + " to " + args[1] + "?")
							.color(ChatColor.GREEN).then("    [Accept]").command("confirmcopy").then("    [Deny]")
							.color(ChatColor.GREEN);
					World cto = Bukkit.getWorld(args[1]);

					WorldCreator cfc = new WorldCreator(args[0]);
					cfc.copy(cto);
				}
			}
		}
		return false;
	}

	void saveConfig() {
		try {

			fc.save(plugin.multiWorld);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
