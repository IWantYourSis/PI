package de.syrax.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_info implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(args.length == 0){
			p.sendMessage("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬ §6§lKnockIT§8 ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
			p.sendMessage("");
			p.sendMessage("§8» §6§lKnockIT");
			p.sendMessage("§8» §7Von: §bIWantYourSis "); 
			p.sendMessage("§8» §7Version §8| §c2.0");
			p.sendMessage("");
			p.sendMessage("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬ §6§lKnockIT§8 ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		}
		return false;
	}
}
