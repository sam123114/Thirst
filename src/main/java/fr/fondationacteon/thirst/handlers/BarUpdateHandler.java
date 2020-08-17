package fr.fondationacteon.thirst.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.fondationacteon.thirst.TemperatureBar;

public class BarUpdateHandler implements Runnable {
	
	public void run() {
		execute();
	}
	
	private void execute() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			TemperatureBar.updateDisplayBar(p);
		}
	}

}
