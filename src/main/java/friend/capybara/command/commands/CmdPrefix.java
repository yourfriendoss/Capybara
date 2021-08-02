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
package friend.capybara.command.commands;

import friend.capybara.command.Command;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileManager;

public class CmdPrefix extends Command {

    @Override
    public String getAlias() {
        return "prefix";
    }

    @Override
    public String getDescription() {
        return "Sets the command prefix";
    }

    @Override
    public String getSyntax() {
        return "prefix [Char]";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args[0].isEmpty()) {
            CapyLogger.errorMessage("Prefix Cannot Be Empty");
            return;
        }

        FileManager.createEmptyFile("prefix.txt");
        FileManager.appendFile(args[0], "prefix.txt");
        PREFIX = args[0];
        CapyLogger.infoMessage("Set Prefix To: \"" + args[0] + "\"");
    }

}
