package net.apartium.servers.infernumpvp.test;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
 
public class Targeter {
 
	Player player;
	
	public Targeter(Player player) {
		this.player = player;
	}
	
	
    public Player getTargetPlayer() {
        return getTarget(player, player.getWorld().getPlayers());
    }
 
    public Entity getTargetEntity() {
        return getTarget(player, player.getWorld().getEntities());
    }
 
    public static <T extends Entity> T getTarget(final Entity entity,
            final Iterable<T> entities) {
        if (entity == null)
            return null;
        T target = null;
        final double threshold = 1;
        for (final T other : entities) {
            final Vector n = other.getLocation().toVector()
                    .subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n)
                    .lengthSquared() < threshold
                    && n.normalize().dot(
                            entity.getLocation().getDirection().normalize()) >= 0) {
                if (target == null
                        || target.getLocation().distanceSquared(
                                entity.getLocation()) > other.getLocation()
                                .distanceSquared(entity.getLocation()))
                    target = other;
            }
        }
        return target;
    }
 
}