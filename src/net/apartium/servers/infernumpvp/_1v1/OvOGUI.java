package net.apartium.servers.infernumpvp._1v1;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class OvOGUI implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onInteractOvOStick(PlayerInteractEntityEvent e) {
		if (((e.getPlayer() instanceof Player)) && ((e.getRightClicked() instanceof Player))) {
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
				if (ArenasManager.getArenas().getList().isEmpty() || ArenasManager.getArenas().getList() == null) {
					{
						p.sendMessage(m.OVO + ChatBuilder.build("There is no arenas you can join."));
						return;

					}
				}

			}
		}
	}
}
