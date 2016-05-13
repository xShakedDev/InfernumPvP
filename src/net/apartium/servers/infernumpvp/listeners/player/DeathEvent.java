package net.apartium.servers.infernumpvp.listeners.player;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp._1v1.ArenasManager;
import net.apartium.servers.infernumpvp._1v1.OvOListener;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class DeathEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();
	HashMap<String, Integer> killstreak = new HashMap<>();

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = e.getEntity();
			e.getDrops().clear();
			PlayerData pd = new PlayerData(p);
			pd.setDeaths(pd.getDeaths() + 1);
			if (killstreak.get(p.getName()) != null)
				if (pd.getHighestKS() < killstreak.get(p.getName()))
					pd.setHighKS(killstreak.get(p.getName()));
			killstreak.put(p.getName(), 0);
			if (e.getEntity().getKiller() instanceof Player)
				e.setDeathMessage(m.ARENA + ChatBuilder.build("The player", p.getName(), "was killed by",
						e.getEntity().getKiller().getName()));
			else
				e.setDeathMessage(m.ARENA + ChatBuilder.build("The player", p.getName(), "died!"));
			if (e.getEntity().getKiller() instanceof Player) {
				Player t = e.getEntity().getKiller();
				PlayerData td = new PlayerData(t);
				Random r = new Random();
				int i = r.nextInt(3) + 3;
				td.giveCoins(i);
				td.getPlayer().sendMessage(m.ECONOMY + ChatBuilder.build("You have earned", i + "", "coins for kill."));
				td.setKills(td.getKills() + 1);
				int amount = 0;
				for (ItemStack is : t.getInventory().all(Material.MUSHROOM_SOUP).values()) {
					amount = amount + is.getAmount();
				}
				p.sendMessage(m.ARENA + ChatBuilder.build("The player", t.getName(), "had", (int) t.getHealth() + "",
						"hearts left and"));
				p.sendMessage(ChatBuilder.build("", amount + "", "Soups"));
				if (killstreak.containsKey(t.getName()))
					killstreak.put(t.getName(), killstreak.get(t.getName()) + 1);
				else
					killstreak.put(t.getName(), 1);
				if (td.getHighestKS() < killstreak.get(t.getName()))
					td.setHighKS(killstreak.get(t.getName()));
				killstreak.put(p.getName(), 0);
				if ((killstreak.get(t.getName()) % 5) == 0 && killstreak.get(t.getName()) != 0)
					Bukkit.broadcastMessage(m.ARENA + ChatBuilder.build("The player", t.getName(),
							"is on Killstreak of", killstreak.get(t.getName()) + ""));
			}
			if (OvOListener.ingame.containsKey(pd.getPlayer().getUniqueId())) {
				ArenasManager.getArenas().getArenaByPlayer(pd.getPlayer()).endDuel();
				e.getEntity().sendMessage(
						m.OVO + ChatBuilder.build("You lose the 1V1 versus", e.getEntity().getKiller().getName()));
				e.getEntity().getKiller()
						.sendMessage(m.OVO + ChatBuilder.build("You won the 1V1 versus", e.getEntity().getName()));
				e.getEntity().getKiller().getInventory().setHeldItemSlot(5);
				Bukkit.dispatchCommand(e.getEntity().getKiller(), "spawn");
			}
		}
	}
}
