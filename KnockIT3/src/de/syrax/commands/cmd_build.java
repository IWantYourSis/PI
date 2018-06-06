package de.syrax.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import de.syrax.main.Kit;
import de.syrax.main.Main;
import de.syrax.sk.Itemmanager;


public class cmd_build implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 0){
					if(Main.build.contains(p)){
						p.sendMessage(Main.pre+"§cDu bist nun nicht mehr im Build-Modus!");
						Main.build.remove(p);
						p.setGameMode(GameMode.ADVENTURE);
						if(Main.kit == Kit.Ender){
							p.getInventory().clear();
							p.getInventory().setItem(0, Itemmanager.createEnchantItem1(Material.BLAZE_ROD, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
							p.getInventory().setItem(1, Itemmanager.createItem(Material.ENDER_PEARL, 3, 0, "§8» §cEnderPearl"));
						}else if (Main.kit == Kit.Bogen){
							p.getInventory().clear();
							p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
						}else if (Main.kit == Kit.Schnee){
							p.getInventory().clear();
							p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
							p.getInventory().setItem(1, Itemmanager.createItem(Material.SNOW_BALL, 4, 0, "§8» §fSchnee Ball"));
						}else if (Main.kit == Kit.Normal){
							p.getInventory().clear();
							p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
							p.getInventory().setItem(1, Itemmanager.createUnbreakItem(Material.FISHING_ROD, 1, 0, "§8» §cEnterhaken")); 
						}
					}else{
						
						p.sendMessage(Main.pre+"§cDu bist nun im Build-Modus!");
						Main.build.add(p);
						p.setGameMode(GameMode.CREATIVE);
						p.getInventory().clear();
					}
				}else{
					p.sendMessage(Main.pre+"§c/build");
				}
			}else{
				p.sendMessage(Main.pre+"§cDir fehlen Rechte...");
			}
		}
		return false;
	}

}
