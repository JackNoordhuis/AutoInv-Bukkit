package net.jacknoordhuis.autoinv.util.config;

import net.jacknoordhuis.autoinv.AutoInv;
import net.jacknoordhuis.autoinv.event.EventManager;
import net.jacknoordhuis.autoinv.event.handle.BlockBreakPickup;
import net.jacknoordhuis.autoinv.event.handle.EntityDeathPickup;
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

		if(data.getBoolean("general.events.player-death")) {
			// TODO: player death pickup handler
		}

		if(data.getBoolean("general.events.entity-death")) {
			manager.registerHandler(new EntityDeathPickup(manager));
		}

		if(data.getBoolean("general.events.entity-explosion")) {
			// TODO: entity explosion handler
		}

		if(data.getBoolean("general.events.inventory-full")) {
			// TODO: inventory full alert handler
		}
	}

}