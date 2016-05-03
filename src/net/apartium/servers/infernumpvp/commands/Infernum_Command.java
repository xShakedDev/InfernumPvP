package net.apartium.servers.infernumpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.gui.KitGUI;
import net.apartium.servers.infernumpvp.gui.ShopGUI;
import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class Infernum_Command implements CommandExecutor {
	private static InfernumPvP m = InfernumPvP.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			PlayerData pd = new PlayerData(p);
			if (label.equalsIgnoreCase("infernum")) {
				if (args.length == 0) {
					p.sendMessage(m.USAGE + ChatBuilder.build("/infernum kit <name> <player>"));
					p.sendMessage(m.USAGE + ChatBuilder.build("/infernum shop <name> <player>"));
					if (p.isOp()) {
						p.sendMessage(m.USAGE + ChatBuilder.build("/infernum gm <name> <player>"));
						p.sendMessage(m.USAGE + ChatBuilder.build("/infernum gk <name> <player>"));
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("kit")) {
						p.openInventory(new KitGUI(p).getResult());
					} else if (args[0].equalsIgnoreCase("shop")) {
						p.openInventory(new ShopGUI(p).getResult());
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("kit")) {
						Kit.give(Kit.byName(args[1]), p);
					}
				} else if (args.length <= 3) {
					if (Bukkit.getPlayer(args[2]) != null) {
						Player t = Bukkit.getPlayer(args[2]);
						if (args[0].equalsIgnoreCase("gm")) {
							if (isInt(args[1])) {
								int amount = Integer.parseInt(args[1]);
								PlayerData tp = new PlayerData(t);
								tp.giveCoins(amount);
								p.sendMessage(m.ECONOMY
										+ ChatBuilder.build("You gave", amount + "", "coins to", tp.getNick()));
								t.sendMessage(m.ECONOMY
										+ ChatBuilder.build("The player", pd.getNick(), "gave you", amount + ""));
							}
						} else if (args[0].equalsIgnoreCase("gk")) {
							if (Kit.isKit(args[1])) {
								PlayerData tp = new PlayerData(t);
								tp.giveKit(args[1]);
								t.sendMessage(
										m.KIT_SHOP + ChatBuilder.build(pd.getNick(), "gave you the kit", args[1]));
							} else {
								p.sendMessage(m.KIT_SHOP + "Kit not found");
							}
						}
					} else {
						p.sendMessage(m.OFFLINE_PLAYER);
					}
				}
			}
		}
		return false;
	}

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
