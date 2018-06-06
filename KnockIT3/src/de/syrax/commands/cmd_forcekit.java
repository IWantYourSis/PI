package de.syrax.commands;

import de.syrax.main.Kit;
import de.syrax.main.Main;
import de.syrax.sk.Itemmanager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class cmd_forcekit
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args)
  {
	  Player p = (Player) sender;
    if (p.isOp()) {
      if (args.length == 0) {
        sender.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
        sender.sendMessage("");
        sender.sendMessage("§8» §e/forcekit §7<§eStandart§7/§eEnterhaken§7/§eEnderman§7/§eSchnee§7>");
        sender.sendMessage("");
        sender.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
      }
      else if (args.length == 1) {
        if (args[0].equalsIgnoreCase("Schnee")) {
          if (Main.kit == Kit.Schnee)
            sender.sendMessage(Main.pre + "§7Das Kit §f§lSchnee §7ist derzeitig §aAktiviert§7.");
          else {
            for (Player all : Bukkit.getOnlinePlayers()) {
              de.syrax.sk.KitCounter.cd = 300;
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.sendMessage("");
              all.sendMessage("§8» " + "§l" + sender.getName() + " §7hat ein neues Kit gewählt§c!");
              all.sendMessage("         §8» §7Neues Kit §8» §f§lSchnee");
              all.sendMessage("");
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              Main.kit = Kit.Schnee;
              all.getInventory().clear();
              all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
              all.getInventory().setItem(1, Itemmanager.createItem(Material.SNOW_BALL, 4, 0, "§8» §fSchnee Ball"));
              Main.getScoreboardManager().sendPacketsScoreboard(all);
            }
          }
        }
        else if (args[0].equalsIgnoreCase("Enderman")) {
          if (Main.kit == Kit.Ender)
            sender.sendMessage(Main.pre + "§7Das Kit §5§lEnderman §7ist derzeitig §aAktiviert§7.");
          else {
            for (Player all : Bukkit.getOnlinePlayers()) {
              de.syrax.sk.KitCounter.cd = 300;
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.sendMessage("");
              all.sendMessage("§8» " + "§l" + sender.getName() + " §7hat ein neues Kit gewählt§c!");
              all.sendMessage("         §8» §7Neues Kit §8» §5§lEnderman");
              all.sendMessage("");
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.getInventory().clear();
              Main.kit = Kit.Ender;
              all.getInventory().setItem(0, Itemmanager.createEnchantItem1(Material.BLAZE_ROD, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
              all.getInventory().setItem(1, Itemmanager.createItem(Material.ENDER_PEARL, 3, 0, "§8» §cEnderPearl"));
              Main.getScoreboardManager().sendPacketsScoreboard(all);
            }
          }
        }
        else if (args[0].equalsIgnoreCase("Standart")) {
          if (Main.kit == Kit.Bogen)
            sender.sendMessage(Main.pre + "§7Das Kit §e§lStandart §7ist derzeitig §aAktiviert§7.");
          else {
            for (Player all : Bukkit.getOnlinePlayers()) {
              de.syrax.sk.KitCounter.cd = 300;
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.sendMessage("");
              all.sendMessage("§8» " + "§l" + sender.getName() + " §7hat ein neues Kit gewählt§c!");
              all.sendMessage("         §8» §7Neues Kit §8» §e§lStandart");
              all.sendMessage("");
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              Main.kit = Kit.Bogen;
              all.getInventory().clear();
              all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
              Main.getScoreboardManager().sendPacketsScoreboard(all);
            }
          }
        }
        else if (args[0].equalsIgnoreCase("Enterhaken"))
          if (Main.kit == Kit.Normal)
            sender.sendMessage(Main.pre + "§7Das Kit §c§lEnterhaken §7ist derzeitig §aAktiviert§7.");
          else
            for (Player all : Bukkit.getOnlinePlayers()) {
              de.syrax.sk.KitCounter.cd = 300;
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.sendMessage("");
              all.sendMessage("§8» " + "§l" + sender.getName() + " §7hat ein neues Kit gewählt§c!");
              all.sendMessage("         §8» §7Neues Kit §8» §c§lEnterhaken");
              all.sendMessage("");
              all.sendMessage("§8§m---------------- §e§lKnockIT §8§m----------------");
              all.getInventory().clear();
              Main.kit = Kit.Normal;
              all.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
              all.getInventory().setItem(1, Itemmanager.createUnbreakItem(Material.FISHING_ROD, 1, 0, "§8» §cEnterhaken"));
              Main.getScoreboardManager().sendPacketsScoreboard(all);
            }
      }
      else
      {
        sender.sendMessage(Main.pre + "§cDu hast keine Rechte dazu§4!");
      }
    }
    return false;
  }
}