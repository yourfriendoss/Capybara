package friend.capybara.module.mods;

import org.lwjgl.glfw.GLFW;

import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.setting.base.SettingToggle;

public class ColourChooser extends Module {
    public ColourChooser() {
        super("ColourChooser", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT, "HUD color settings",
                new SettingToggle("Rainbow", false),
                new SettingSlider("Red", 0, 255, 245, 0),
                new SettingSlider("Green", 0, 255, 169, 0),
                new SettingSlider("Blue", 0, 255, 184, 0),
                new SettingSlider("TextRed", 0, 255, 91, 0),
                new SettingSlider("TextGreen", 0, 255, 206, 0),
                new SettingSlider("TextBlue", 0, 255, 250, 0));
    }

    @Override
    public void onEnable() {
        setToggled(false);
    }
}