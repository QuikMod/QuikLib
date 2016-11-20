/*
*/
package com.github.quikmod.quiklib;

import com.github.quikmod.quiklib.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/*
 * QuikMod Default Mod File.
 */
@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.MOD_VERSION,
		updateJSON = Reference.MOD_UPDATE_URL
)
public class QuikLibMod {

	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("QuickMod Initialized");
	}

}
