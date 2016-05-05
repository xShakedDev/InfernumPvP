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
import org.bukkit.inventory.EquipmentSlot;

import mkremins.fanciful.FancyMessage;
import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class OvOListener implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	public static Map<UUID, UUID> requests = new HashMap<>();
	public static Map<UUID, UUID> ingame = new HashMap<>();

	// When player click someone its inviting him
	// then when the player he clicked clicking him back he is accepting the
	// invitation
	// then its opening the player who invited him first the GUI(i will
	// do{xShaked})
	// then when the player is clicking the START button in inv its starting the
	// game with the options he selected
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractOvOStick(PlayerInteractEntityEvent e) {
		if (((e.getPlayer() instanceof Player)) && ((e.getRightClicked() instanceof Player))) {
			Player p = e.getPlayer();
			PlayerData pd = new PlayerData(p);
			Player t = (Player) e.getRightClicked();
			EquipmentSlot ev = e.getHand();
			if (ev.equals(EquipmentSlot.HAND)) {
				if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
					if (ArenasManager.getArenas().getList().isEmpty() || ArenasManager.getArenas().getList() == null) {
						pd.sendMessage(m.OVO + ChatBuilder.build("There is no arenas you can join."));
						return;
					}
					Arena a = null;
					a = ArenasManager.getArenas().randomArena();
					if (a == null) {
						pd.sendMessage(m.OVO + ChatBuilder.build("There is no arenas you can join."));
						return;
					}
					if (a.isInuse()) {
						pd.sendMessage(m.OVO + ChatBuilder.build("There is no arenas you can join."));
						return;
					}
					if (!ingame.containsKey(e.getRightClicked().getUniqueId())) {
						if (requests.get(p.getUniqueId()) != e.getRightClicked().getUniqueId()) {
							if (requests.containsKey(p.getUniqueId())) {
								requests.replace(p.getUniqueId(), t.getUniqueId());
								return;
							}
							requests.put(p.getUniqueId(), t.getUniqueId());
							a.setTempplayer(p);
							pd.sendMessage(m.OVO + ChatBuilder.build("Waiting the other player to accept."));
							new FancyMessage(ChatColor.AQUA + pd.getNick() + " invited you to duel").then(" §6[Accept]")
									.tooltip("Click to accept").command("/ovo join " + p.getName()).send(t);
						} else {
							pd.sendMessage(m.OVO + ChatBuilder.build("You already invited this player to game."));
						}
					} else {
						pd.sendMessage(m.OVO + ChatBuilder
								.build("The player you trying to invite is already ingame/customizing game."));
					}
				}
			}
		}
	}
}
