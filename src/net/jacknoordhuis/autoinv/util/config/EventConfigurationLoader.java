package net.jacknoordhuis.autoinv.util.config;

import net.jacknoordhuis.autoinv.AutoInv;
import net.jacknoordhuis.autoinv.event.EventManager;
import net.jacknoordhuis.autoinv.event.handle.BlockBreakPickup;
import org.bukkit.configuration.file.FileConfiguration;

public class EventConfigurationLoader extends ConfigurationLoader {

	public EventConfigurationLoader(AutoInv plugin, String path) {
		super(plugin, path);
	}

	public void onLoad(FileConfiguration data) {
		EventManager manager = this.getPlugin().getEventManager();

		if(data.getBoolean("general.events.block-break")) {
			manager.registerHandler(new BlockBreakPickup(manager));
		}
	}
}
