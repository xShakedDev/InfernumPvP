package net.apartium.servers.infernumpvp.listeners.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class StomperEvents implements Listener {
	private int FallDamage = 4;
	private InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void Fall(EntityDamageEvent event) {
		Entity ent = event.getEntity();
		if ((ent instanceof Player)) {
			Player player = (Player) ent;
			if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
				for (ItemStack is : player.getInventory().getContents()) {
					if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§cStomper's Sword")) {
						if (event.getDamage() > this.FallDamage)
							event.setCancelled(true);
						if (this.FallDamage > 0) {
							player.damage(this.FallDamage);
						}
						for (Entity nearby : player.getNearbyEntities(3.0D, 3.0D, 3.0D)) {
							if (!(nearby instanceof Player))
								continue;
							Player targetplayer = (Player) nearby;
							if (targetplayer.isSneaking()) {
								targetplayer.damage(4);
							} else {
								targetplayer.damage(event.getDamage());
							}
							event.setCancelled(true);
							if (targetplayer.isDead()) {
								Bukkit.broadcastMessage(m.ARENA + ChatBuilder.build("The player",
										targetplayer.getName(), "stomped by", player.getName()));
							}
						}
					}
				}
			}
		}
	}
}
