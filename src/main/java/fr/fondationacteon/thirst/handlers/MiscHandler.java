package fr.fondationacteon.thirst.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.fondationacteon.thirst.TemperatureBar;

public class MiscHandler implements Runnable{

	public void run() {
		execute();
	}
	
	public void execute() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			TemperatureBar.lookForUpdate(p);
		}
	}

}
