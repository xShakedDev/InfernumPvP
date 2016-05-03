package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class CheaterMan extends Kit implements Listener{

	public CheaterMan() {
		super("CheaterMan", 0);
	}

	@Override
	public ItemStack helmet() {
		// TODO Auto-generated method stub
		return new ItemStack(Material.DIAMOND_HELMET);
	}

	@Override
	public ItemStack chestplate() {
		// TODO Auto-generated method stub
		return new ItemStack(Material.DIAMOND_CHESTPLATE);
	}

	@Override
	public ItemStack leggings() {
		// TODO Auto-generated method stub
		return new ItemStack(Material.DIAMOND_LEGGINGS);
	}

	@Override
	public ItemStack boots() {
		// TODO Auto-generated method stub
		return new ItemStack(Material.DIAMOND_HELMET);
	}

	@Override
	public Material icon() {
		return Material.COMMAND;
	}
	
	@Override
	public void onFill(Player p) {
		ItemStack sword = ItemUtil.easy(Material.DIAMOND_SWORD, ChatColor.BOLD+""+ChatColor.AQUA
				+"Sword of cheats");
		
		p.getInventory().addItem(sword);
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		if(e.getPlayer().getItemInHand().hasItemMeta()&&e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BOLD+""
					+ChatColor.AQUA+"Sword of cheats")&&e.getRightClicked() instanceof Player){
			((Player)e.getRightClicked()).setHealth(0);
		}
	}

}
