/*
 */
package com.github.quikmod.quiklib.gui;

import com.github.quikmod.quikcore.core.QuikCore;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * 
 */
@SideOnly(Side.CLIENT)
public final class QuikGuiComponentRenderer {
	
    public static final Color NORMAL_TEXT = new Color(14737632);
    public static final Color ACTIVE_TEXT = new Color(16777120);
    public static final Color DISABLED_TEXT = new Color(10526880);
    public static final ResourceLocation WIDGETS = new ResourceLocation("agricraft:textures/gui/widgets.png");

	public static void renderIconComponent(QuikGuiWrapper gui, QuikGuiComponent<ResourceLocation> component) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(component.getComponent());
		QuikGuiWrapper.drawModalRectWithCustomSizedTexture(
				0,
				0,
				0,
				0,
				component.getBounds().width,
				component.getBounds().height,
				component.getBounds().width,
				component.getBounds().height
		);
	}
    
    public static void renderComponentProgressBar(QuikGuiWrapper gui, QuikGuiComponent<Supplier<Integer>> component) {
        final int width = component.getBounds().width;
        final int height = component.getBounds().height;
        final double progress = component.getComponent().get();
        GuiUtils.drawContinuousTexturedBox(WIDGETS, 0, 0, 100, 25, width, height, 16, 16, 2, 0);
        GuiUtils.drawContinuousTexturedBox(WIDGETS, 0, 0, 125, 25, (int) ((width * progress) / 100), height, 16, 16, 2, 0);
    }
    
    public static void renderComponentButton(QuikGuiWrapper gui, QuikGuiComponent<String> component) {
        GuiUtils.drawContinuousTexturedBox(WIDGETS, 0, 0, 0, 46 + getButtonNumber(component) * 20, component.getBounds().width, component.getBounds().height, 200, 20, 2, 3, 2, 2, 0);
        QuikGuiComponentRenderer.renderComponentText(gui, component, getTextColor(component), true);
    }
    
    public static int getButtonNumber(QuikGuiComponent component) {
        if (!component.isEnabled()) { 
            return 0;
        } else if (!component.isHovered()) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public static Color getTextColor(QuikGuiComponent component) {
        if (!component.isEnabled()) { 
            return DISABLED_TEXT;
        } else if (!component.isHovered()) {
            return NORMAL_TEXT;
        } else {
            return ACTIVE_TEXT;
        }
    }

	public static void renderComponentStackFramed(QuikGuiWrapper gui, QuikGuiComponent<ItemStack> component) {
		renderStackFrame(gui, component);
		renderComponentStack(gui, component);
	}

	public static void renderComponentStack(QuikGuiWrapper gui, QuikGuiComponent<ItemStack> component) {
		gui.getItemRender().renderItemAndEffectIntoGUI(component.getComponent(), 0, 0);
        GlStateManager.enableAlpha();
	}

	public static void renderStackFrame(QuikGuiWrapper gui, QuikGuiComponent<ItemStack> component) {
		GuiUtils.drawContinuousTexturedBox(WIDGETS, 0, 0, 142, 25, 18, 18, 18, 18, 2, 0);
	}
	
	public static void renderComponentText(QuikGuiWrapper gui, QuikGuiComponent<String> component) {
		      renderComponentText(gui, component, Color.BLACK, false);
	}
    
    public static void renderComponentText(QuikGuiWrapper gui, QuikGuiComponent<String> component, Color color, boolean shadow) {
		final FontRenderer fontRenderer = gui.getFontRenderer();
        
		String text = QuikCore.getTranslator().translate(component.getComponent());
		text = splitInLines(gui.getFontRenderer(), text, 95, component.getScale());
		List<String> write = getLinesFromData(text);
        float vertOffset = component.isCenteredVertically() ? (component.getBounds().height - write.size() * fontRenderer.FONT_HEIGHT) / 2f : 0;
		for (int i = 0; i < write.size(); i++) {
			String line = write.get(i);
			float xOffset = component.isCenteredHorizontally() ? (component.getBounds().width - fontRenderer.getStringWidth(line)) / 2f : 0;
			float yOffset = vertOffset + i * fontRenderer.FONT_HEIGHT;
			fontRenderer.drawString(line, xOffset, yOffset, color.hashCode(), shadow);
		}
	}
	
	/**
     * Utility method: splits the string in different lines so it will fit on the page.
     *
     * @param fontRendererObj the font renderer to check against.
     * @param input the line to split up.
     * @param maxWidth the maximum allowable width of the line before being wrapped.
     * @param scale the scale of the text to the width.
     * @return the string split up into lines by the '\n' character.
     */
    public static String splitInLines(FontRenderer fontRendererObj, String input, double maxWidth, double scale) {
        maxWidth = maxWidth / scale;
        String notProcessed = input;
        String output = "";
        while (fontRendererObj.getStringWidth(notProcessed) > maxWidth) {
            int index = 0;
            if (notProcessed != null && !notProcessed.equals("")) {
                //find the first index at which the string exceeds the size limit
                while (notProcessed.length() - 1 > index && fontRendererObj.getStringWidth(notProcessed.substring(0, index)) < maxWidth) {
                    index = (index + 1) < notProcessed.length() ? index + 1 : index;
                }
                //go back to the first space to cut the string in two lines
                while (index>0 && notProcessed.charAt(index) != ' ') {
                    index--;
                }
                //update the data for the next iteration
                output = output.equals("") ? output : output + '\n';
                output = output + notProcessed.substring(0, index);
                notProcessed = notProcessed.length() > index + 1 ? notProcessed.substring(index + 1) : notProcessed;
            }
        }
        return output + '\n' + notProcessed;
    }

    //turns the raw data string into an array (each array element is a line from the string)
    public static List<String> getLinesFromData(String input) {
        int count = 0;
        String unprocessed = input;
        for (int i=0;i<unprocessed.length();i++) {
            if (unprocessed.charAt(i) == '\n') {
                count++;
            }
        }
        List<String> data = new ArrayList<>(count + 1); // There will be no more than count plus + lines, thereby preventing resizing.
        if (unprocessed.length()>0) {
            for (int i=0;i<count;i++) {
                String line = (unprocessed.substring(0,unprocessed.indexOf('\n'))).trim();
                if (line.length() > 0 && line.charAt(0) != '#') {
                    data.add(line); // The string line was already trimmed in its declaration.
                }
                unprocessed = unprocessed.substring(unprocessed.indexOf('\n')+1);
            }
        }
        
        unprocessed = unprocessed.trim();
        
        if (unprocessed.length()>0 && unprocessed.charAt(0)!='#') {
            data.add(unprocessed);
        }
        return data;
    }

}
