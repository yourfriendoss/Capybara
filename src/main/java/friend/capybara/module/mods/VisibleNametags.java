package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventEntityRender;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import net.minecraft.entity.mob.MobEntity;

public class VisibleNametags extends Module {
    public VisibleNametags() {
        super("VisibleNametags", KEY_UNBOUND, Category.MISC, "Makes nametags of mobs always visible");
    }
    @Subscribe
    public void onEntity(EventEntityRender event) {
        event.getEntity().setCustomNameVisible(event.getEntity() instanceof MobEntity && event.getEntity().hasCustomName());
    }
}
