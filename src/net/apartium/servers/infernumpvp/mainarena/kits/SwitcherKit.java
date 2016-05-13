package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class SwitcherKit extends Kit {
	public SwitcherKit() {
		super("Switcher", 225, "I am there WOOPS now im there");
	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.IRON_HELMET);
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.IRON_CHESTPLATE);
	}

	@Override
	public ItemStack leggings() {
		return new ItemStack(Material.IRON_LEGGINGS);
	}

	@Override
	public ItemStack boots() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(ItemUtil.easy(Material.IRON_SWORD, "§cFisher's Sword"));
		p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 16));
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.SNOW_BALL);
	}
}
