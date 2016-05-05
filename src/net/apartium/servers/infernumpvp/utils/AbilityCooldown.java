package net.apartium.servers.infernumpvp.utils;
 
import java.util.HashMap;
 
public class AbilityCooldown {
 
    public String ability = "";
    public String player = "";
    public long seconds;
    public long systime;
 
    public HashMap<String, AbilityCooldown> cooldownMap = new HashMap<String, AbilityCooldown>();
 
    public AbilityCooldown(String player, long seconds, long systime) {
        this.player = player;
        this.seconds = seconds;
        this.systime = systime;
    }
 
    public AbilityCooldown(String player) {
        this.player = player;
    }
 
}
 