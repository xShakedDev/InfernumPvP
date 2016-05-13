package net.apartium.servers.infernumpvp.ranks;

import org.bukkit.entity.Player;

import net.apartium.servers.infernumpvp.InfernumPvP;

public class Ranks {
	private InfernumPvP m = InfernumPvP.getInstance();
	Player p;

	public void Rank(Player p) {
		this.p = p;
	}

	public boolean hasRank() {
		if (getRank() != null)
			return true;
		else
			return false;
	}

	public String getRank() {
		if (m.rc.getString(p.getUniqueId() + ".rank.name") != null)
			return m.rc.getString(p.getUniqueId() + ".rank.name");
		else
			return null;
	}

	public boolean isRank(String rank) {
		if (m.rc.getStringList("ranks").contains(rank))
			return true;
		else
			return false;
	}

	public void setRank(String rank) {
		m.rc.set(p.getUniqueId() + ".rank.name", rank);
	}
}
