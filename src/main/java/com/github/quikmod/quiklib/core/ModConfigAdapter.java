/*
 */
package com.github.quikmod.quiklib.core;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.github.quikmod.quikcore.config.QuikConfigAdapter;
import com.github.quikmod.quikcore.core.QuikCore;

/**
 *
 * 
 */
public class ModConfigAdapter implements QuikConfigAdapter {
	
	private final Configuration config;

	public ModConfigAdapter(Configuration config) {
		this.config = config;
		this.config.load();
	}

	@Override
	public void save() {
		this.config.save();
	}

	@Override
	public boolean getBoolean(String config, String name, String category, boolean defaultValue, String comment) {
		return this.config.getBoolean(name, category, defaultValue, comment);
	}

	@Override
	public int getInt(String config, String name, String category, int defaultValue, int minValue, int maxValue, String comment) {
		return this.config.getInt(name, category, defaultValue, minValue, maxValue, comment);
	}

	@Override
	public float getFloat(String config, String name, String category, float defaultValue, float minValue, float maxValue, String comment) {
		return this.config.getFloat(name, category, defaultValue, minValue, maxValue, comment);
	}

	@Override
	public double getDouble(String config, String name, String category, double defaultValue, double minValue, double maxValue, String comment) {
		return this.config.getFloat(name, category, (float)defaultValue, (float)minValue, (float)maxValue, comment);
	}

	@Override
	public String getString(String config, String name, String category, String defaultValue, String comment) {
		return this.config.getString(name, category, defaultValue, comment);
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		QuikCore.getConfig().save();
		QuikCore.getConfig().load();
		QuikCore.getCoreLogger().debug("Configuration reloaded.");
	}
	
}
