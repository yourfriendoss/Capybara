package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventOpenScreen;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyQueue;
import net.minecraft.client.gui.screen.DeathScreen;

public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("AutoRespawn", KEY_UNBOUND, Category.PLAYER, "Automatically respawn when you die",
                new SettingToggle("Delay", false),
                new SettingSlider("Delay", 1, 15, 5, 0));
    }

    @Subscribe
    public void onOpenScreen(EventOpenScreen event) {
        if (event.getScreen() instanceof DeathScreen) {
            if (getSetting(0).asToggle().state) {
                for (int i = 0; i <= (int) getSetting(1).asSlider().getValue(); i++)
                    CapyQueue.add("autorespawn", () -> {
                    });
                CapyQueue.add("autorespawn", () -> mc.player.requestRespawn());
            } else {
                mc.player.requestRespawn();
            }
        }
    }
}
