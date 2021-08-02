package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyLogger;
import net.minecraft.util.math.Vec3d;

public class DeathCoords extends Module {

    boolean sent = false;

    public DeathCoords() {
        super("DeathCoords", KEY_UNBOUND, Category.WORLD, "Sends death coords in chat",
                new SettingToggle("Dimension", true));
    }

    @Subscribe
    public void onTick(EventTick e) {
        if (mc.player.isDead() && !sent) {
            Vec3d pos = mc.player.getPos();
            String coords = Math.round(pos.x) + "\u00a7f/\u00A73" + Math.round(pos.y) + "\u00a7f/\u00A73" + Math.round(pos.z);
            CapyLogger.infoMessage("Death coords [\u00a73" + coords + "\u00a7f]");
            if (getSetting(0).asToggle().state)
                CapyLogger.infoMessage("Dimension [\u00A73" + mc.world.getRegistryKey().getValue().getPath().toLowerCase() + "\u00a7f]");
            sent = true;
        } else if (!mc.player.isDead()) sent = false;
    }
}
