package net.apartium.servers.infernumpvp._1v1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.InvUtil;

public class DuelCustomizer implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();
	static Inventory i;

	public static Inventory gen(Player inviter, Player invited) {
		i = Bukkit.createInventory(null, 9 * 6, "§6" + inviter.getName() + "§4 VS §6" + invited.getName());
		ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS);
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack start = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
		ItemStack cancel = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		ItemMeta soupm = soup.getItemMeta();
		ItemMeta startm = start.getItemMeta();
		ItemMeta cancelm = cancel.getItemMeta();
		soupm.setDisplayName("§aWith soup");
		soup.setItemMeta(soupm);
		startm.setDisplayName("§aStart");
		start.setItemMeta(startm);
		cancelm.setDisplayName("§cCancel");
		cancel.setItemMeta(cancelm);
		i.setItem(13, head);
		i.setItem(20, soup);
		i.setItem(22, chest);
		i.setItem(24, sword);
		i.setItem(31, leg);
		i.setItem(40, boot);
		i.setItem(37, cancel);
		i.setItem(43, start);
		InvUtil.fillInventory(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
		return i;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void invClick(InventoryClickEvent e) {
		if (e.getInventory() == null)
			return;
		if (e.getInventory().getSize() < 35)
			return;
		if (e.getInventory().getItem(43).getType() == Material.STAINED_CLAY
				&& e.getInventory().getItem(37).getType() == Material.STAINED_CLAY) {
			e.setCancelled(true);
			ItemStack clicked = e.getCurrentItem();
			ItemMeta clickedm = clicked.getItemMeta();
			// ====================== Helmet ======================
			if (clicked.getType() == Material.DIAMOND_HELMET) {
				clicked.setType(Material.GOLD_HELMET);
			} else if (clicked.getType() == Material.GOLD_HELMET) {
				clicked.setType(Material.IRON_HELMET);
			} else if (clicked.getType() == Material.IRON_HELMET) {
				clicked.setType(Material.LEATHER_HELMET);
			} else if (clicked.getType() == Material.LEATHER_HELMET) {
				clicked.setType(Material.DIAMOND_HELMET);
			}
			// ====================== CHESTPLATE ======================
			if (clicked.getType() == Material.DIAMOND_CHESTPLATE) {
				clicked.setType(Material.GOLD_CHESTPLATE);
			} else if (clicked.getType() == Material.GOLD_CHESTPLATE) {
				clicked.setType(Material.IRON_CHESTPLATE);
			} else if (clicked.getType() == Material.IRON_CHESTPLATE) {
				clicked.setType(Material.LEATHER_CHESTPLATE);
			} else if (clicked.getType() == Material.LEATHER_CHESTPLATE) {
				clicked.setType(Material.DIAMOND_CHESTPLATE);
			}
			// ====================== LEGGINGS ======================
			if (clicked.getType() == Material.DIAMOND_LEGGINGS) {
				clicked.setType(Material.GOLD_LEGGINGS);
			} else if (clicked.getType() == Material.GOLD_LEGGINGS) {
				clicked.setType(Material.IRON_LEGGINGS);
			} else if (clicked.getType() == Material.IRON_LEGGINGS) {
				clicked.setType(Material.LEATHER_LEGGINGS);
			} else if (clicked.getType() == Material.LEATHER_LEGGINGS) {
				clicked.setType(Material.DIAMOND_LEGGINGS);
			}
			// ====================== BOOTS ======================
			if (clicked.getType() == Material.DIAMOND_BOOTS) {
				clicked.setType(Material.GOLD_BOOTS);
			} else if (clicked.getType() == Material.GOLD_BOOTS) {
				clicked.setType(Material.IRON_BOOTS);
			} else if (clicked.getType() == Material.IRON_BOOTS) {
				clicked.setType(Material.LEATHER_BOOTS);
			} else if (clicked.getType() == Material.LEATHER_BOOTS) {
				clicked.setType(Material.DIAMOND_BOOTS);
			}
			// ====================== SWORD ======================
			if (clicked.getType() == Material.DIAMOND_SWORD) {
				clicked.setType(Material.GOLD_SWORD);
			} else if (clicked.getType() == Material.GOLD_SWORD) {
				clicked.setType(Material.IRON_SWORD);
			} else if (clicked.getType() == Material.IRON_SWORD) {
				clicked.setType(Material.STONE_SWORD);
			} else if (clicked.getType() == Material.STONE_SWORD) {
				clicked.setType(Material.WOOD_SWORD);
			} else if (clicked.getType() == Material.WOOD_SWORD) {
				clicked.setType(Material.DIAMOND_SWORD);
			}
			// ====================== SOUP ======================
			if (clicked.getType() == Material.MUSHROOM_SOUP) {
				clickedm.setDisplayName("§cNo Soup");
				clicked.setItemMeta(clickedm);
				clicked.setType(Material.BOWL);
			} else if (clicked.getType() == Material.BOWL) {
				clickedm.setDisplayName("§aWith Soup");
				clicked.setItemMeta(clickedm);
				clicked.setType(Material.MUSHROOM_SOUP);
			}
			// ====================== START/CANCEL ======================
			if (clicked.getType() == Material.STAINED_CLAY) {
				// START
				if (clicked.getDurability() == 5) {
					Player tar = (Player) Bukkit.getPlayer(OvOGUI.ingame.get(e.getWhoClicked().getUniqueId()));
					Player p = (Player) e.getWhoClicked();
					e.getWhoClicked().closeInventory();
					Arena a = ArenasManager.getArenas().randomArena();
					e.getWhoClicked().teleport(a.getPos1());
					tar.teleport(a.getPos2());
					tar.getInventory().getItemInHand().setType(Material.AIR);
					for (PotionEffect pe : tar.getActivePotionEffects())
						tar.removePotionEffect(pe.getType());
					tar.getInventory().clear();
					tar.getInventory().setHelmet(e.getInventory().getItem(13));
					tar.getInventory().setChestplate(e.getInventory().getItem(22));
					tar.getInventory().setLeggings(e.getInventory().getItem(31));
					tar.getInventory().setBoots(e.getInventory().getItem(40));
					tar.getInventory().addItem(e.getInventory().getItem(24));
					if (e.getInventory().getItem(20).getType() == Material.MUSHROOM_SOUP) {
						m.giveSoup(tar);
					}
					p.teleport(a.getPos1());
					p.getInventory().clear();
					p.getInventory().getItemInHand().setType(Material.AIR);
					for (PotionEffect pe : p.getActivePotionEffects())
						p.removePotionEffect(pe.getType());
					p.getInventory().setHelmet(e.getInventory().getItem(13));
					p.getInventory().setChestplate(e.getInventory().getItem(22));
					p.getInventory().setLeggings(e.getInventory().getItem(31));
					p.getInventory().setBoots(e.getInventory().getItem(40));
					p.getInventory().addItem(e.getInventory().getItem(24));
					for (Player ps : Bukkit.getOnlinePlayers()) {
						if (ps != p && ps != tar) {
							p.hidePlayer(ps);
							tar.hidePlayer(ps);
							ps.hidePlayer(p);
							ps.hidePlayer(tar);
						}
					}
					if (e.getInventory().getItem(20).getType() == Material.MUSHROOM_SOUP) {
						m.giveSoup(p);
					}
				} else {
					// STOP
					if (clicked.getDurability() == 14) {
						OvOGUI.ingame.remove(e.getWhoClicked().getUniqueId());
						OvOGUI.ingame.remove(OvOGUI.ingame.get(e.getWhoClicked().getUniqueId()));
					}
				}
			}
		}
	}
}
