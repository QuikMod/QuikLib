/*
 */
package com.github.quikmod.quiklib.test;

import com.github.quikmod.quikcore.reflection.Quik;
import com.github.quikmod.quiklib.registry.QuikBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 *
 * @author Ryan
 */
@Quik
@QuikBlock(mod="quiklib", name="test_block")
public class TestBlock extends Block {
	
	public TestBlock() {
		super(Material.AIR, MapColor.IRON);
	}
	
}
