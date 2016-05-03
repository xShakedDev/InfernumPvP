package net.apartium.servers.infernumpvp._1v1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import mkremins.fanciful.FancyMessage;
import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class OvOGUI implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	public static Map<UUID, UUID> requests = new HashMap<>();

	// When player click someone its inviting him
	// then when the player he clicked clicking him back he is accepting the
	// invitation
	// then its opening the player who invited him first the GUI(i will
	// do{xShaked})
	// then when the player is clicking the START button in inv its starting the
	// game with the options he selected
	@EventHandler
	public void onInteractOvOStick(PlayerInteractEntityEvent e) {
		if (((e.getPlayer() instanceof Player)) && ((e.getRightClicked() instanceof Player))) {
			Player p = e.getPlayer();
			PlayerData pd = new PlayerData(p);
			if (!(e.getRightClicked() instanceof Player))
				return;

			Player t = (Player) e.getRightClicked();
			if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
				if (ArenasManager.getArenas().getList().isEmpty()
						|| ArenasManager.getArenas().getList() == null) {
					p.sendMessage(m.OVO + ChatBuilder.build("There is no arenas you can join."));
					return;
				}
				if(requests.containsKey(p.getUniqueId())) 
					requests.replace(p.getUniqueId(),t.getUniqueId());
				else
					requests.put(p.getUniqueId(), t.getUniqueId());
				
				new FancyMessage(ChatColor.AQUA+pd.getNick()+"invited you to duel").then("Accept").
					command("/1v1 join "+p.getName()).send(t);
			}
		}
	}
}
