package fr.fondationacteon.thirst.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.fondationacteon.thirst.Main;

public class JoinEvents implements Listener{

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		File playerFile = main.db.getPlayerData(e.getPlayer());
		FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
		data.set("last-hydration", new Date());
		try {
			data.save(playerFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
