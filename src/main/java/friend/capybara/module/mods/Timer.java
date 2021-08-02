package friend.capybara.module.mods;

import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;

public class Timer extends Module {

    public Timer() {
        super("Timer", KEY_UNBOUND, Category.WORLD, "more speeds",
                new SettingSlider("Speed", 0.01, 20, 1, 2));
    }

    // See MixinRenderTickCounter for code

}
