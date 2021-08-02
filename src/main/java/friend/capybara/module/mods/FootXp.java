package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventSendPacket;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.utils.WorldUtils;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;

public class FootXp extends Module {
	public FootXp() {
		super("FootXP", KEY_UNBOUND, Category.MOVEMENT, "Automatically points xp at feet");
	}

	@Subscribe
	public void sendPacket(EventSendPacket event) {
		if (mc.world == null || mc.player == null)
			return;
		if (event.getPacket() instanceof PlayerInteractItemC2SPacket
				&& mc.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE) {
			WorldUtils.facePosPacket(mc.player.getX(), mc.player.getY() - 2, mc.player.getZ());
		}
	}
}