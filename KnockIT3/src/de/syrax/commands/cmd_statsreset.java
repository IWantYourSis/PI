package de.syrax.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.syrax.main.Main;
import de.syrax.stats.Stats;

public class cmd_statsreset implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.isOp()){
		   if(args.length == 0){
           Stats.setKills(p.getName(), 0);
           Stats.setDeaths(p.getName(), 0);
           Main.getScoreboardManager().sendPacketsScoreboard(p);
		   Stats.update();
		}else if(args.length == 1){
			for (Player all : Bukkit.getOnlinePlayers()){
			String pl = String.valueOf(args[0]);
			Stats.setKills(pl, 0);
	           Stats.setDeaths(pl, 0);
			   Stats.update();
			   Main.getScoreboardManager().sendPacketsScoreboard(all);
		}
		}
		   else{
			p.sendMessage(Main.pre+ "/statsreset");
		}
		}else{
			p.sendMessage(Main.pre+ "Dir fehlen Rechte...");
		}
			}
		return false;
		}
}
