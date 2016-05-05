package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {

	@EventHandler
	public void onPing(ServerListPingEvent e) {
		e.setMotd("                      " + "§6§l-=§b§lInfernumPvP§r§6§l=-\n                       "
				+ ChatColor.RESET + status());
	}

	private String status() {
		if (!Bukkit.hasWhitelist())
			return "§a§nJoin now!";
		else
			return "§c§nMaintance";
	}
}
