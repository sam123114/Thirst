package fr.fondationacteon.thirst;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TemperatureBar {
	
	public static String CHAR = Main.getInstance().getConfig().getString("displaybar.character-full");
	public static String CHAR_MIDFULL = Main.getInstance().getConfig().getString("displaybar.character-midfull");
	public static String CHAR_MID = Main.getInstance().getConfig().getString("displaybar.character-mid");
	public static String CHAR_MIDEMPTY = Main.getInstance().getConfig().getString("displaybar.character-midempty");
	public static String CHAR_EMPTY = Main.getInstance().getConfig().getString("displaybar.character-empty");
	
	private static ArrayList<UUID> ignoredPlayer = new ArrayList<UUID>();
	private static ArrayList<UUID> hiddenPlayer = new ArrayList<UUID>();
	
	public static void updateDisplayBar(Player p) {
		File playerFile = Main.getInstance().db.getPlayerData(p);
		FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
		if(!p.getGameMode().equals(GameMode.CREATIVE) && !p.getGameMode().equals(GameMode.SPECTATOR) && !hiddenPlayer.contains(p.getUniqueId())) {
			/*StringBuilder sb = new StringBuilder();
			int thirst = data.getInt("thirst-level");
			for(int i = 0; i != thirst; i++) {
				sb.append(CHAR);
			}
			for(int i = 10 - thirst; i != 0; i--) {
				sb.append(CHAR_EMPTY);
			}
			p.sendActionBar(sb.toString());*/
			
			int percent = data.getInt("thirst-level") * 10;
			if(percent <= 100 && percent > 75) {
				p.sendActionBar(CHAR);
			}
			else if(percent <= 75 && percent > 50) {
				p.sendActionBar(CHAR_MIDFULL);
			}
			else if(percent <= 50 && percent > 25) {
				p.sendActionBar(CHAR_MID);
			}
			else if(percent <= 25 && percent > 0) {
				p.sendActionBar(CHAR_MIDEMPTY);
			}
			else if(percent == 0) {
				p.sendActionBar(CHAR_EMPTY);
			}
		}
	}
	
	public static void lookForUpdate(Player p) {
		File playerFile = Main.getInstance().db.getPlayerData(p);
		FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
		Date d1 = data.getObject("last-hydration", Date.class);
		Date d2 = new Date();
		long seconds = (d2.getTime()-d1.getTime())/1000;
		if(p.getGameMode().equals(GameMode.CREATIVE) || ignoredPlayer.contains(p.getUniqueId())) {
			data.set("last-hydration", new Date());
			try {
				data.save(playerFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if(seconds >= Main.getInstance().getConfig().getInt("dehydration.rate")) {
			data.set("last-hydration", new Date());
			int thirst = data.getInt("thirst-level");
			int value = Main.getInstance().getConfig().getInt("dehydration.value") / 10;
			if((thirst - value) < 0) {
				data.set("thirst-level", 0);
			} else {
				data.set("thirst-level", thirst - value);
			}
			try {
				data.save(playerFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void toggleIgnore(Player p) {
		if(ignoredPlayer.contains(p.getUniqueId()))
			ignoredPlayer.remove(p.getUniqueId());
		else
			ignoredPlayer.add(p.getUniqueId());
	}
	
	public static boolean isIgnored(Player p) {
		if(ignoredPlayer.contains(p.getUniqueId()))
			return true;
		else 
			return false;
	}
	
	public static void toggleHidden(Player p) {
		if(hiddenPlayer.contains(p.getUniqueId()))
			hiddenPlayer.remove(p.getUniqueId());
		else
			hiddenPlayer.add(p.getUniqueId());
	}
	
	public static boolean isHidden(Player p) {
		if(hiddenPlayer.contains(p.getUniqueId()))
			return true;
		else 
			return false;
	}
	
}
