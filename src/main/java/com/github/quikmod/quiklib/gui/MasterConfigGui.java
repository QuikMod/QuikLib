package com.github.quikmod.quiklib.gui;

import com.github.quikmod.quikcore.core.QuikCore;
import com.github.quikmod.quiklib.QuikLibMod;
import com.github.quikmod.quiklib.core.ModConfigAdapter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class MasterConfigGui extends GuiConfig {

	public MasterConfigGui(GuiScreen guiScreen) {
		super(guiScreen, getConfigs(), "quiklib", false, false, "Quik Configurations");
	}
	
	private static List<IConfigElement> getConfigs() {
		return QuikCore
				.getConfig()
				.getConfigs()
				.stream()
				.map(config -> getConfig(QuikLibMod.getInstance().getConfigAdapter(), config))
				.collect(Collectors.toList());
	}
	
	private static IConfigElement getConfig(ModConfigAdapter adaptor, String config) {
		return new DummyConfigElement.DummyCategoryElement(config, config, getConfigElements(adaptor, config));
	}

	private static List<IConfigElement> getConfigElements(ModConfigAdapter adapter, String config) {
		return QuikCore
				.getConfig()
				.getConfigCategories(config)
				.stream()
				.map(category -> getCategoryElement(adapter, config, category))
				.collect(Collectors.toList());
	}
	
	private static IConfigElement getCategoryElement(ModConfigAdapter adaptor, String config, String category) {
		return new DummyConfigElement.DummyCategoryElement(category, category, getCategoryElements(adaptor, config, category));
	}

	private static List<IConfigElement> getCategoryElements(ModConfigAdapter adaptor, String config, String category) {
		return new ConfigElement(adaptor.fetch(config).getCategory(category)).getChildElements();
	}

}
