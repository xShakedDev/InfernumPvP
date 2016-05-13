package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class StomperKit extends Kit {
	public StomperKit() {
		super("Stomper", 300, "I will crush your ~HEAD~ from the air");
	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public ItemStack leggings() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public ItemStack boots() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(ItemUtil.easy(Material.STONE_SWORD, "§cStomper's Sword"));
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.ANVIL);
	}

}
