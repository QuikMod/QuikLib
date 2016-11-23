/*
 */
package com.github.quikmod.quiklib.core;

import com.github.quikmod.quikcore.log.QuikLogAdapter;
import java.text.MessageFormat;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/**
 *
 * 
 */
public class ModLogAdapter implements QuikLogAdapter {

	public void log(Level logLevel, Object source, String format, Object... objects) {
		try {
			FMLLog.log(String.valueOf(source), logLevel, MessageFormat.format(format, objects));
		} catch (IllegalArgumentException ex) {
			// This is bad...
			FMLLog.log(String.valueOf(source), logLevel, format);
		}
	}

	@Override
	public void all(Object source, String format, Object... objects) {
		log(Level.ALL, source, format, objects);
	}

	@Override
	public void debug(Object source, String format, Object... objects) {
		log(Level.INFO, source, "[Debug]: " + format, objects);
	}

	@Override
	public void trace(Object source, Exception e) {
		debug(source, e.getLocalizedMessage());
	}

	@Override
	public void error(Object source, String format, Object... objects) {
		log(Level.ERROR, source, format, objects);
	}

	@Override
	public void info(Object source, String format, Object... objects) {
		log(Level.INFO, source, format, objects);
	}

	@Override
	public void warn(Object source, String format, Object... objects) {
		log(Level.WARN, source, format, objects);
	}

	@Override
	public void severe(Object source, String format, Object... objects) {
		log(Level.FATAL, source, format, objects);
	}

}
