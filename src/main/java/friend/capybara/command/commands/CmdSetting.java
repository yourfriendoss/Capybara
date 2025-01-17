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
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.setting.base.SettingBase;
import friend.capybara.setting.base.SettingMode;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyLogger;

public class CmdSetting extends Command {

    @Override
    public String getAlias() {
        return "setting";
    }

    @Override
    public String getDescription() {
        return "Changes a setting in a module";
    }

    @Override
    public String getSyntax() {
        return "setting [Module] [Setting number (starts at 0)] [value]";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args.length < 2) {
            CapyLogger.errorMessage(getSyntax());
            return;
        }

        Module m = ModuleManager.getModuleByName(args[0]);
        SettingBase s = m.getSetting(Integer.parseInt(args[1]));

        if (s instanceof SettingSlider) s.asSlider().setValue(Double.parseDouble(args[2]));
        else if (s instanceof SettingToggle) s.asToggle().state = Boolean.valueOf(args[2]);
        else if (s instanceof SettingMode) s.asMode().mode = Integer.parseInt(args[2]);
        else {
            CapyLogger.errorMessage("Invalid Command");
            return;
        }

        CapyLogger.infoMessage("Set Setting " + args[1] + " Of " + m.getName() + " To " + args[2]);
    }

}
