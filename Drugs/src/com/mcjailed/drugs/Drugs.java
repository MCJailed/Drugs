package com.mcjailed.drugs;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Drugs extends JavaPlugin{

	private Logger log = Logger.getLogger("Minecraft");
	public static Permission perms = null;
	
	@Override
	public void onEnable(){
		
		log.info(String.format("[%s] Version: %s - Is Now Enabled!", getDescription().getName(), getDescription().getVersion()));
		
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		reloadConfig();
		getServer().getPluginManager().registerEvents(new DrugEvent(), this);
		getServer().getPluginManager().registerEvents(new UpdateListener(), this);
		
		if(!setupPermissions()){
			log.severe(String.format("[%s] Has Been Disabled Due to Lack of Vault's Permissions", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
	}
	
	@Override
	public void onDisable(){
		
		log.info(String.format("[%s] Version: %s - Is Now Disabled!", getDescription().getName(), getDescription().getVersion()));
		
	}
	
	public static boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("Drugs")){
			
			if (args.length == 0){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s To Reload the Plugin, Use: &3/drugs reload", getConfig().get("Options.Prefix"))));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s For Info About the Plugin, Use: &3/drugs about", getConfig().get("Options.Prefix"))));
			}
			else if (args.length == 1){
				if (args[0].equalsIgnoreCase("reload")){
					if (perms.has(sender, "drugs.reload")){
						reloadConfig();
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", getConfig().get("Options.Prefix"), getConfig().getString("Message.Reload"))));
					}
					else{
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", getConfig().getString("Options.Prefix"), getConfig().getString("Message.No_Perm"))));
					}
				}
				if (args[0].equalsIgnoreCase("about")){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fPlugin by &3OhYea777&f!", getConfig().get("Options.Prefix"))));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fVersion: &3%s&f!", getConfig().get("Options.Prefix"), getDescription().getVersion())));
				}
			}
		}
		return false;
		
	}
	
}
