package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventSendPacket;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.utils.FabricReflect;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class Chatfix extends Module {



	public Chatfix() {
		super("Chatfix", KEY_UNBOUND, Category.CHAT,
				"Customizes your chat messages!",
				new SettingToggle("Prefix", false)
						.withDesc("Message prepended to the message."),
				new SettingToggle("Suffix", false)
						.withDesc("Message appended to the message."));
	}

	@Subscribe
	public void onPacketSend(EventSendPacket event) {
		if (event.getPacket() instanceof ChatMessageC2SPacket) {
			String text = ((ChatMessageC2SPacket) event.getPacket()).getChatMessage();

			if (text.startsWith("/"))
				return;

			if (getSetting(0).asToggle().state) {
				text = "✼ " + text;
			}

			if (getSetting(1).asToggle().state) {
				text = text + " ⦮ ⦯";
			}

			if (!text.equals(((ChatMessageC2SPacket) event.getPacket()).getChatMessage())) {
				FabricReflect.writeField(event.getPacket(), text, "field_12764", "chatMessage");
			}
		}
	}


}
