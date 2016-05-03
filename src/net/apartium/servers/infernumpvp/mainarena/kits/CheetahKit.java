package net.apartium.servers.infernumpvp.mainarena.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class CheetahKit extends Kit {

	public CheetahKit() {
		super("Cheetah", 125);

	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.LEATHER_HELMET);
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.LEATHER_CHESTPLATE);
	}

	@Override
	public ItemStack leggings() {
		return new ItemStack(Material.LEATHER_LEGGINGS);
	}

	@Override
	public ItemStack boots() {
		return new ItemStack(Material.LEATHER_BOOTS);
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
	}

	@Override
	public Material icon() {
		try {
			return ItemUtil.getSkullFromURL(
					"http://textures.minecraft.net/texture/845eae6fcd84bb42237f076ef3a514695f146174085362b77fead4506864e8",
					"Cheetah").getType();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PotionEffect> pots() {
		ArrayList<PotionEffect> pot = new ArrayList<>();
		pots().add(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
		return pot;
	}
}
