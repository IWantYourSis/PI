package de.syrax.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.syrax.main.Main;

public class cmd_settop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("1")){
						Main.getMap().setTop("1", p.getLocation(), args[1]);
						p.sendMessage(Main.pre+"Du hast die Top §e1 §7Location für die Map §e"+args[1]+"gesetzt!");
					}else if(args[0].equalsIgnoreCase("2")){
						Main.getMap().setTop("2", p.getLocation(), args[1]);
						p.sendMessage(Main.pre+"Du hast die Top §e2 §7Location gesetzt!");
					}else if(args[0].equalsIgnoreCase("3")){
						Main.getMap().setTop("3", p.getLocation(), args[1]);
						p.sendMessage(Main.pre+"Du hast die Top §e3 §7Location gesetzt!");
					}else{
						p.sendMessage(Main.pre+"/settop 1-3 <Map>");
					}
				}else{
					p.sendMessage(Main.pre+"/settop 1-3 <Map>");
				}
			}else{
				p.sendMessage(Main.pre+"§cDir fehlen Rechte...");
			}
		}
		return false;
	}

}
