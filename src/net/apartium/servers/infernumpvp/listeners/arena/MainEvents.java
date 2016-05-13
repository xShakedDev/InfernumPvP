package net.apartium.servers.infernumpvp.listeners.arena;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

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

	@EventHandler(ignoreCancelled = true)
	public void onArmorSlot(InventoryClickEvent event) {
		if (event.getSlotType().equals(SlotType.ARMOR) && event.getInventory().getItem(event.getSlot()) != null)
			event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType().name() == "BOWL"
				|| e.getItemDrop().getItemStack().getType().name() == "MUSHROOM_SOUP")
			e.getItemDrop().remove();
		else
			e.setCancelled(true);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBreak(BlockBreakEvent e) {
		if (m.breaks.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBreak(BlockPlaceEvent e) {
		if (m.breaks.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		e.setRespawnLocation(new Location(m.spawnWorld, -485.500, 123.5, -98.5));

		ItemStack select = ItemUtil.easy(Material.CHEST, ChatColor.GRAY + "Kits");
		ItemStack shop = ItemUtil.easy(Material.ENDER_CHEST, ChatColor.GRAY + "Kit Shop");

		p.getInventory().clear();
		for (PotionEffect pe : p.getActivePotionEffects())
			p.removePotionEffect(pe.getType());
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setGameMode(GameMode.SURVIVAL);

		p.getInventory().setItem(0, select);
		p.getInventory().setItem(2, shop);
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(p.getName());
		im.setDisplayName("§c" + p.getName() + " Stats");
		is.setItemMeta(im);
		p.getInventory().addItem(ItemUtil.easy(Material.BLAZE_ROD, "§61V1 Stick"));
		p.getInventory().setItem(8, is);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void noFood(FoodLevelChangeEvent event) {
		event.setCancelled(true);
		((Player) event.getEntity()).setFoodLevel(20);
	}

	@EventHandler(priority = EventPriority.HIGH)
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

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent ev) {
		if ((ev.getEntity() instanceof Player)) {
			ev.setCancelled(false);
			Player p = (Player) ev.getEntity();
			ItemStack[] armor = p.getInventory().getArmorContents();
			for (ItemStack armo : armor) {
				if (armo != null) {
					if (armo.getTypeId() != 397)
						armo.setDurability((short) -armo.getType().getMaxDurability());
					p.updateInventory();
				}
			}
			if ((ev.getDamager() instanceof Player)) {
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
