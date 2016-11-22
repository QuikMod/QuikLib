/*
 */
package com.github.quikmod.quiklib.core;

import com.github.quikmod.quikcore.lang.QuikTranslationAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;

/**
 *
 * 
 */
public class ModTranslatorAdapter implements QuikTranslationAdapter {

	@Override
	public String translateKey(String key) {
		return I18n.translateToLocal(key);
	}

	@Override
	public String getLocale() {
		return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
	}

}
