package de.syrax.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.syrax.main.Main;

public class listener_Break implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(Main.build.contains(p)) {
		e.setCancelled(false);
	}else {
		e.setCancelled(true);
	}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(Main.build.contains(p)) {
		e.setCancelled(false);
	}else {
		e.setCancelled(true);
	}
	}
}