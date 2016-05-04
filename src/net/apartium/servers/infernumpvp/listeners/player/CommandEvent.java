package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp._1v1.OvOGUI;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class CommandEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (OvOGUI.ingame.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(m.OVO + ChatBuilder.build("Cant write messages while ingame!"));
		}
	}
}
