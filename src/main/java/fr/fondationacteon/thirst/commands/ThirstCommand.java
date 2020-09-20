package fr.fondationacteon.thirst.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.fondationacteon.thirst.Main;
import fr.fondationacteon.thirst.TemperatureBar;

public class ThirstCommand implements CommandExecutor{

	private Main main = Main.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		
		if(args.length == 1) {
			
			if(args[0].equals("reload")) {
				
				if(!p.hasPermission("thirst.reload")) {
					noPermission(p);
					return false;
				}
				
				main.reloadConfig();
				
				TemperatureBar.ACTIONBAR_CHAR = main.getConfig().getString("display.action-bar.character-full").replace("&", "§");
				TemperatureBar.ACTIONBAR_CHAR_EMPTY = main.getConfig().getString("display.action-bar.character-empty").replace("&", "§");
				
				p.sendMessage("§aConfig reloaded");
				
			}else if(args[0].equals("ignore")) {
				
				if(!p.hasPermission("thirst.ignore")) {
					noPermission(p);
					return false;
				}
				
				TemperatureBar.toggleIgnore(p);
				
				if(TemperatureBar.isIgnored(p)) {
					p.sendMessage(main.getConfig().getString("messages.now-ignoring").replace("&", "§"));
				} else {
					p.sendMessage(main.getConfig().getString("messages.no-longer-ignoring").replace("&", "§"));
				}
				
			} else if(args[0].equals("refill")) {
				
				if(!p.hasPermission("thirst.refill")) {
					noPermission(p);
					return false;
				}
				
				File playerFile = Main.getInstance().db.getPlayerData(p);
				FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
				
				data.set("thirst-level", 10);
				try {
					data.save(playerFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.sendMessage(main.getConfig().getString("messages.thirst-refill").replace("&", "§"));
				
			} else if(args[0].equals("hide")) {
				
				if(!p.hasPermission("thirst.hide")) {
					noPermission(p);
					return false;
				}
				
				TemperatureBar.toggleHidden(p);
				
				if(TemperatureBar.isHidden(p)) {
					p.sendMessage(main.getConfig().getString("messages.now-hidden").replace("&", "§"));
				} else {
					p.sendMessage(main.getConfig().getString("messages.no-longer-hidden").replace("&", "§"));
				}
				
			} else {
				displayMenu(p);
			}
			
		} else {
			
			displayMenu(p);
			
		}
		
		return true;
	}
	
	private void displayMenu(Player p) {
		p.sendMessage("§8-=-=-=-=-[§aThirst§8]-=-=-=-=-");
		p.sendMessage("");
		p.sendMessage("§e /thirst §f- §7Command list");
		p.sendMessage("§e /thirst reload §f- §7Reload the config file");
		p.sendMessage("§e /thirst ignore §f- §7Toggle ignore thirst");
		p.sendMessage("§e /thirst refill §f- §7Refill the thirst");
		p.sendMessage("§e /thirst hide §f- §7Toggle hide thirst");
		p.sendMessage("");
		p.sendMessage("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}
	
	private void noPermission(Player p) {
		p.sendMessage(main.getConfig().getString("messages.permission").replace("&", "§"));
	}

}
