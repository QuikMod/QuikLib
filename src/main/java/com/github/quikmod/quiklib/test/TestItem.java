/*
 */
package com.github.quikmod.quiklib.test;

import com.github.quikmod.quikcore.reflection.Quik;
import com.github.quikmod.quiklib.registry.QuikItem;
import net.minecraft.item.Item;

/**
 *
 * @author Ryan
 */
@Quik
@QuikItem(mod = "quikmod", name = "test_item")
public class TestItem extends Item {

	public TestItem() {
	}
	
}
