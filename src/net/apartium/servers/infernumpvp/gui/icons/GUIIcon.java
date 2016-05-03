package net.apartium.servers.infernumpvp.gui.icons;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIIcon {
	
	protected String name;
	protected List<String> lore;
	protected Material material;
	
	public GUIIcon(String name, List<String> lore, Material m) {
		this.name=name;
		this.lore=lore;
		this.material=m;
	}
	
	public String name() {return name;}
	
	public List<String> lore() {return lore;}
	
	public Material material() {return material;}
	
	public final ItemStack item() {
		ItemStack item = new ItemStack(material());
		ItemMeta im = item.getItemMeta();
		if(lore()!=null) 
			im.setLore(lore());
		
		im.setDisplayName(name());
		item.setItemMeta(im);
		return item;
	}
}
