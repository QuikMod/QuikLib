/*
*/
package com.github.quikmod.quiklib;

import com.github.quikmod.quikcore.command.QuikCommand;
import com.github.quikmod.quikcore.config.QuikConfigurable;
import com.github.quikmod.quikcore.core.QuikCore;
import com.github.quikmod.quikcore.reflection.Quik;
import com.github.quikmod.quiklib.core.ModCommandAdaptor;
import com.github.quikmod.quiklib.core.ModConfigAdapter;
import com.github.quikmod.quiklib.core.ModLogAdapter;
import com.github.quikmod.quiklib.core.ModTranslatorAdapter;
import com.github.quikmod.quiklib.reference.Reference;
import com.github.quikmod.quiklib.registry.ModRegistryAdaptor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/*
 * QuikMod Default Mod File.
 */
@Quik
@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.MOD_VERSION,
		updateJSON = Reference.MOD_UPDATE_URL,
		guiFactory = "com.github.quikmod.quiklib.gui.GuiFactory"
)
public class QuikLibMod {
	
	@Mod.Instance
	private static QuikLibMod instance;
	
	private ModConfigAdapter adapter;

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		this.adapter = new ModConfigAdapter(event.getSuggestedConfigurationFile().toPath().getParent());
		QuikCore.init(
				new ModLogAdapter(),
				new ModTranslatorAdapter(),
				this.adapter
		);
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event) {
		ModRegistryAdaptor.registerBlocks();
		ModRegistryAdaptor.registerItems();
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new ModCommandAdaptor());
	}

	public static QuikLibMod getInstance() {
		return instance;
	}

	public ModConfigAdapter getConfigAdapter() {
		return adapter;
	}
	
	@QuikCommand(name = "test", info = "A test quikcommand.")
	public static String test() {
		return "Command Test Success!";
	}
	
	@QuikConfigurable(config = "QuikLib", key = "test", category = "Test", comment = "Test integer for the config.")
	public static int test = 0;

}
