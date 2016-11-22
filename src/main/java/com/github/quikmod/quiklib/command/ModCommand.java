/*
 */
package com.github.quikmod.quiklib.command;

import com.github.quikmod.quikcore.command.QuikInvocationResult;
import com.github.quikmod.quikcore.core.QuikCore;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

/**
 *
 * @author Ryan
 */
public class ModCommand implements ICommand {

	@Override
	public String getCommandName() {
		return ".";
	}

	@Override
	public String getCommandUsage(ICommandSender ics) {
		return "/. <quikcommand> [... arguments]";
	}

	@Override
	public List<String> getCommandAliases() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void execute(MinecraftServer ms, ICommandSender ics, String[] strings) throws CommandException {
		QuikInvocationResult result = QuikCore.getCommands().invoke(recombine(strings));
		for (String e : result.getOutput()) {
			ics.addChatMessage(new TextComponentString(e));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer ms, ICommandSender ics) {
		// Whatever...
		return true;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer ms, ICommandSender ics, String[] strings, BlockPos bp) {
		return QuikCore
				.getCommands()
				.getPossibleCommandNamesFor(recombine(strings))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isUsernameIndex(String[] strings, int i) {
		return true;
	}

	@Override
	public int compareTo(ICommand o) {
		// So that this command is always first.
		return this.equals(o) ? 0 : -1;
	}

	public static String recombine(String... args) {
		StringBuilder sb = new StringBuilder();
		for (String e : args) {
			sb.append(e).append(" ");
		}
		return sb.toString();
	}

}
