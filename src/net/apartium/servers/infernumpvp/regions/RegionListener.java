package net.apartium.servers.infernumpvp.regions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.regions.Region.Flag_List;
import net.apartium.servers.infernumpvp.regions.Region.Flags;

public class RegionListener implements Listener {
	private static InfernumPvP plugin = InfernumPvP.getInstance();
	//private static Map<UUID,Region> players = new HashMap<>();
	
	public static boolean playerInRegion(Location loc) {
		return getLocationRegion(loc)!=null;
	}

	public static Region getLocationRegion(Location loc) {
		for (Region r : Region.regions.values()) {
			if(!r.isGlobal()) {
				double fromx = r.getMinLocation().getX();
				double fromz = r.getMinLocation().getZ();
				double tox = r.getMaxLocation().getX();
				double toz = r.getMaxLocation().getZ();
				World w = r.getWorld();

				if (loc.getX() < fromx && loc.getX() > tox && loc.getZ() < fromz && loc.getZ() > toz
						&& loc.getWorld().getName().equals(w))
					return r;	
			}
		}
		return null;
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (playerInRegion(p.getLocation())) {

			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (playerInRegion(e.getItemDrop().getLocation())) {
			if (!getLocationRegion(e.getItemDrop().getLocation()).getFlag(Flags.ITEMDROP)) {
				e.setCancelled(true);
				if (getLocationRegion(p.getLocation()).getFlag(Flags.ALLOW_SOUPDROP)) {
					if (e.getItemDrop().getItemStack().getTypeId() == 281
							|| e.getItemDrop().getItemStack().getTypeId() == 282) {
						e.setCancelled(false);
					}
				} else
					e.setCancelled(true);

			} else
				e.setCancelled(false);
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		Bukkit.broadcastMessage("a");
		if (playerInRegion(p.getLocation())) {
			Region rgm = getLocationRegion(p.getLocation());
			Bukkit.broadcastMessage(rgm.getName());

			if (rgm.getFlag(Flags.ITEMPICKUP)) 
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		PlayerData pd = new PlayerData(p);
		if (playerInRegion(e.getBlock().getLocation())) {
			Region rgm = getLocationRegion(e.getBlock().getLocation());
			if (rgm.getFlag(Flags.BUILD) && !pd.hasPermission("cubedpvp.region." + rgm.getName() + ".place", false)) {
				p.sendMessage(plugin.REGIONS + "§4You cant place blocks here!");

				e.setCancelled(true);
			}
		} else {
			e.setCancelled(false);
		}

	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		PlayerData pd = new PlayerData(p);

		if (playerInRegion(e.getBlock().getLocation())) {
			Region rgm = getLocationRegion(e.getBlock().getLocation());
			if (rgm.getFlag(Flags.DESTROY) && !pd.hasPermission("cubedpvp.region." + rgm.getName() + ".break", false)) {
				p.sendMessage(plugin.REGIONS + "§4You cant hasPermission blocks here!");
				e.setCancelled(true);
			}
		} else {
			e.setCancelled(false);
		}

	}

	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player d = (Player) e.getDamager();
			Player p = (Player) e.getEntity();

			if (playerInRegion(p.getLocation())) {
				if (getLocationRegion(p.getLocation()).getFlag(Flags.PVP)
						|| getLocationRegion(d.getLocation()).getFlag(Flags.PVP)) {
					e.setCancelled(true);
					d.sendMessage(plugin.REGIONS + "§4You cant damage players here!");
				} else {
					p.sendMessage(plugin.REGIONS + "§4§l ERROR, PLEASE CONTACE ADMIN");
				}
			}
		}
	}

	@EventHandler
	public void entityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (playerInRegion(p.getLocation())) {
				Region rgm = getLocationRegion(p.getLocation());
				if (rgm.getFlag(Flags.DAMAGE)) {
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
			}

		}
	}

	@EventHandler
	public void preCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (playerInRegion(p.getLocation())) {
			Region rgm = getLocationRegion(p.getLocation());
			if (rgm.getFlag(Flags.BLOCK_CMD) && rgm.getFlagsList(Flag_List.BLOCKED_CMD).contains(e.getMessage())
					&& rgm.getFlag(Flags.BLOCK_CMD)) {
				p.sendMessage(plugin.REGIONS + "§4This command is blocked here!");
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}

	}

	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e) {
		if (playerInRegion(e.getEntity().getLocation())) {
			Region rgm = getLocationRegion(e.getLocation());

			if (rgm.getFlag(Flags.MOB_SPAWNING)) {
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (playerInRegion(p.getLocation())) {
			Region rgm = getLocationRegion(p.getLocation());
			if (rgm.getFlag(Flags.ENTRY) && !p.hasPermission("cubedpvp.region." + rgm.getName() + ".entry")) {
				PlayerData pd = new PlayerData(p);
				pd.spawn();
				p.sendMessage(plugin.REGIONS + "§4You dont allowed to be here!");
				e.setCancelled(true);
			} else
				e.setCancelled(false);

		}
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		Entity ent = e.getEntity();
		if (playerInRegion(ent.getLocation())) {
			Region rgm = getLocationRegion(e.getLocation());
			if (!rgm.getFlag(Flags.EXPLODE)) {
				ent.remove();
				e.setCancelled(true);
			} else
				e.setCancelled(false);
		}
	}
}
