package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.Capybara;
import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileManager;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoRespond extends Module {

    List<String> gamers = new ArrayList<>();
    List<String> msgs = new ArrayList<>();

    public AutoRespond() {
        super("AutoRespond", KEY_UNBOUND, Category.CHAT, "Automatically responds to chat messages from selected player(s)",
                new SettingToggle("Reload", false));
    }

    public void init() {
        String[] arFiles = {"players", "messages", "offlineFormat"};
        for (String s : arFiles) FileManager.createFile("ar_" + s + ".txt");
        if (FileManager.readFileLines("ar_offlineFormat.txt").isEmpty())
            FileManager.appendFile("<%name%>", "ar_offlineFormat.txt");
        gamers = FileManager.readFileLines("ar_players.txt");
        msgs = FileManager.readFileLines("ar_messages.txt");
    }
    @Subscribe
    public void onChatMessage(EventReadPacket e) {
        MobOwner mo = new MobOwner();
        Random r = new Random();
        if (!(e.getPacket() instanceof GameMessageS2CPacket)) return;
        String msg = ((GameMessageS2CPacket) e.getPacket()).getMessage().getString().toLowerCase();
        if (gamers.isEmpty()) disable("ar_players.txt");
        if (msgs.isEmpty()) disable("ar_messages.txt");
        if (gamers.contains(mo.getNameFromUUID(((GameMessageS2CPacket) e.getPacket()).getSenderUuid().toString()))) {
            mc.player.sendChatMessage(msgs.get(r.nextInt(msgs.size())));
            return;
        }
        for (String s : gamers) {
            String formatted = FileManager.readFileLines("ar_offlineFormat.txt").toString().toLowerCase().replace("%name%", s.toLowerCase());
            if (msg.startsWith(formatted.toLowerCase().replace("[", "").replace("]", "")))
                mc.player.sendChatMessage(msgs.get(r.nextInt(msgs.size())));
        }
    }

    @Subscribe
    public void onTick(EventTick e) {
        if (getSetting(0).asToggle().state) {
            gamers = FileManager.readFileLines("ar_players.txt");
            msgs = FileManager.readFileLines("ar_messages.txt");
            getSetting(0).asToggle().toggle();
            CapyLogger.infoMessage("Files reloaded");
        }
    }

    private void disable(String fileName) {
        CapyLogger.errorMessage("./" + Capybara.NAME + "/" + fileName +" is empty! Disabling AutoRespond...");
        CapyLogger.infoMessage("Check " + Capybara.NAME + " in your Minecraft folder!");
        setToggled(false);
    }
}
