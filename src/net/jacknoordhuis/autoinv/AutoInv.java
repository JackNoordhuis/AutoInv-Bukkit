package net.jacknoordhuis.autoinv;

import net.jacknoordhuis.autoinv.event.EventManager;
import net.jacknoordhuis.autoinv.util.config.EventConfigurationLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoInv extends JavaPlugin {

	private EventManager eventManager;

	private EventConfigurationLoader eventConfigLoader;

	public static final String SETTINGS_CONFIG = "Settings.yml";

	public void onEnable() {
		this.saveResource(AutoInv.SETTINGS_CONFIG, false);
		this.setEventManager();
		this.eventConfigLoader = new EventConfigurationLoader(this, this.getDataFolder() + AutoInv.SETTINGS_CONFIG);
	}

	public EventManager getEventManager() {
		return this.eventManager;
	}

	private void setEventManager() {
		if(this.eventManager == null) {
			this.eventManager = new EventManager(this);
		}
	}

}