package com.ohyea777.drugs.listener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ohyea777.drugs.DrugsPlus;

public class UpdateListener implements Listener{

	DrugsPlus plugin = DrugsPlus.instance;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws MalformedURLException{
		if (event.getPlayer().hasPermission("drugs.update")){

			if (Bukkit.getPluginManager().getPlugin("DrugsPlus").getConfig().getBoolean("Options.Check_For_Updates")){

				try {
					URL updateFile = new URL("https://dl.dropbox.com/s/g9j0m27fpn2ly8u/Update.yml");
					YamlConfiguration updates = YamlConfiguration.loadConfiguration(updateFile.openStream());
					double remVer = updates.getDouble("version");
					Boolean critical = updates.getBoolean("critical");
					if (remVer > Double.valueOf(Bukkit.getPluginManager().getPlugin("DrugsPlus").getDescription().getVersion())){
						event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fA New Update is Available!", Bukkit.getPluginManager().getPlugin("DrugsPlus").getConfig().get("Options.Prefix"))));
						event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fVisit: &3http://dev.bukkit.org/server-mods/Drugs&f!", Bukkit.getPluginManager().getPlugin("DrugsPlus").getConfig().get("Options.Prefix"))));
						if (critical){
							event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &3&lThis Is a Very Important Update!", Bukkit.getPluginManager().getPlugin("DrugsPlus").getConfig().get("Options.Prefix"))));
						}
					}

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}