package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventSendPacket;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.utils.file.FileManager;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class AutoLogin extends Module {

    public AutoLogin() { super("AutoLogin", KEY_UNBOUND, Category.CHAT, "Login on offline (cracked) servers"); }

    @Override
    public void init() {
        super.init();
        FileManager.createFile("loginData.txt");
    }

    @Subscribe
    public void packetRead(EventReadPacket e) {
        if (mc.getCurrentServerEntry() == null) return;

        if (e.getPacket() instanceof GameMessageS2CPacket) {
            if (!(((GameMessageS2CPacket) e.getPacket()).getSenderUuid().toString().contains("000000000"))) return;
            String msg = ((GameMessageS2CPacket) e.getPacket()).getMessage().getString();
            if (!msg.contains(" /l ") && !msg.contains(" /login ")) return;

            FileManager.readFileLines("loginData.txt").forEach(line -> {
                String[] data = line.split("-");
                if (mc.player.getDisplayName().getString().equals(data[0]) && mc.getCurrentServerEntry().address.equals(data[1]))
                    mc.player.sendChatMessage("/login " + data[2]);
            });
        }
    }

    @Subscribe
    public void packetSend(EventSendPacket e) {
        if (mc.getCurrentServerEntry() == null) return;

        if (e.getPacket() instanceof ChatMessageC2SPacket) {
            String msg = ((ChatMessageC2SPacket) e.getPacket()).getChatMessage();
            if (!msg.startsWith("/register") && !msg.startsWith("/reg") && !msg.startsWith("/l") && !msg.startsWith("/login")) return;

            for (String line : FileManager.readFileLines("loginData.txt")) {
                String[] data = line.split("-");
                if (mc.player.getDisplayName().getString().equals(data[0]) && mc.getCurrentServerEntry().address.equals(data[1]))
                    return;
            }

            String[] regData = msg.split(" ");
            FileManager.appendFile(mc.player.getDisplayName().getString() + "-" + mc.getCurrentServerEntry().address + "-" + regData[1], "loginData.txt");
        }
    }

}
