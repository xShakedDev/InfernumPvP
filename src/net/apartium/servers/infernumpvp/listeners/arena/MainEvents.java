package net.apartium.servers.infernumpvp.listeners.arena;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.apartium.servers.infernumpvp.InfernumPvP;

@SuppressWarnings("deprecation")
public class MainEvents implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getHealth() == 20) {
			if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
					&& player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
				event.setCancelled(true);
			}
		} else {
			int soup = +7;
			if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
					&& player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
				event.setCancelled(true);
				player.setHealth(player.getHealth() + soup > player.getMaxHealth() ? player.getMaxHealth()
						: player.getHealth() + soup);
				event.getPlayer().getItemInHand().setType(Material.BOWL);
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.dispatchCommand(e.getPlayer(), "spawn");
			}
		}.runTaskLater(m, 1);
	}

	@EventHandler
	public void noFood(FoodLevelChangeEvent event) {
		event.setCancelled(true);
		((Player) event.getEntity()).setFoodLevel(20);
	}

	@EventHandler
	public void noMobs(EntitySpawnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBurn(BlockBurnEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockSpread(BlockSpreadEvent event) {
		if (event.getBlock().getTypeId() != 3) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack handitem = e.getPlayer().getItemInHand();
		if (handitem.getType() == Material.FISHING_ROD || handitem.getType() == Material.FLINT_AND_STEEL) {
			if (handitem.getDurability() != 0) {
				if (handitem.getDurability() != handitem.getType().getMaxDurability()) {
					handitem.setDurability((short) Short.MIN_VALUE);
					e.getPlayer().updateInventory();
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent ev) {
		if ((ev.getEntity() instanceof Player)) {
			Player p = (Player) ev.getEntity();

			ItemStack[] armor = p.getInventory().getArmorContents();
			for (ItemStack armo : armor) {
				if (armo != null) {
					if (armo.getType() != Material.SKULL) {
						armo.setDurability((short) -armo.getType().getMaxDurability());
						p.updateInventory();
					}
				}
			}
		}
		if ((ev.getDamager() instanceof Player)) {
			Player p = (Player) ev.getDamager();
			ItemStack handitem = p.getItemInHand();
			if (handitem.getType() == Material.GOLD_SWORD) {
				handitem.setDurability((short) 30);
				p.updateInventory();
			}
			if (handitem.getDurability() != 0) {
				if (handitem.getDurability() != handitem.getType().getMaxDurability()) {
					handitem.setDurability((short) Short.MIN_VALUE);
					p.updateInventory();
				}
			}
		}
	}

	@EventHandler
	public void onBowUse(EntityShootBowEvent ev) {
		if ((ev.getEntity() instanceof Player)) {
			Player p = (Player) ev.getEntity();
			ItemStack handitem = p.getItemInHand();
			if (handitem.getDurability() != handitem.getType().getMaxDurability()) {
				handitem.setDurability((short) Short.MIN_VALUE);
				p.updateInventory();
			}
		}
	}
}
