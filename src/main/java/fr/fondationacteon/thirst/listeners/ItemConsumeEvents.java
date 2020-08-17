package fr.fondationacteon.thirst.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import fr.fondationacteon.thirst.Main;

public class ItemConsumeEvents implements Listener{
	
	@EventHandler
	public void onDrink(PlayerItemConsumeEvent e) {
		ItemStack item = e.getItem();
		Player p = e.getPlayer();
		if(!(item.getItemMeta() instanceof PotionMeta)) {
			return;
		}
		
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		PotionData pd = pm.getBasePotionData();
		
		if(item.getType().equals(Material.POTION) && pd.getType().equals(PotionType.WATER)) {
			File playerFile = Main.getInstance().db.getPlayerData(p);
			FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
			int thirst = data.getInt("thirst-level");
			int value = Main.getInstance().getConfig().getInt("hydration.value") / 10;
			if((thirst + value) > 10) {
				data.set("thirst-level", 10);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 60, 1));
			} else {
				data.set("thirst-level", thirst + value);
			}
			data.set("last-hydration", new Date());
			try {
				data.save(playerFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
