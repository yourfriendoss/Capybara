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

import java.util.List;

import friend.capybara.command.Command;
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileManager;

public class CmdDrawn extends Command {

    @Override
    public String getAlias() {
        return "drawn";
    }

    @Override
    public String getDescription() {
        return "Choose if the module is drawn or not";
    }

    @Override
    public String getSyntax() {
        return "drawn [Module] [true/false]";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        for (Module m : ModuleManager.getModules()) {
            if (args[0].equalsIgnoreCase(m.getName())) {
                if (args[1] == null) {
                    CapyLogger.errorMessage("Expected true or false.");
                    return;
                }
                if (Boolean.parseBoolean(args[1]) == Boolean.FALSE) {
                    FileManager.appendFile(args[0].toLowerCase(), "drawn.txt");
                } else if (Boolean.parseBoolean(args[1]) == Boolean.TRUE) {
                    List<String> lines = FileManager.readFileLines("drawn.txt");
                    System.out.println("[BH] TEST: "+lines.toString());
                    lines.removeIf(s -> s.equals(args[0].toLowerCase()));
                    System.out.println("[BH] TEST: "+lines.toString());
                    FileManager.createEmptyFile("drawn.txt");
                    for (String line : lines) {
                        FileManager.appendFile(line.toLowerCase(), "drawn.txt");
                    }
                }
                m.setDrawn(Boolean.parseBoolean(args[1]));
                CapyLogger.errorMessage("Drawn \"" + m.getName() + "\" set to " + Boolean.parseBoolean(args[1]));
                return;
            }
        }
        CapyLogger.errorMessage("No module provided.");
    }

}
