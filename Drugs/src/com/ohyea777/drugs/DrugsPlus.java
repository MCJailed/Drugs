package com.ohyea777.drugs;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.drugs.command.DrugCMD;
import com.ohyea777.drugs.listener.PlayerListener;
import com.ohyea777.drugs.listener.UpdateListener;

public class DrugsPlus extends JavaPlugin{

	public static DrugsPlus instance;
	private Logger log = Logger.getLogger("Minecrat");
	
	@Override
	public void onEnable(){
		instance = this;
		PluginDescriptionFile pdf = this.getDescription();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new UpdateListener(), this);
		Bukkit.getPluginCommand("drugs").setExecutor(new DrugCMD(this));
		
		log.info(String.format("[%s] Version: %s - Has Been Enabled", pdf.getName(), pdf.getVersion()));
		
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		this.reloadConfig();
	}
	
	@Override
	public void onDisable(){
		instance = this;
		PluginDescriptionFile pdf = this.getDescription();
		
		log.info(String.format("[%s] Version: %s - Has Been Disabled", pdf.getName(), pdf.getVersion()));
	}
	
}
