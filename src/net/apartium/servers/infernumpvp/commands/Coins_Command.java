package net.apartium.servers.infernumpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class Coins_Command implements CommandExecutor {
	private InfernumPvP m = InfernumPvP.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			PlayerData pp = new PlayerData(((Player) sender).getUniqueId());
			if (label.equalsIgnoreCase("coins"))
				if (args.length == 0)
					sender.sendMessage(m.ECONOMY + ChatBuilder.build("Your coins", pp.getCoins() + ""));
				else {
					if (Bukkit.getPlayer(args[0]) != null) {
						PlayerData td = new PlayerData(Bukkit.getPlayer(args[0]));
						sender.sendMessage(
								m.ECONOMY + ChatBuilder.build(td.getNick(), "has", td.getCoins() + "", "coins"));
					}
				}
		}
		return false;
	}

}
