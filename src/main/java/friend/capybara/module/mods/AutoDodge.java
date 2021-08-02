package bleach.hack.module.mods;

import com.google.common.eventbus.Subscribe;

import bleach.hack.event.events.EventTick;
import bleach.hack.module.Category;
import bleach.hack.module.Module;
import bleach.hack.setting.base.SettingSlider;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoDodge extends Module {
	public Vec3d prevPos;

	public AutoDodge() {
		super("AutoDodge", KEY_UNBOUND, Category.PLAYER, "Automatically dodge obstacles",
				new SettingSlider("Range: ", 1.0D, 25.0D, 5.0D, 0));
	}

	@Subscribe
	public void onTick(EventTick event) {
		BlockPos player = mc.player.getBlockPos().north((int) this.getSettings().get(0).asSlider().getValue());
		if (this.mc.world.getBlockState(player).getBlock() != Blocks.AIR) {
			if (this.mc.world.getBlockState(mc.player.getBlockPos().east(1).north(1)).getBlock() == Blocks.AIR) {
				mc.player.setVelocity(0.1, mc.player.getVelocity().y, 0);
			} else if (this.mc.world.getBlockState(mc.player.getBlockPos().west(1).north(1)).getBlock() == Blocks.AIR) {
				mc.player.setVelocity(-0.1, mc.player.getVelocity().y, 0);
			}
		}
	}
}