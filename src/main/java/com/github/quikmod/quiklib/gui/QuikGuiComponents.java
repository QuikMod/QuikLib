/*
 */
package com.github.quikmod.quiklib.gui;

import java.awt.Point;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * 
 */
@SideOnly(Side.CLIENT)
public class QuikGuiComponents {

	public static QuikGuiComponent<String> getTextComponent(String string, int x, int y, double scale) {
		return new QuikGuiComponentBuilder<>(string, x, y, 0, 0)
				.setScale(scale)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentText)
				.build();
	}

	public static QuikGuiComponent<String> getTextComponent(String string, int x, int y, double scale, boolean centered) {
		return new QuikGuiComponentBuilder<>(string, x, y, 0, 0)
				.setScale(scale)
				.setCenteredHorizontally(centered)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentText)
				.build();
	}
    
    public static QuikGuiComponent<String> getButtonComponent(String string, int x, int y, int width, int height, BiFunction<QuikGuiComponent<String>, Point, Boolean> onClick) {
		return new QuikGuiComponentBuilder<>(string, x, y, width, height)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentButton)
                .setMouseClickAction(onClick)
                .setCenteredHorizontally(true)
                .setCenteredVertically(true)
				.build();
	}
    
    public static QuikGuiComponent<Supplier<Integer>> getProgressBarComponent(Supplier<Integer> progress, int x, int y, int width, int height) {
		return new QuikGuiComponentBuilder<>(progress, x, y, width, height)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentProgressBar)
				.build();
	}

	public static QuikGuiComponent<ItemStack> getStackComponent(ItemStack stack, int x, int y) {
		return new QuikGuiComponentBuilder<>(stack, x, y, 16, 16)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentStack)
				.setTootipAdder(QuikGuiComponents::addStackTooltip)
				.build();
	}

	public static QuikGuiComponent<ItemStack> getStackComponentFramed(ItemStack stack, int x, int y) {
		return new QuikGuiComponentBuilder<>(stack, x, y, 16, 16)
				.setRenderAction(QuikGuiComponentRenderer::renderComponentStackFramed)
				.setTootipAdder(QuikGuiComponents::addStackTooltip)
				.build();
	}

	public static QuikGuiComponent<ResourceLocation> getIconComponent(ResourceLocation icon, int x, int y, int u, int v) {
		return new QuikGuiComponentBuilder<>(icon, x, y, u, v)
				.setRenderAction(QuikGuiComponentRenderer::renderIconComponent)
				.build();
	}

	public static QuikGuiComponent<ResourceLocation> getIconComponent(ResourceLocation icon, int x, int y, int u, int v, String tooltip) {
		return new QuikGuiComponentBuilder<>(icon, x, y, u, v)
				.setRenderAction(QuikGuiComponentRenderer::renderIconComponent)
				.setTootipAdder((c, l) -> l.add(tooltip))
				.build();
	}

	public static void addStackTooltip(QuikGuiComponent<ItemStack> component, List<String> tooltip) {
		if (component.getComponent() != null) {
			tooltip.addAll(component.getComponent().getTooltip(Minecraft.getMinecraft().thePlayer, false));
		}
	}

}
