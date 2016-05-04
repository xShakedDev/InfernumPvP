package net.apartium.servers.infernumpvp.listeners.kits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.apartium.servers.infernumpvp.InfernumPvP;

public class PyroEvents implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFire(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) {
				Block b = e.getClickedBlock();
				new BukkitRunnable() {

					@Override
					public void run() {
						b.getRelative(0, 1, 0).setType(Material.AIR);
						cancel();
					}
				}.runTaskLater(InfernumPvP.getInstance(), 100);
			}
		}
	}

}
