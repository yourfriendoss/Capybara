package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingMode;
import friend.capybara.utils.Finder;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

public class NoTotemsSuicide extends Module {

    boolean kill = false;

    public NoTotemsSuicide() {
        super("NoTotemsSuicide", KEY_UNBOUND, Category.COMBAT, "Automaticslly executes kill/suicide command",
                new SettingMode("Command", "/kill", "/suicide"));
    }

    @Subscribe
    public void onTick(EventTick event) {
        String cmd = getSetting(0).asMode().mode == 0 ? "/kill" : "/suicide";
        Integer totem = Finder.find(Items.TOTEM_OF_UNDYING, false);

        if (totem != null && !kill) kill = true;
        if (totem == null && kill) {
            mc.player.sendChatMessage(cmd);
            kill = false;
        }
    }

    @Subscribe
    public void join(EventReadPacket e) {
        if (!(e.getPacket() instanceof GameJoinS2CPacket)) return;
        kill = false;
    }
}
