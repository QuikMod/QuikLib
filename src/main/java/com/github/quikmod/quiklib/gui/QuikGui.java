/*
 */
package com.github.quikmod.quiklib.gui;

import com.github.quikmod.quiklib.container.FakeContainer;
import java.util.List;
import net.minecraft.inventory.Container;

/*
 *
 */
public interface QuikGui<T extends Container> {
    
    // ========================================
    // Constants
    // ========================================
    final Container FAKE_CONTAINER = new FakeContainer();

    // ========================================
    // Getters
    // ========================================
    T getContainer();
    
    int getHeight();

    int getWidth();

    // ========================================
    // Hooks
    // ========================================
    default void onGuiInit(QuikGuiWrapper wrapper) {
    }

    default void onUpdateMouse(QuikGuiWrapper wrapper, List<String> tooltips, int relMouseX, int relMouseY) {
    }
    
    default void onMouseClicked(QuikGuiWrapper wrapper, int relMouseX, int relMouseY, int mouseButton) {
    }
    
    default void onMouseClickMove(QuikGuiWrapper wrapper, int relMouseX, int relMouseY, int mouseButton) {
    }
    
    default void onKeyTyped(QuikGuiWrapper wrapper, char character, int keycode) {
    }

    default void onRenderForeground(QuikGuiWrapper wrapper, List<String> tooltips, int relMouseX, int relMouseY) {
    }
    
    default void onRenderBackground(QuikGuiWrapper wrapper, float f, int relMouseX, int relMouseY) {
    }

}
