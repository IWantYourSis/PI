package de.syrax.sk;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import de.syrax.main.Kit;
import de.syrax.main.Main;

public class KitCounter {

	public static int cd = 300;
	
	public static void start(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable(){

			@Override
			public void run() {
				if(cd >= 0){
					if(cd == 300){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b5 §7minuten!");
						
					}else if(cd == 240){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b4 §7minuten!");
						
					}else if(cd == 180){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b3 §7minuten!");
						
					}else if(cd == 120){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b2 §7minuten!");
						
					}else if(cd == 60){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b1 §7minute!");
						
					}else if(cd == 60){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b1 §7minute!");
						
					}else if(cd == 30){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b30 §7sekunden!");
						
					}else if(cd == 20){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b20 §7sekunden!");
						
					}else if(cd == 10){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b10 §7sekunden!");
						
					}else if(cd == 5){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b5 §7sekunden!");
						
					}else if(cd == 4){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b4 §7sekunden!");
						
					}else if(cd == 3){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b3 §7sekunden!");
						
					}else if(cd == 2){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b2 §7sekunden!");
						
					}else if(cd == 1){
						Bukkit.broadcastMessage(Main.pre + "Kit wechsel in §b1 §7sekunden!");
					}else if(cd == 0){
						cd = 300;
						if(Main.kit == Kit.Normal){
							Main.kit = Kit.Ender;
							Main.kit = Kit.Bogen;
							Main.kit = Kit.Schnee;
							
							for(Player all : Bukkit.getOnlinePlayers()){
							Main.getScoreboardManager().sendPacketsScoreboard(all);
							}
							
							Bukkit.broadcastMessage(Main.pre + "Kit wechsel!");
						}else if(Main.kit == Kit.Ender){
							Main.kit = Kit.Normal;
							Main.kit = Kit.Bogen;
							Main.kit = Kit.Schnee;
							
							for(Player all : Bukkit.getOnlinePlayers()){
							Main.getScoreboardManager().sendPacketsScoreboard(all);
							}
							
							Bukkit.broadcastMessage(Main.pre + "Kit wechsel!");
						}else if(Main.kit == Kit.Bogen){
							Main.kit = Kit.Normal;
							Main.kit = Kit.Ender;
							Main.kit = Kit.Schnee;
							
							for(Player all : Bukkit.getOnlinePlayers()){
							Main.getScoreboardManager().sendPacketsScoreboard(all);
							}
							
							Bukkit.broadcastMessage(Main.pre + "Kit wechsel!");
						}else if(Main.kit == Kit.Schnee){
							Main.kit = Kit.Normal;
							Main.kit = Kit.Ender;
							Main.kit = Kit.Bogen;
							
							for(Player all : Bukkit.getOnlinePlayers()){
							Main.getScoreboardManager().sendPacketsScoreboard(all);
							}
							Bukkit.broadcastMessage(Main.pre + "Kit wechsel!");
						}
						for(Player all : Bukkit.getOnlinePlayers()){
							if(Main.kit == Kit.Ender){
								all.getInventory().clear();
								all.getInventory().setItem(0, Itemmanager.createEnchantItem1(Material.BLAZE_ROD, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
								all.getInventory().setItem(1, Itemmanager.createItem(Material.ENDER_PEARL, 3, 0, "§8» §cEnderPearl"));
								Main.getScoreboardManager().sendPacketsScoreboard(all);
							}else if (Main.kit == Kit.Bogen){
								all.getInventory().clear();
								Main.getScoreboardManager().sendPacketsScoreboard(all);
								all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
							}else if (Main.kit == Kit.Schnee){
								all.getInventory().clear();
								Main.getScoreboardManager().sendPacketsScoreboard(all);
								all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
								all.getInventory().setItem(1, Itemmanager.createItem(Material.SNOW_BALL, 4, 0, "§8» §fSchnee Ball"));
							}else if (Main.kit == Kit.Normal){
								all.getInventory().clear();
								Main.getScoreboardManager().sendPacketsScoreboard(all);
								all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
								all.getInventory().setItem(1, Itemmanager.createUnbreakItem(Material.FISHING_ROD, 1, 0, "§8» §cEnterhaken")); 
							}
						}
					}
					for(Player all : Bukkit.getOnlinePlayers()){
						ActionBar.sendActionBar(all, "§8» §fKit wechsel in §e§l" + cd + " §fsekunden §8« x  §8» §fMap wechsel in §e§l" + MapCounter.cd + " §fsekunden§c §8«");
					}
					cd--;
				}
			}
			
		}, 20, 20);
	}
}
