/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bleach.hack.command;

import java.util.Arrays;
import java.util.List;

import bleach.hack.command.commands.CmdBind;
import bleach.hack.command.commands.CmdCI;
import bleach.hack.command.commands.CmdCleanChat;
import bleach.hack.command.commands.CmdCredits;
import bleach.hack.command.commands.CmdCustomChat;
import bleach.hack.command.commands.CmdDrawn;
import bleach.hack.command.commands.CmdDupe;
import bleach.hack.command.commands.CmdEnchant;
import bleach.hack.command.commands.CmdEntityStats;
import bleach.hack.command.commands.CmdFakeText;
import bleach.hack.command.commands.CmdFriends;
import bleach.hack.command.commands.CmdGamemode;
import bleach.hack.command.commands.CmdGive;
import bleach.hack.command.commands.CmdGuiReset;
import bleach.hack.command.commands.CmdHelp;
import bleach.hack.command.commands.CmdLogin;
import bleach.hack.command.commands.CmdNBT;
import bleach.hack.command.commands.CmdNotebot;
import bleach.hack.command.commands.CmdNuker;
import bleach.hack.command.commands.CmdOpenFolder;
import bleach.hack.command.commands.CmdPeek;
import bleach.hack.command.commands.CmdPrefix;
import bleach.hack.command.commands.CmdRbook;
import bleach.hack.command.commands.CmdRename;
import bleach.hack.command.commands.CmdRpc;
import bleach.hack.command.commands.CmdSearch;
import bleach.hack.command.commands.CmdSetting;
import bleach.hack.command.commands.CmdSkull;
import bleach.hack.command.commands.CmdToggle;
import bleach.hack.command.commands.CmdXray;
import bleach.hack.utils.BleachLogger;

public class CommandManager {

	private static List<Command> commands = Arrays.asList(new CmdBind(), new CmdCI(), new CmdOpenFolder(),
			new CmdCleanChat(), new CmdCredits(), new CmdCustomChat(), new CmdDrawn(), new CmdDupe(), new CmdEnchant(),
			new CmdEntityStats(), new CmdFakeText(), new CmdFriends(), new CmdGamemode(), new CmdGive(),
			new CmdGuiReset(), new CmdHelp(), new CmdLogin(), new CmdNBT(), new CmdNotebot(), new CmdNuker(),
			new CmdPeek(), new CmdPrefix(), new CmdRbook(), new CmdRename(), new CmdRpc(), new CmdSearch(),
			new CmdSetting(), new CmdSkull(), new CmdToggle(), new CmdXray());

	public static List<Command> getCommands() {
		return commands;
	}

	public static void callCommand(String input) {
		String[] split = input.split(" ", -1);
		System.out.println(Arrays.asList(split));
		String command = split[0];
		String args = input.substring(command.length()).trim();
		for (Command c : getCommands()) {
			if (c.getAlias().equalsIgnoreCase(command)) {
				try {
					c.onCommand(command, args.split(" "));
				} catch (Exception e) {
					e.printStackTrace();
					BleachLogger.errorMessage("Invalid Syntax!");
					BleachLogger.infoMessage(c.getSyntax());
				}
				return;
			}
		}
		BleachLogger.errorMessage("Command Not Found, Try \"" + Command.PREFIX + "help\"");
	}
}