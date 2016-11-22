/*
*/
package com.github.quikmod.quiklib;

import com.github.quikmod.quikcore.command.QuikCommand;
import com.github.quikmod.quikcore.command.QuikCommandManager;
import com.github.quikmod.quikcore.conversion.QuikConverterManager;
import com.github.quikmod.quikcore.core.QuikCore;
import com.github.quikmod.quikcore.reflection.Quik;
import com.github.quikmod.quikcore.reflection.QuikReflector;
import com.github.quikmod.quiklib.command.ModCommand;
import com.github.quikmod.quiklib.core.ModConfigAdapter;
import com.github.quikmod.quiklib.core.ModLogAdapter;
import com.github.quikmod.quiklib.core.ModTranslatorAdapter;
import com.github.quikmod.quiklib.reference.Reference;
import java.nio.file.Path;
import net.minecraftforge.common.config.Configuration;
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
		updateJSON = Reference.MOD_UPDATE_URL
)
public class QuikLibMod {

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		QuikCore.init(
				new ModLogAdapter(),
				new ModTranslatorAdapter(),
				new QuikConverterManager(),
				new QuikCommandManager(),
				new ModConfigAdapter(config),
				new QuikReflector()
		);
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new ModCommand());
	}
	
	@QuikCommand(name = "test", info = "A test quikcommand.")
	public static String test() {
		return "Command Test Success!";
	}

}
