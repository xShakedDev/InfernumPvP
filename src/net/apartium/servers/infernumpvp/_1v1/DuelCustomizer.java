package net.apartium.servers.infernumpvp._1v1;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;
import net.apartium.servers.infernumpvp.utils.InvUtil;

public class DuelCustomizer implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();
	static Inventory i;
	static ArrayList<String> nomove = new ArrayList<>();

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
		if (e.getInventory().getTitle() == null)
			return;
		if (e.getCurrentItem() == null)
			return;
		if (e.getCurrentItem().getItemMeta() == null)
			return;
		if (e.getInventory().getTitle().contains("§4 VS")) {
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
					clickedm.setDisplayName("§aWith Pots");
					clicked.setItemMeta(clickedm);
					e.getInventory().setItem(20, new ItemStack(Material.POTION, 1, (short) 16421));
				} else if (clicked.getType() == Material.SPLASH_POTION) {
					clickedm.setDisplayName("§aWith Soup");
					clicked.setItemMeta(clickedm);
					clicked.setType(Material.MUSHROOM_SOUP);
				}
				// ====================== START/CANCEL ======================
				if (clicked.getType() == Material.STAINED_CLAY) {
					// START
					if (clicked.getDurability() == 5) {
						Player p = (Player) e.getWhoClicked();
						ArenasManager.getArenas().getArenaByPlayer(p).setCostumizing(false);
						Arena a = ArenasManager.getArenas().getArenaByPlayer((Player) e.getWhoClicked());
						Player tar = a.getOpponentinarena();
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
						if (e.getInventory().getItem(20).getType() == Material.SPLASH_POTION) {
							m.givePots(tar);
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
						p.setGameMode(GameMode.SURVIVAL);
						tar.setGameMode(GameMode.SURVIVAL);
						p.setHealth(20);
						tar.setHealth(20);
						a.setInuse(true);
						if (e.getInventory().getItem(20).getType() == Material.MUSHROOM_SOUP) {
							m.giveSoup(p);
						} else if (e.getInventory().getItem(20).getType() == Material.SPLASH_POTION) {
							m.givePots(p);
						}
						nomove.add(p.getName());
						nomove.add(tar.getName());
						new BukkitRunnable() {
							int timer = 5;

							@Override
							public void run() {
								if (timer == 0) {
									p.sendMessage(m.OVO + ChatBuilder.build("GO GO GO!!!"));
									tar.sendMessage(m.OVO + ChatBuilder.build("GO GO GO!!!"));
									nomove.remove(p.getName());
									nomove.remove(tar.getName());
									cancel();
								} else {
									p.sendMessage(m.OVO + ChatBuilder.build(timer + ""));
									tar.sendMessage(m.OVO + ChatBuilder.build(timer + ""));
									timer--;
								}
							}
						}.runTaskTimer(m, 0, 20l);
					} else {
						// CANCEL
						if (clicked.getDurability() == 14) {
							Player p = (Player) e.getWhoClicked();
							ArenasManager.getArenas().getArenaByPlayer(p).setInuse(false);
							p.closeInventory();
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (ArenasManager.getArenas().getArenaByPlayer((Player) e.getPlayer()) != null) {
			if (ArenasManager.getArenas().getArenaByPlayer((Player) e.getPlayer()).isCostumizing() == true) {
				if (e.getInventory().equals(i)) {
					Player tar = (Player) Bukkit.getPlayer(OvOListener.ingame.get(e.getPlayer().getUniqueId()));
					Player p = (Player) e.getPlayer();
					ArenasManager.getArenas().getArenaByPlayer(p).endDuel();
					tar.sendMessage(m.OVO + ChatBuilder.build("Your opponent canceled the duel."));
					p.sendMessage(m.OVO + ChatBuilder.build("You canceled the duel."));
				}
			}
		}
	}
}
