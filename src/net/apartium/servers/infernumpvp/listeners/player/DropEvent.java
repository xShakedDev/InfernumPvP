package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;

public class DropEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler(priority = EventPriority.HIGH)
	public void onDrop(PlayerDropItemEvent e) {
		if (m.breaks.contains(e.getPlayer()))
			e.setCancelled(false);
		else
			e.setCancelled(true);
	}
}
