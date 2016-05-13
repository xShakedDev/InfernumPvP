package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;

public class LeaveEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
	}
}
