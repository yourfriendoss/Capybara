package friend.capybara.module.mods;

import java.util.Random;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventSendPacket;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.utils.FabricReflect;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class uwunya extends Module {
	public uwunya() {
		super("uwu nya", KEY_UNBOUND, Category.CHAT, ":3");
	}

	@Subscribe
	public void onPacketSend(EventSendPacket event) {
		if (event.getPacket() instanceof ChatMessageC2SPacket) {
			String text = ((ChatMessageC2SPacket) event.getPacket()).getChatMessage();

			if (text.startsWith("/"))
				return;

			text = text.toLowerCase().replace("l", "w").replace("r", "w").replace("o", "u").replace("p", "pw")
					.replace("'", "").replace(",", "").replace("su", "so").replace("tuu", "2").replace("hewwu", "hewwo")
					.replace("yuu", "u") + " " + getFurryText();

			if (!text.equals(((ChatMessageC2SPacket) event.getPacket()).getChatMessage())) {
				FabricReflect.writeField(event.getPacket(), text, "field_12764", "chatMessage");
			}
		}
	}

	public static String getFurryText() {
		String[] uwus = new String[] { "OwO", "UwU", "Awoo", "Rawr" };
		Random generator = new Random();
		int randomIndex = generator.nextInt(uwus.length);
		return uwus[randomIndex];
	}
}
