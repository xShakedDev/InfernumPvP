package net.apartium.servers.infernumpvp.listeners.arena;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class AntiTnT implements Listener {
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onExplode2(ExplosionPrimeEvent e){
		e.setCancelled(true);
	}
}
