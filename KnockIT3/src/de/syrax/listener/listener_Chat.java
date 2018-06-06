package de.syrax.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class listener_Chat implements Listener {
	  
		@EventHandler
		public void onQuit(PlayerQuitEvent e){
			final Player p = e.getPlayer();
			p.getInventory().clear();
			e.setQuitMessage(null);
		
	  
	}
}

