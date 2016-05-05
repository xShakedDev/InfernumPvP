package net.apartium.servers.infernumpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class Break_Command implements CommandExecutor {
	private InfernumPvP m = InfernumPvP.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			PlayerData pp = new PlayerData(((Player) sender).getUniqueId());
			if (label.equalsIgnoreCase("break")) {
				if (pp.hasPermission("infernumpvp.break", true)) {
					if (m.breaks.contains(pp.getPlayer().getUniqueId())) {
						pp.sendMessage(m.ADMINISTRATION + ChatBuilder.build("Block Break", "DISABLED"));
						m.breaks.remove(pp.getPlayer().getUniqueId());
					} else {
						pp.sendMessage(m.ADMINISTRATION + ChatBuilder.build("Block Break", "ENABLED"));
						m.breaks.add(pp.getPlayer().getUniqueId());
					}
				}
			}
		}
		return false;
	}

}
