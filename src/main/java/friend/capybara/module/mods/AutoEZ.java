package friend.capybara.module.mods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import friend.capybara.Capybara;
import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileManager;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class AutoEZ extends Module {

	long killTime = 0;
	List<String> lines = new ArrayList<>();

	public AutoEZ() {
		super("AutoEZ", KEY_UNBOUND, Category.CHAT, "Does exactly what you think it does",
				new SettingToggle("Reload", false));
	}

	@Override
	public void init() {
		super.init();
		if (!FileManager.fileExists("AutoEZ.txt")) {
			FileManager.createFile("AutoEZ.txt");
			FileManager.appendFile("EZ! " + Capybara.NAME + " on top! Kill streak: %killStreak%", "AutoEZ.txt");
		}
		lines = FileManager.readFileLines("AutoEZ.txt");
	}

	@Override
	public void onEnable() {
		super.onEnable();
		CapyLogger.infoMessage("You can edit AutoEZ messages in " + Capybara.NAME + "/AutoEZ.txt");
	}

	@Subscribe
	public void deathMSG(EventReadPacket event) {
		if (!(event.getPacket() instanceof GameMessageS2CPacket))
			return;

		String msg = ((GameMessageS2CPacket) event.getPacket()).getMessage().getString().toLowerCase();
		KillStreak ks = new KillStreak();
		for (String word : ks.killWords) {
			if (msg.contains(word) && msg.contains(mc.player.getDisplayName().getString().toLowerCase())
					&& ((GameMessageS2CPacket) event.getPacket()).getSenderUuid().toString().contains("000000000")) {
				killTime = System.currentTimeMillis();
			}
		}
	}

	@Subscribe
	public void onTick(EventTick et) {
		if (getSetting(0).asToggle().state) {
			lines = FileManager.readFileLines("AutoEZ.txt");
			getSetting(0).asToggle().toggle();
		}

		if (killTime != 0 && (System.currentTimeMillis() - killTime) > 200 && !mc.player.isDead()) {
			Random r = new Random();
			if (lines.isEmpty())
				mc.player.sendChatMessage("EZ! " + Capybara.CLIENT + " on top! Kill streak: " + killStreak());
			else
				mc.player.sendChatMessage(lines.get(r.nextInt(lines.size())).replace("%killStreak%", killStreak()));
			killTime = 0;
		}

		if (mc.player.isDead()) {
			killTime = 0;
		}
	}

	private String killStreak() {
		KillStreak streak = (KillStreak) ModuleManager.getModule(KillStreak.class);
		return streak.isToggled() ? String.valueOf(streak.kills + 1) : "[module is disabled]";
	}
}
