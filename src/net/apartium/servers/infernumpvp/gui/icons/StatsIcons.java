package net.apartium.servers.infernumpvp.gui.icons;

import java.text.DecimalFormat;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.PlayerData;

public class StatsIcons {

	public static class Kills extends GUIIcon {

		public Kills(Player p) {
			super("§6Kills §c" + new PlayerData(p).getKills(), null, Material.DIAMOND_SWORD);
		}

	}

	public static class Deaths extends GUIIcon {
		public Deaths(Player p) {
			super("§6Deaths §c" + new PlayerData(p).getDeaths(), null, Material.LEATHER);
		}
	}

	public static class KD extends GUIIcon {
		public KD(Player p) {
			super("§6K/D Ratio §c" + new DecimalFormat("0.00").format(new PlayerData(p).getKDR()), null,
					Material.REDSTONE_BLOCK);
		}
	}

	public static class KS extends GUIIcon {
		public KS(Player p) {
			super("§6Highest Killstreak §c" + new PlayerData(p).getHighestKS(), null, Material.GOLDEN_APPLE);
		}
	}
}
