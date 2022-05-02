package de.cure53.anton.lifesteal;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LifeSteal extends JavaPlugin implements Listener {

	/**
	 * Hier registrieren wir, dass ein Spieler einen anderen tötet
	 * 
	 * @param e
	 */
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		
		getLogger().info("Player wurde getötet");
	    Player player = e.getEntity();
		
		if (player.getKiller() != null) {
	    	
			// Wie viele Herzen hat der Killer?
	    	double killerHearts = player.getKiller().getMaxHealth();
	    	
	    	// Wie viele Herzen hat der Gekillte
	    	double killedHearts = player.getMaxHealth();
	    	
	    	// Setze Herzen des Gekillten runter / des Killers hoch
	    	if (killedHearts >= 10) {
	    		player.setMaxHealth(killedHearts - 2);
	    		player.getKiller().setMaxHealth(killerHearts + 2);
	    		player.sendMessage( 
	    			ChatColor.YELLOW + "Oh, no, you lost a heart, " + 
	    			ChatColor.RED + player.getKiller().getName() + 
	    			ChatColor.YELLOW + " gained one." );
	    	}
	    	
	    }
	}

	/**
	 * Hier registrieren wir, dass das Plugin lädt
	 * 
	 * @param e
	 */
	@Override
    public void onEnable() {
		getLogger().info("LifeSteal wurde gestartet");
		getServer().getPluginManager().registerEvents(this, this);
    }
	
	/**
	 * Hier registrieren wir, dass das Plugin entfernt wird
	 * 
	 * @param e
	 */
    @Override
    public void onDisable() {
    	getLogger().info("LifeSteal wurde beendet");
    }
    
}
