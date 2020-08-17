package fr.fondationacteon.thirst;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Database {
	private static final String DATA_FOLDER_NAME = "playerData";
	
	private File dataFolder;
	
	public Database(File pluginFolder) {
		dataFolder = new File(pluginFolder + File.separator + DATA_FOLDER_NAME + File.separator);
		if(!dataFolder.exists()) {
			dataFolder.mkdir();
		}
	}
	
	public File getPlayerData(Player p) {
		File playerData = new File(dataFolder.getAbsolutePath() + File.separator + p.getUniqueId() + ".yml");
		if(!playerData.exists()) {
			createPlayerData(playerData, p);
		}
		return playerData;
	}
	
	private void createPlayerData(File playerData, Player p){
		FileConfiguration data = YamlConfiguration.loadConfiguration(playerData);
		data.set("name", p.getDisplayName());
		data.set("uuid", p.getUniqueId().toString());
		data.set("thirst-level", 10);
		data.set("last-hydration", new Date());
		try {
			data.save(playerData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
