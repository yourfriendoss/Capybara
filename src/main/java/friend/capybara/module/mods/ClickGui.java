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
package friend.capybara.module.mods;

import org.lwjgl.glfw.GLFW;

import friend.capybara.gui.clickgui.ClickGuiScreen;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.setting.base.SettingToggle;

public class ClickGui extends Module {

    public static ClickGuiScreen clickGui = new ClickGuiScreen();

    public ClickGui() {
        super("ClickGUI", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT, "Draws the clickgui",
                new SettingSlider("Length", 90, 100, 100, 0),
                new SettingToggle("Search bar", true),
                new SettingToggle("Help", true),
                new SettingToggle("Static descriptions", true),
                new SettingToggle("Icons", true),
                new SettingToggle("Enable Padding", true));
    }

    public void onEnable() {
        mc.openScreen(clickGui);
        setToggled(false);
    }
}
