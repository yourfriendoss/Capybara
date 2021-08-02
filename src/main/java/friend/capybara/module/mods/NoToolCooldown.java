package friend.capybara.module.mods;

import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;

public class NoToolCooldown extends Module {

	public NoToolCooldown() {
		super("NoToolCooldown", KEY_UNBOUND, Category.COMBAT, "No Tool Cooldown",
				new SettingSlider("Timer", 7, 20, 7, 1));
	}
}
