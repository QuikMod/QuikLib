/*
 */
package com.github.quikmod.quiklib.registry;

import com.github.quikmod.quikcore.core.QuikCore;
import com.github.quikmod.quikcore.reflection.Quik;
import com.github.quikmod.quikcore.reflection.QuikRegister;
import com.github.quikmod.quikcore.util.ReflectionHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 *
 * @author Ryan
 */
@Quik
public class ModRegistryAdaptor {

	private static final Map<String, Class<? extends Block>> BLOCKS = new HashMap<>();
	private static final Map<String, Class<? extends Item>> ITEMS = new HashMap<>();

	@QuikRegister
	public static void registerElementClass(Class<?> clazz) {
		if (clazz.isAnnotationPresent(QuikBlock.class)) {
			registerBlockClass(clazz.getAnnotation(QuikBlock.class), clazz);
		} else if (clazz.isAnnotationPresent(QuikItem.class)) {
			registerItemClass(clazz.getAnnotation(QuikItem.class), clazz);
		}
	}

	private static void registerBlockClass(QuikBlock anno, Class<?> block) {
		if (Block.class.isAssignableFrom(block)) {
			BLOCKS.putIfAbsent(anno.mod() + ":" + anno.name(), (Class<? extends Block>) block);
		} else {
			QuikCore.getCoreLogger().error("A quikblock must be a subclass of Block!\n{0} is not a subclass of Item!", block.getName());
		}
	}
	
	private static void registerItemClass(QuikItem anno, Class<?> item) {
		if (Item.class.isAssignableFrom(item)) {
			ITEMS.putIfAbsent(anno.mod() + ":" + anno.name(), (Class<? extends Item>) item);
		} else {
			QuikCore.getCoreLogger().error("A quikitem must be a subclass of Item!\n{0} is not a subclass of Item!", item.getName());
		}
	}
	
	public static void registerBlocks() {
		QuikCore.getCoreLogger().info("Registering Blocks!");
		long start = System.currentTimeMillis();
		for (Map.Entry<String, Class<? extends Block>> entry : BLOCKS.entrySet()) {
			Optional<? extends Block> block = ReflectionHelper.attemptInstantiate(entry.getValue());
			if (block.isPresent()) {
				QuikCore.getCoreLogger().info("Registering @QuikBlock: \"{0}\"!", entry.getKey());
				GameRegistry.register(block.get().setRegistryName(entry.getKey()).setUnlocalizedName(entry.getKey()));
			}
		}
		long end = System.currentTimeMillis();
		QuikCore.getCoreLogger().info("Registered Blocks! ({0} ms)", end - start);
	}
	
	public static void registerItems() {
		QuikCore.getCoreLogger().info("Registering Items!");
		long start = System.currentTimeMillis();
		for (Map.Entry<String, Class<? extends Item>> entry : ITEMS.entrySet()) {
			Optional<? extends Item> item = ReflectionHelper.attemptInstantiate(entry.getValue());
			if (item.isPresent()) {
				QuikCore.getCoreLogger().info("Registering @QuikBlock: \"{0}\"!", entry.getKey());
				GameRegistry.register(item.get().setRegistryName(entry.getKey()).setUnlocalizedName(entry.getKey()));
			}
		}
		long end = System.currentTimeMillis();
		QuikCore.getCoreLogger().info("Registered Items! ({0} ms)", end - start);
	}

}
