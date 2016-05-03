package net.apartium.servers.infernumpvp.listeners.arena;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityShotEvent implements Listener {
	@EventHandler
	public void onShot(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Egg) {
			if (((Egg) e.getEntity()).getShooter() instanceof Player) {
				Projectile egg = e.getEntity();
				egg.getWorld().createExplosion(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(),
						e.getEntity().getLocation().getZ(), 2F, false, false);
			}
		}
	}
}
