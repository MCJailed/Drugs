package com.ohyea777.drugs.lang;

import org.bukkit.configuration.ConfigurationSection;

import com.ohyea777.drugs.DrugsPlus;

public class Message implements IMessage{

	DrugsPlus plugin;
	
	public Message(){
		plugin = DrugsPlus.instance;
	}
	
	@Override
	public String getReload() {
		return this.getConfigSection().getString("Reload");
	}

	@Override
	public String getNoPerm() {
		return this.getConfigSection().getString("No_Perm");
	}
	
	public String getPrefix(){
		return this.plugin.getConfig().getString("Options.Prefix");
	}
	
	@Override
	public ConfigurationSection getConfigSection() {
		return plugin.getConfig().getConfigurationSection("Message");
	}

}
