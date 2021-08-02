package friend.capybara.module.mods;

import java.util.HashMap;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventReadPacket;
import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.utils.CapyLogger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

public class TotemNotifier extends Module {

	HashMap<String, Boolean> players = new HashMap<>();
	long time = 0;

	public TotemNotifier() {
		super("TotemNotifier", KEY_UNBOUND, Category.MISC,
				"Notifies when other players have/don't have a totem in their hands",
				new SettingSlider("Range", 5, 100, 50, 1), new SettingSlider("Delay", 0, 500, 100, 0));
	}

	@Subscribe
	public void onTick(EventTick event) {
		if ((System.currentTimeMillis() - time) > getSetting(1).asSlider().getValue()) {
			for (Entity entity : mc.world.getEntities()) {
				if (!(entity instanceof PlayerEntity) || entity == mc.player
						|| mc.player.distanceTo(entity) > getSetting(0).asSlider().getValue())
					continue;
				String displayName = entity.getDisplayName().getString();
				Item mainHandItem = ((PlayerEntity) entity).getMainHandStack().getItem();
				Item offHandItem = ((PlayerEntity) entity).getOffHandStack().getItem();
				boolean totem = false;
				if (mainHandItem == Items.TOTEM_OF_UNDYING || offHandItem == Items.TOTEM_OF_UNDYING)
					totem = true;
				if (!players.containsKey(displayName)) {
					players.putIfAbsent(displayName, totem);
					CapyLogger.infoMessage(totem ? "\u00a7f" + displayName + " \u00a73has totem in his hand"
							: "\u00a7f" + displayName + " \u00a73doesn't have totem in his hand");
				}
				if (players.get(displayName) != totem) {
					players.put(displayName, totem);
					CapyLogger.infoMessage(totem ? "\u00a7f" + displayName + " \u00a73now has totem in his hand"
							: "\u00a7f" + displayName + " \u00a73now doesn't have totem in his hand");
				}
			}
			time = System.currentTimeMillis();
		}
	}

	@Subscribe
	public void gameJoin(EventReadPacket e) {
		if (!(e.getPacket() instanceof GameJoinS2CPacket))
			return;
		players.clear();
	}

}
