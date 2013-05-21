package com.ohyea777.drugs.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ohyea777.drugs.DrugsPlus;

public class DrugCMD implements CommandExecutor{

	DrugsPlus plugin;
	
	public DrugCMD(DrugsPlus plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("Drugs")){
			
			if (args.length == 0){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s To Reload the Plugin, Use: &3/drugs reload", DrugsPlus.instance.getConfig().get("Options.Prefix"))));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s For Info About the Plugin, Use: &3/drugs about", DrugsPlus.instance.getConfig().get("Options.Prefix"))));
			}
			else if (args.length == 1){
				if (args[0].equalsIgnoreCase("reload")){
					if (sender.hasPermission("drugs.reload")){
						DrugsPlus.instance.reloadConfig();
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", DrugsPlus.instance.getConfig().get("Options.Prefix"), DrugsPlus.instance.getConfig().getString("Message.Reload"))));
					}
					else{
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", DrugsPlus.instance.getConfig().getString("Options.Prefix"), DrugsPlus.instance.getConfig().getString("Message.No_Perm"))));
					}
				}
				if (args[0].equalsIgnoreCase("about")){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fPlugin by &3OhYea777&f!", DrugsPlus.instance.getConfig().get("Options.Prefix"))));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fVersion: &3%s&f!", DrugsPlus.instance.getConfig().get("Options.Prefix"), DrugsPlus.instance.getDescription().getVersion())));
				}
			}
		}
		return false;
		
	}

	
}
