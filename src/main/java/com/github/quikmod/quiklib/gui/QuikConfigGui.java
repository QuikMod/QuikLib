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
import net.minecraftforge.fml.common.Loader;

@SideOnly(Side.CLIENT)
public class QuikConfigGui extends GuiConfig {
	
	// Heavy Wizardry
	
	// Simply use this class as the main config class in the mod's GuiFactory, and QuikLib does the rest.

	public QuikConfigGui(GuiScreen guiScreen) {
		super(guiScreen, getConfigElements(), Loader.instance().activeModContainer().getModId(), false, false, Loader.instance().activeModContainer().getName() + " Config");
	}

	private static List<IConfigElement> getConfigElements() {
		final String modId = Loader.instance().activeModContainer().getModId();
		final ModConfigAdapter adapter = QuikLibMod.getInstance().getConfigAdapter();
		return QuikCore
				.getConfig()
				.getConfigCategories(modId)
				.stream()
				.map(category -> getCategoryElement(adapter, modId, category))
				.collect(Collectors.toList());
	}
	
	private static IConfigElement getCategoryElement(ModConfigAdapter adapter, String config, String category) {
		return new DummyConfigElement.DummyCategoryElement(category, category, getCategoryElements(adapter, config, category));
	}

	private static List<IConfigElement> getCategoryElements(ModConfigAdapter adapter, String config, String category) {
		return new ConfigElement(adapter.fetch(config).getCategory(category)).getChildElements();
	}

}
