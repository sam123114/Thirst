package fr.fondationacteon.thirst.handlers;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.fondationacteon.thirst.Main;

public class EffectHandler implements Runnable{

	public void run() {
		execute();
	}
	
	private void execute() {
		FileConfiguration config = Main.getInstance().getConfig();
		for(String key : config.getConfigurationSection("effects").getKeys(false)) {
			int thirst = Integer.parseInt(key);
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.getGameMode().equals(GameMode.CREATIVE)) {
					return;
				}
				File playerFile = Main.getInstance().db.getPlayerData(p);
				FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
				if(data.getInt("thirst-level") * 10 == thirst) {
					@SuppressWarnings("unchecked")
					List<String> effects = (List<String>) config.getList("effects." + key);
					for(String s : effects) {
						String[] effect = s.split(":");
						p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect[0]), 100, Integer.parseInt(effect[1]) - 1));
					}
				}
			}
			
		}
	}

}
