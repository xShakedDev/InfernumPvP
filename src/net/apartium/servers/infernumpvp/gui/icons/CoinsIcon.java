package net.apartium.servers.infernumpvp.gui.icons;

import org.bukkit.Material;

import net.apartium.servers.infernumpvp.PlayerData;

public class CoinsIcon extends GUIIcon{
	public CoinsIcon(PlayerData p) {
		super("Current coins: "+p.getCoins(), null, Material.GOLD_INGOT);
		
	}
	
}
