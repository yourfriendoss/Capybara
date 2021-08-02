package friend.capybara.setting.other;

import friend.capybara.setting.base.SettingMode;
import friend.capybara.setting.base.SettingToggle;

public class SettingRotate extends SettingToggle {

    public SettingRotate(boolean state) {
        super("Rotate", state);
        children.add(new SettingMode("Mode", "Server", "Client").withDesc("How to rotate"));
    }

    public int getRotateMode() {
        return getChild(0).asMode().mode;
    }
}
