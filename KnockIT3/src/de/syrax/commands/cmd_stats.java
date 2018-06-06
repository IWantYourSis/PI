package de.syrax.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.syrax.stats.Stats;

public class cmd_stats implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length == 0){
				p.sendMessage("§eDeine Stats:");
				p.sendMessage("");
				p.sendMessage("§7K/D : §c"+Stats.getKD(p.getName()));
				p.sendMessage("§7Kills : §c"+Stats.getKills(p.getName()));
				p.sendMessage("§7Tode : §c"+Stats.getDeaths(p.getName()));
			}else if(args.length == 1){
				String name = String.valueOf(args[0]);
				p.sendMessage("§e"+name+"'s Stats:");
				p.sendMessage("");
				p.sendMessage("§7K/D : §c"+Stats.getKD((name)));
				p.sendMessage("§7Kills : §c"+Stats.getKills((name)));
				p.sendMessage("§7Tode : §c"+Stats.getDeaths((name)));
			}
		}
		return false;
	}

}
