package net.apartium.servers.infernumpvp.utils;

import static net.apartium.servers.infernumpvp.utils.NMSClassInteracter.asBukkitCopy;
import static net.apartium.servers.infernumpvp.utils.NMSClassInteracter.asNMSCopy;
import static net.apartium.servers.infernumpvp.utils.NMSClassInteracter.getNMS;

import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemUtil {

	public static ItemStack easy(Material m, String name) {
		ItemStack is = new ItemStack(m);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(name);
		is.setItemMeta(im);

		return is;
	}

	public static ItemStack skull(String player) {
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(player);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack getSkullFromURL(String url, String name) throws Exception {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner("NacOJerk");
		skull.setItemMeta(sm);
		url = Base64Coder.encodeString("{textures:{SKIN:{url:\"" + url + "\"}}}");
		GameProfile gp = new GameProfile(UUID.randomUUID(), name);
		gp.getProperties().put("textures", new Property("textures", url));

		Object isskull = asNMSCopy(skull);
		Object nbt = getNMS("NBTTagCompound").getConstructor().newInstance();
		Method serialize = getNMS("GameProfileSerializer").getMethod("serialize", getNMS("NBTTagCompound"),
				GameProfile.class);
		serialize.invoke(null, nbt, gp);
		Object nbtr = isskull.getClass().getMethod("getTag").invoke(isskull);
		nbtr.getClass().getMethod("set", String.class, getNMS("NBTBase")).invoke(nbtr, "SkullOwner", nbt);
		isskull.getClass().getMethod("setTag", getNMS("NBTTagCompound")).invoke(isskull, nbtr);
		skull = asBukkitCopy(isskull);
		return skull;
	}
}
