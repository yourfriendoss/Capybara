package friend.capybara.module.mods;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.eventbus.Subscribe;

import friend.capybara.command.Command;
import friend.capybara.event.events.EventReadPacket;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.utils.file.FileManager;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class CleanChat extends Module {

	public CleanChat() {
		super("CleanChat", KEY_UNBOUND, Category.CHAT,
				"checks messages you receive and removes ones with bad words in them! To add words \"" + Command.PREFIX
						+ "cleanchat add/del [word]\"");
	}

	ArrayList<String> blacklistedWords = new ArrayList<String>();

	@Override
	public void onEnable() {
		super.onEnable();
		for (String s : FileManager.readFileLines("cleanchat.txt")) {
			blacklistedWords.add(s);
		}
	}

	@Subscribe
	public void onPacketRead(EventReadPacket event) {
		if (event.getPacket() instanceof GameMessageS2CPacket) {
			List<String> allMatches = new ArrayList<String>();
			String text = ((GameMessageS2CPacket) event.getPacket()).getMessage().toString();
			Pattern p = Pattern.compile("text='(.*?)'"); // the pattern to search for
			Matcher m = p.matcher(text);
			while (m.find()) {
				allMatches.add(m.group(1));
			}
			StringBuilder parsed = new StringBuilder();
			for (String s : allMatches) {
				parsed.append(s);
			}
			for (Object s : blacklistedWords) {
				if (parsed.toString().toLowerCase().contains(s.toString().toLowerCase())) {
					event.setCancelled(true);
				}
			}

		}
	}

}
