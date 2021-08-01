package bleach.hack.module.mods;

import java.util.Objects;

import com.google.common.eventbus.Subscribe;

import bleach.hack.BleachHack;
import bleach.hack.event.events.EventTick;
import bleach.hack.module.Category;
import bleach.hack.module.Module;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

public class TabFriends extends Module {

	public TabFriends() {
		super("TabFriends", KEY_UNBOUND, Category.RENDER, "Adds color to friend names in tablist");
	}

	@Subscribe
	public void tick(EventTick event) {
		assert mc.player != null;
		if (mc.player.age % 10 == 0) {
			for (PlayerListEntry f : mc.player.networkHandler.getPlayerList()) {
				if (BleachHack.friendMang.has(f.getProfile().getName())) {
					Objects.requireNonNull(mc.player.networkHandler.getPlayerListEntry(f.getProfile().getName()))
							.setDisplayName(Text.of("\u00A7b" + f.getProfile().getName()));
				} else {
					Objects.requireNonNull(mc.player.networkHandler.getPlayerListEntry(f.getProfile().getName()))
							.setDisplayName(Text.of("\u00A7f" + f.getProfile().getName()));
				}
			}
		}
	}

}
