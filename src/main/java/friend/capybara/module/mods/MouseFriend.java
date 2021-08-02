package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.Capybara;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingMode;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileHelper;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

public class MouseFriend extends Module {

    private boolean antiSpamClick = false;

    public MouseFriend() {
        super("MouseFriend", KEY_UNBOUND, Category.MISC, "Add/Remove friends with mouse buttons",
                new SettingMode("Button", "Middle", "Right", "MOUSE4", "MOUSE5", "MOUSE6"),
                new SettingToggle("Messages", true).withDesc("says in chat when a friend is added/removed"));
    }

    @Subscribe
    public void onTick(EventTick event) {
        int setting = getSetting(0).asMode().mode;
        int button = setting == 0 ? GLFW.GLFW_MOUSE_BUTTON_MIDDLE : setting == 1 ? GLFW.GLFW_MOUSE_BUTTON_RIGHT : setting + 2;

        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == 1 && !antiSpamClick) {
            antiSpamClick = true;

            Optional<Entity> lookingAt = DebugRenderer.getTargetedEntity(mc.player, 200);

            if (lookingAt.isPresent()) {
                Entity e = lookingAt.get();

                if (e instanceof PlayerEntity) {
                    if (Capybara.friendMang.has(e.getName().asString())) {
                        Capybara.friendMang.remove(e.getName().asString());
                        if (this.getSetting(1).asToggle().state) {
                            CapyLogger.infoMessage("Removed \"" + e.getName().asString() + "\" from the friend list");
                            FileHelper.SCHEDULE_SAVE_FRIENDS = true;
                        }
                    } else {
                        Capybara.friendMang.add(e.getName().asString());
                        if (this.getSetting(1).asToggle().state) {
                            CapyLogger.infoMessage("Added \"" + e.getName().asString() + "\" to the friend list");
                            FileHelper.SCHEDULE_SAVE_FRIENDS = true;
                        }
                    }
                }
            }
        } else if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), button) == 0) {
            antiSpamClick = false;
        }
    }

}
