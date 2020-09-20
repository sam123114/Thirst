package fr.fondationacteon.thirst;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.fondationacteon.thirst.commands.ThirstCommand;
import fr.fondationacteon.thirst.handlers.BarUpdateHandler;
import fr.fondationacteon.thirst.handlers.EffectHandler;
import fr.fondationacteon.thirst.handlers.MiscHandler;
import fr.fondationacteon.thirst.listeners.ItemConsumeEvents;
import fr.fondationacteon.thirst.listeners.JoinEvents;

public class Main extends JavaPlugin{
	private static Main instance;
	public Database db;
	
	/*
	 * Permissions:
	 * - thirst.ignore
	 * - thirst.reload
	 * - thirst.refill
	 * - thirst.hide
	 */
	
	@Override
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		db = new Database(getDataFolder());
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BarUpdateHandler(), 1, getConfig().getInt("display.refresh-rate"));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new MiscHandler(), 1, getConfig().getInt("display.refresh-rate"));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new EffectHandler(), 1, 20);
		
		Bukkit.getPluginManager().registerEvents(new JoinEvents(), this);
		Bukkit.getPluginManager().registerEvents(new ItemConsumeEvents(), this);
		
		getCommand("thirst").setExecutor(new ThirstCommand());
	}
	
	public static Main getInstance() {
		return instance;
	}
}
