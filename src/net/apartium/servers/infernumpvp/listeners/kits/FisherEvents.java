package net.apartium.servers.infernumpvp.listeners.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class FisherEvents implements Listener {
	@EventHandler
	public void onFishEvent(PlayerFishEvent e) {
		if (e.getPlayer() instanceof Player) {
			if (e.getCaught() instanceof Player) {
				Player p = e.getPlayer();
				Player c = (Player) e.getCaught();
				Vector v = p.getVelocity().setY(0.5);
				v.multiply(1.5);
				c.setVelocity(v);
			}
		}
	}
}
