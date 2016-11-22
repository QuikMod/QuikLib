/*
 */
package com.github.quikmod.quiklib.core;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.github.quikmod.quikcore.config.QuikConfigAdapter;
import com.github.quikmod.quikcore.core.QuikCore;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 */
public class ModConfigAdapter implements QuikConfigAdapter {

	private final Path path;
	private final Map<String, Configuration> configs;

	public ModConfigAdapter(Path path) {
		this.path = path;
		this.configs = new HashMap<>();
	}

	public Configuration fetch(String name) {
		if (this.configs.containsKey(name)) {
			return this.configs.get(name);
		} else {
			Configuration config = new Configuration(this.path.resolve(name + ".cfg").toFile());
			config.load();
			this.configs.put(name, config);
			return config;
		}
	}

	@Override
	public void save() {
		this.configs.values().forEach(e -> e.save());
	}

	@Override
	public Set<String> getConfigs() {
		return this.configs.keySet();
	}

	@Override
	public Set<String> getConfigCategories(String config) {
		return this.fetch(config).getCategoryNames();
	}

	@Override
	public boolean getBoolean(String config, String category, String element, boolean defaultValue, String comment) {
		return this.fetch(config).getBoolean(element, category, defaultValue, comment);
	}

	@Override
	public int getInt(String config, String category, String element, int defaultValue, int minValue, int maxValue, String comment) {
		return this.fetch(config).getInt(element, category, defaultValue, minValue, maxValue, comment);
	}

	@Override
	public float getFloat(String config, String category, String element, float defaultValue, float minValue, float maxValue, String comment) {
		return this.fetch(config).getFloat(element, category, defaultValue, minValue, maxValue, comment);
	}

	@Override
	public double getDouble(String config, String category, String element, double defaultValue, double minValue, double maxValue, String comment) {
		return this.fetch(config).getFloat(element, category, (float) defaultValue, (float) minValue, (float) maxValue, comment);
	}

	@Override
	public String getString(String config, String category, String element, String defaultValue, String comment) {
		return this.fetch(config).getString(element, category, defaultValue, comment);
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		QuikCore.getConfig().save();
		QuikCore.getConfig().load();
		QuikCore.getCoreLogger().debug("Configuration reloaded.");
	}

}
