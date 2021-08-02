package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingMode;
import friend.capybara.utils.CapyLogger;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class JoinLeaveMSGs extends Module {

    List<String> players = new ArrayList<>();
    long time = -1;

    public JoinLeaveMSGs() {
        super("JoinLeaveMSGs", KEY_UNBOUND, Category.CHAT, "Notifies when someone joins/leaves",
                new SettingMode("Style", "Vanilla", "Old 2b2t"));
    }

    @Subscribe
    public void onTick(EventTick event) {
        if (time == -1) {
            for (PlayerListEntry player : mc.player.networkHandler.getPlayerList())
                players.add(player.getProfile().getName());
            time = System.currentTimeMillis();
        } else if (time != -1 && (System.currentTimeMillis() - time) > 1000) {
            for (PlayerListEntry player : mc.player.networkHandler.getPlayerList()) {
                if (!players.contains(player.getProfile().getName()))
                    CapyLogger.noPrefixMessage(getMSG(player.getProfile().getName() + " joined"));
            }
            for (String player : players) {
                if (mc.player.networkHandler.getPlayerListEntry(player) == null)
                    CapyLogger.noPrefixMessage(getMSG(player + " left"));
            }
            time = -1;
            players.clear();
        }
    }

    @Subscribe
    public void disconnect(EventReadPacket e) {
        if (!(e.getPacket() instanceof DisconnectS2CPacket)) return;
        players.clear();
    }

    private String getMSG(String name) {
        return getSetting(0).asMode().mode == 0 ? Formatting.YELLOW + name + " the game" : Formatting.GRAY + name;
    }
}
