package net.jacknoordhuis.autoinv.util.config;

import net.jacknoordhuis.autoinv.AutoInv;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Basic class to help manage configuration values
 */
public abstract class ConfigurationLoader {

	private AutoInv plugin;

	private String path;

	private File dataFile;

	private FileConfiguration data;

	public ConfigurationLoader(AutoInv plugin, String path) {
		this.plugin = plugin;
		this.path = path;

		this.loadData();
		this.onLoad(this.data);

	}

	public AutoInv getPlugin() {
		return this.plugin;
	}

	final public void loadData() {
		this.dataFile = new File(this.plugin.getDataFolder(), AutoInv.SETTINGS_CONFIG);
		this.data = YamlConfiguration.loadConfiguration(this.dataFile);
	}

	final public void saveData() {
		try {
			this.data.save(this.dataFile);
		}catch (IOException var2) {
			this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.dataFile, var2);
		}
	}

	final public void reloadData() {
		this.saveData();
		this.loadData();
	}

	/*
	 * Called when the config file is loaded into memory
	 */
	abstract protected void onLoad(FileConfiguration data);

}
