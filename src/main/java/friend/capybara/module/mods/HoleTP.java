package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

//insanely hard to code! mostly everything is stolen from bleach's step module

public class HoleTP extends Module {
	public HoleTP() {
		super("HoleTP", KEY_UNBOUND, Category.MOVEMENT, "AHH I FALL FAST");
	}

	@Subscribe
	public void onTick(EventTick event) {
		if (mc.player == null || mc.world == null || mc.player.isInLava() || mc.player.isSubmergedInWater()) {
			return;
		}
		if (mc.player.isOnGround()) {
			mc.player.setVelocity(mc.player.getVelocity().x, -1, mc.player.getVelocity().z);
			mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
		}
	}
}
