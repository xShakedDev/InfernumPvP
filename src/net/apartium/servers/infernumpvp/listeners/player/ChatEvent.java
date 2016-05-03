package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

@SuppressWarnings("deprecation")
public class ChatEvent implements Listener {

	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onChat(PlayerChatEvent e) {
		PlayerData pd = new PlayerData(e.getPlayer());
		e.setCancelled(true);
		Bukkit.broadcastMessage(ChatBuilder.build("", pd.getNick() + "  >", e.getMessage()));
	}
}
