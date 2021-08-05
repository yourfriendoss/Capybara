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
package friend.capybara.command;

import java.util.Arrays;
import java.util.List;

import friend.capybara.command.commands.CmdBind;
import friend.capybara.command.commands.CmdCI;
import friend.capybara.command.commands.CmdCleanChat;
import friend.capybara.command.commands.CmdCredits;
import friend.capybara.command.commands.CmdDrawn;
import friend.capybara.command.commands.CmdDupe;
import friend.capybara.command.commands.CmdEnchant;
import friend.capybara.command.commands.CmdEntityStats;
import friend.capybara.command.commands.CmdFakeText;
import friend.capybara.command.commands.CmdFriends;
import friend.capybara.command.commands.CmdGamemode;
import friend.capybara.command.commands.CmdGive;
import friend.capybara.command.commands.CmdGuiReset;
import friend.capybara.command.commands.CmdHelp;
import friend.capybara.command.commands.CmdLogin;
import friend.capybara.command.commands.CmdNBT;
import friend.capybara.command.commands.CmdNotebot;
import friend.capybara.command.commands.CmdNuker;
import friend.capybara.command.commands.CmdOpenFolder;
import friend.capybara.command.commands.CmdPeek;
import friend.capybara.command.commands.CmdPrefix;
import friend.capybara.command.commands.CmdRbook;
import friend.capybara.command.commands.CmdRename;
import friend.capybara.command.commands.CmdRpc;
import friend.capybara.command.commands.CmdSearch;
import friend.capybara.command.commands.CmdSetting;
import friend.capybara.command.commands.CmdSkull;
import friend.capybara.command.commands.CmdToggle;
import friend.capybara.command.commands.CmdXray;
import friend.capybara.utils.CapyLogger;

public class CommandManager {

	private static List<Command> commands = Arrays.asList(new CmdBind(), new CmdCI(), new CmdOpenFolder(),
			new CmdCleanChat(), new CmdCredits(), new CmdDrawn(), new CmdDupe(), new CmdEnchant(),
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
					CapyLogger.errorMessage("Invalid Syntax!");
					CapyLogger.infoMessage(c.getSyntax());
				}
				return;
			}
		}
		CapyLogger.errorMessage("Command Not Found, Try \"" + Command.PREFIX + "help\"");
	}
}