package net.apartium.servers.infernumpvp._1v1;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class Command1v1 implements CommandExecutor {
	private static InfernumPvP m = InfernumPvP.getInstance();
	private Map<String, Location> sels = new HashMap<>();
	private Map<String, Location> sels2 = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			PlayerData pd = new PlayerData(p);
			if (label.equalsIgnoreCase("ovo")) {
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("join")) {
						Player tp = Bukkit.getPlayer(args[1]);
						if (OvOGUI.requests.containsKey(tp.getUniqueId())
								&& !OvOGUI.ingame.containsKey(tp.getUniqueId())
								&& OvOGUI.requests.get(tp.getUniqueId()).equals(p.getUniqueId())) {
							tp.openInventory(DuelCustomizer.gen(tp, p));
							OvOGUI.requests.remove(tp.getUniqueId());
							OvOGUI.ingame.put(p.getUniqueId(), tp.getUniqueId());
							OvOGUI.ingame.put(tp.getUniqueId(), p.getUniqueId());
							p.sendMessage(m.OVO + ChatBuilder.build("The player is now customizing the 1V1"));
						}else{
							p.sendMessage(m.OVO + ChatBuilder.build("You are not invited to any game"));
						}
					}
					if (pd.hasPermission("OvO.Admin", false)) {
						if (args[0].equalsIgnoreCase("createarena")) {
							if (args.length != 2) {
								p.sendMessage(m.OVO + ChatBuilder.build("/ovo create <arena>"));
								return true;
							}
							String name = args[1];
							if (ArenasManager.getArenas().isExist(name)) {
								p.sendMessage(m.OVO + ChatBuilder.build("Name is taken."));
								return true;
							}
							if (!sels2.containsKey(p.getName())) {
								p.sendMessage(m.OVO + ChatBuilder.build("Please select positions!"));
								return true;
							}
							new Arena(name, sels.get(p.getName()), sels2.get(p.getName()));
							ArenasManager.getArenas().saveArenas();

							p.sendMessage(m.OVO + ChatBuilder.build("Arena created"));
						} else if (args[0].equalsIgnoreCase("delete")) {
							if (args.length != 2) {
								p.sendMessage(m.OVO + ChatBuilder.build("/ovo delete <arena>"));
								return true;
							}
							String name = args[1];
							if (!ArenasManager.getArenas().isExist(name)) {
								p.sendMessage(m.OVO + ChatBuilder.build("Arena not exist"));
								return true;
							}
							Arena arena = ArenasManager.getArenas().getArenaByName(name);

							ArenasManager.getArenas().delArena(arena);

							p.sendMessage(m.OVO + ChatBuilder.build("Arena deleted"));
							ArenasManager.getArenas().saveArenas();

						} else if (args[0].equalsIgnoreCase("setpos1")) {
							if (sels.containsKey(p.getName()))
								sels.replace(p.getName(), p.getLocation());
							else
								sels.put(p.getName(), p.getLocation());

							p.sendMessage(m.OVO + ChatBuilder.build("First pos set!"));

						} else if (args[0].equalsIgnoreCase("setpos2")) {
							if (!sels.containsKey(p.getName())) {
								p.sendMessage(m.OVO + ChatBuilder.build("You must set pos1 first!"));
								return false;
							}
							if (sels2.containsKey(p.getName()))
								sels2.replace(p.getName(), p.getLocation());
							else
								sels2.put(p.getName(), p.getLocation());
							p.sendMessage(m.OVO + ChatBuilder.build("Second pos set!"));

						} else if (args[0].equalsIgnoreCase("arenas")) {
							if (ArenasManager.getArenas().getList().isEmpty()) {
								p.sendMessage(m.OVO + ChatBuilder.build("There is no arenas!"));
								return true;
							}
							String arenas = "";
							for (Arena arena : ArenasManager.getArenas().getList()) {
								arenas = arenas + arena.getName() + ", ";
							}
							String newArenas = arenas.substring(0, arenas.length() - 2);

							p.sendMessage(m.OVO + ChatBuilder.build("Arenas:", newArenas));

						} else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
