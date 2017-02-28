/*
 */
package com.github.quikmod.quiklib.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuikGuiComponentBuilder<C> {

	private final C component;
	private final Rectangle bounds;
	private Rectangle uv = new Rectangle(0, 0, 16, 16);
	private double scale = 1;
	private boolean centeredHorizontally = false;
	private boolean centeredVertically = false;
	private boolean visible = true;
	private boolean enabled = true;
	private BiConsumer<QuikGuiComponent<C>, List<String>> tootipAdder = null;
	private BiFunction<QuikGuiComponent<C>, Point, Boolean> mouseClickAction = null;
	private BiConsumer<QuikGuiComponent<C>, Point> mouseEnterAction = null;
	private BiConsumer<QuikGuiComponent<C>, Point> mouseLeaveAction = null;
	private BiConsumer<QuikGuiWrapper, QuikGuiComponent<C>> renderAction = null;

	public QuikGuiComponentBuilder(C component, int x, int y, int width, int height) {
		this.component = component;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public QuikGuiComponentBuilder<C> setUV(int u, int v, int width, int height) {
		this.uv = new Rectangle(u, v, width, height);
		return this;
	}

	public QuikGuiComponentBuilder<C> setScale(double scale) {
		this.scale = scale;
		return this;
	}

	public QuikGuiComponentBuilder<C> setCenteredHorizontally(boolean centeredHorizontally) {
		this.centeredHorizontally = centeredHorizontally;
		return this;
	}
    
    public QuikGuiComponentBuilder<C> setCenteredVertically(boolean centeredVertically) {
		this.centeredVertically = centeredVertically;
		return this;
	}
	
	public QuikGuiComponentBuilder<C> setVisable(boolean visable) {
		this.visible = visable;
		return this;
	}
	
	public QuikGuiComponentBuilder<C> setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public QuikGuiComponentBuilder<C> setTootipAdder(BiConsumer<QuikGuiComponent<C>, List<String>> tootipAdder) {
		this.tootipAdder = tootipAdder;
		return this;
	}

	public QuikGuiComponentBuilder<C> setMouseClickAction(BiFunction<QuikGuiComponent<C>, Point, Boolean> mouseClickAction) {
		this.mouseClickAction = mouseClickAction;
		return this;
	}

	public QuikGuiComponentBuilder<C> setMouseEnterAction(BiConsumer<QuikGuiComponent<C>, Point> mouseEnterAction) {
		this.mouseEnterAction = mouseEnterAction;
		return this;
	}

	public QuikGuiComponentBuilder<C> setMouseLeaveAction(BiConsumer<QuikGuiComponent<C>, Point> mouseLeaveAction) {
		this.mouseLeaveAction = mouseLeaveAction;
		return this;
	}

	public QuikGuiComponentBuilder<C> setRenderAction(BiConsumer<QuikGuiWrapper, QuikGuiComponent<C>> renderAction) {
		this.renderAction = renderAction;
		return this;
	}

	public QuikGuiComponent<C> build() {
		return new QuikGuiComponent<>(component, bounds, uv, scale, centeredHorizontally, centeredVertically, visible, enabled, tootipAdder, mouseClickAction, mouseEnterAction, mouseLeaveAction, renderAction);
	}
	
}
