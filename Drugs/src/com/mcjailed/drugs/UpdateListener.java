package com.mcjailed.drugs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateListener implements Listener{

	Drugs plugin;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws MalformedURLException{
		if (Drugs.perms.has(event.getPlayer(), "drugs.update")){

			if (Bukkit.getPluginManager().getPlugin("Drugs").getConfig().getBoolean("Options.Check_For_Updates")){

				try {
					URL updateFile = new URL("https://dl.dropbox.com/s/g9j0m27fpn2ly8u/Update.yml");
					YamlConfiguration updates = YamlConfiguration.loadConfiguration(updateFile.openStream());
					double remVer = updates.getDouble("version");
					Boolean critical = updates.getBoolean("critical");
					if (remVer > Double.valueOf(Bukkit.getPluginManager().getPlugin("Drugs").getDescription().getVersion())){
						event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fA New Update is Available!", Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Options.Prefix"))));
						event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &fVisit: &3http://dev.bukkit.org/server-mods/Drugs&f!", Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Options.Prefix"))));
						if (critical){
							event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s &3This Is a Very Important Update!", Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Options.Prefix"))));
						}
					}

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}