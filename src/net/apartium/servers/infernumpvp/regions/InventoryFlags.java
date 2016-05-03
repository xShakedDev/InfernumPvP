package net.apartium.servers.infernumpvp.regions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.apartium.servers.infernumpvp.regions.Region.Flag_List;
import net.apartium.servers.infernumpvp.regions.Region.Flags;
import net.apartium.servers.infernumpvp.utils.AnvilGUI;
import net.apartium.servers.infernumpvp.utils.AnvilGUI.AnvilClickEventHandler;

public class InventoryFlags implements Listener {
	public static Inventory inv;

	public static Inventory setupInventory(String rgname) {
		inv = Bukkit.createInventory(null, 18, "§3" + rgname + " Flags");
		ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
		ItemMeta flagsoffm = flagoff.getItemMeta();
		ItemMeta flagsonm = flagon.getItemMeta();
		// ========== Allow Soup Drop ==========
		Region rgm = Region.byName(rgname);
		if (rgm.getFlag(Flags.ALLOW_SOUPDROP)) {
			flagsonm.setDisplayName("§aSoup Drop");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cSoup Drop");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Allow Soup Sign ==========
		if (rgm.getFlag(Flags.ALLOW_SOUPSSIGN)) {
			flagsonm.setDisplayName("§aSoup Sign");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cSoup Sign");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Blocked CMDS ==========
		if (rgm.getFlag(Flags.ALLOW_SOUPSSIGN)) {
			flagsonm.setDisplayName("§aBlocked Cmds");
			flagsonm.setLore(rgm.getFlagsList(Flag_List.BLOCKED_CMD));
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cBlocked Cmds");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Build ==========
		if (rgm.getFlag(Flags.BUILD)) {
			flagsonm.setDisplayName("§aBuild");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cBuild");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Destroy ==========
		if (rgm.getFlag(Flags.DESTROY)) {
			flagsonm.setDisplayName("§aDestroy");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cDestroy");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Entry ==========
		if (rgm.getFlag(Flags.ENTRY)) {
			flagsonm.setDisplayName("§aEntry");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cEntry");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Item Drop ==========
		if (rgm.getFlag(Flags.ITEMDROP)) {
			flagsonm.setDisplayName("§aItem Drop");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cItem Drop");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Item Pick Up ==========
		if (rgm.getFlag(Flags.ITEMPICKUP)) {
			flagsonm.setDisplayName("§aItem Pick Up");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cItem Pick Up");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Join Combat ==========
		if (rgm.getFlag(Flags.JOINCOMBAT)) {
			flagsonm.setDisplayName("§aJoin Combat");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cJoin Combat");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}

		// ========== Damage ==========
		if (rgm.getFlag(Flags.DAMAGE)) {
			flagsonm.setDisplayName("§aPlayer God");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cPlayer God");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Mob Spawning ==========
		if (rgm.getFlag(Flags.MOB_SPAWNING)) {
			flagsonm.setDisplayName("§aMob Spawning");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cMob Spawning");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== PvP ==========
		if (rgm.getFlag(Flags.PVP)) {
			flagsonm.setDisplayName("§aPvP");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cPvP");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Explode ==========
		if (rgm.getFlag(Flags.EXPLODE)) {
			flagsonm.setDisplayName("§aExplode");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cExplode");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}
		// ========== Hunger ==========
		if (rgm.getFlag(Flags.HUNGER)) {
			flagsonm.setDisplayName("§aHunger");
			flagon.setItemMeta(flagsonm);
			inv.addItem(flagon);
		} else {
			flagsoffm.setDisplayName("§cHunger");
			flagoff.setItemMeta(flagsoffm);
			inv.addItem(flagoff);
		}

		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().equals(inv)) {
			e.setCancelled(true);
			Region rgm = Region.byName(e.getInventory().getName().split("3")[1].split(" Flags")[0]);
			// ========== Build ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBuild")) {
				rgm.setFlag(Flags.BUILD, false);
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cBuild");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBuild")) {
				rgm.setFlag(Flags.BUILD, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aBuild");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Blocked CMDs ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBlocked Cmds")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cBlocked Cmds");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.BLOCK_CMD, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBlocked Cmds")) {
				rgm.setFlag(Flags.BLOCK_CMD, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aBlocked Cmds");
				flagon.setItemMeta(flagonm);
				final Player p = (Player) e.getWhoClicked();
				e.getInventory().setItem(e.getSlot(), flagon);
				p.closeInventory();
				AnvilGUI agui = new AnvilGUI((Player) p);
				AnvilClickEventHandler e1 = new AnvilClickEventHandler() {
					@Override
					public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
						if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
							event.setWillClose(true);
							event.setWillDestroy(true);
							String[] commands = event.getName().split(",");
							p.sendMessage("§aAdded: " + commands[0]);
						} else {
							event.setWillClose(false);
							event.setWillDestroy(false);
						}
					}
				};
				agui.setHandler(e1);
				ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta im = (SkullMeta) i.getItemMeta();
				im.setDisplayName("Nick-Name");
				im.setOwner(p.getName());
				i.setItemMeta(im);

				agui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);

				agui.open();
			}
			// ========== Join Combat ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aJoin Combat")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cJoin Combat");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.JOINCOMBAT, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cJoin Combat")) {
				rgm.setFlag(Flags.JOINCOMBAT, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aJoin Combat");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== PvP ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPvP")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cBuild");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.PVP, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPvP")) {
				rgm.setFlag(Flags.PVP, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aPvP");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}

			// ========== Allow Soup Drop ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSoup Drop")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cSoup Drop");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.ALLOW_SOUPDROP, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSoup Drop")) {
				rgm.setFlag(Flags.ALLOW_SOUPDROP, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aSoup Drop");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Allow Soup Sign ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSoup Sign")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cSoup Sign");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.ALLOW_SOUPSSIGN, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSoup Sign")) {
				rgm.setFlag(Flags.ALLOW_SOUPSSIGN, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aSoup Sign");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Allow ItemDrop ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aItem Drop")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cItem Drop");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.ITEMDROP, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cItem Drop")) {
				rgm.setFlag(Flags.ITEMDROP, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aItem Drop");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Allow ItemPickup ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aItem Pick Up")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cItem Pick up");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.ITEMPICKUP, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cItem Pick Up")) {
				rgm.setFlag(Flags.ITEMPICKUP, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aItem Pick up");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Player God ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlayer God")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cPlayer God");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.DAMAGE, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPlayer God")) {
				rgm.setFlag(Flags.DAMAGE, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aPlayer God");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Allow Mob Spawning ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMob Spawning")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cMob Spawning");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.MOB_SPAWNING, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMob Spawning")) {
				rgm.setFlag(Flags.MOB_SPAWNING, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aMob Spawning");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Explode ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aExplode")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cExplode");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.EXPLODE, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cExplode")) {
				rgm.setFlag(Flags.EXPLODE, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aExplode");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Entry ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEntry")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cEntry");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.ENTRY, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cEntry")) {
				rgm.setFlag(Flags.ENTRY, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§aEntry");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Destroy ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDestroy")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cDestroy");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.DESTROY, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDestroy")) {
				rgm.setFlag(Flags.DESTROY, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§cDestroy");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}
			// ========== Hunger ==========
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHunger")) {
				ItemStack flagoff = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
				ItemMeta flagoffm = flagoff.getItemMeta();
				flagoffm.setDisplayName("§cHunger");
				flagoff.setItemMeta(flagoffm);
				e.getInventory().setItem(e.getSlot(), flagoff);
				rgm.setFlag(Flags.HUNGER, false);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cHunger")) {
				rgm.setFlag(Flags.HUNGER, true);
				ItemStack flagon = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
				ItemMeta flagonm = flagon.getItemMeta();
				flagonm.setDisplayName("§cHunger");
				flagon.setItemMeta(flagonm);
				e.getInventory().setItem(e.getSlot(), flagon);
			}

		}
	}
}