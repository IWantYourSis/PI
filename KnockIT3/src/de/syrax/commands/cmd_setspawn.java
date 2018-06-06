package de.syrax.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.syrax.main.Main;

public class cmd_setspawn implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 1){
					p.sendMessage(Main.pre+"Du hast den Spawn für die Map §e"+args[0]+" §7gesetzt!");
					Main.getMap().setMapSpawn(args[0], p.getLocation());
				}else{
					p.sendMessage(Main.pre+ "/setspawn <Map>");
				}
			}else{
				p.sendMessage(Main.pre+ "§cDir fehlen Rechte...");
			}
			
		}
		return false;
	}
}