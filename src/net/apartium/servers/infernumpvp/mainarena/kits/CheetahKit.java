package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class CheetahKit extends Kit {

	public CheetahKit() {
		super("Cheetah", 125, "I am the fastest one in the world!");

	}

	@Override
	public ItemStack helmet() {
		try {
			return ItemUtil.getSkullFromURL(
					"http://textures.minecraft.net/texture/845eae6fcd84bb42237f076ef3a514695f146174085362b77fead4506864e8",
					"Cheetah");
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.DIAMOND_CHESTPLATE);
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
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
		p.getInventory().addItem(ItemUtil.easy(Material.STONE_SWORD, "§cCheetah Sword"));
	}

	@Override
	public ItemStack icon() {
		try {
			return ItemUtil.getSkullFromURL(
					"http://textures.minecraft.net/texture/845eae6fcd84bb42237f076ef3a514695f146174085362b77fead4506864e8",
					"Cheetah");
		} catch (Exception e) {
			return null;
		}
	}
}
