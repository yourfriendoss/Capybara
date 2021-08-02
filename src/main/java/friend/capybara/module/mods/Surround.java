package friend.capybara.module.mods;

import com.google.common.collect.Sets;
import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventTick;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.setting.base.SettingMode;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.setting.base.SettingToggle;
import friend.capybara.setting.other.SettingRotate;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.WorldUtils;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Surround extends Module {

	int slot;

	public Surround() {
		super("Surround", KEY_UNBOUND, Category.COMBAT, "Surrounds yourself with obsidian",
				new SettingMode("Mode", "1x1", "Fit").withDesc(
						"Mode, 1x1 places 4 blocks around you, fit fits the blocks around you so it doesn't place inside of you"),
				new SettingToggle("Autocenter", true).withDesc("Autocenters you to the nearest block"),
				new SettingToggle("Keep on", false).withDesc("Keeps the module on after placing the obsidian"),
				new SettingToggle("Jump disable", true).withDesc("Disables the module if you jump"),
				new SettingToggle("Back to slot", true),
				new SettingSlider("BPT", 1, 8, 2, 0).withDesc("Blocks per tick, how many blocks to place per tick"),
				new SettingRotate(false).withDesc("Rotates when placing"));
	}

	public void onEnable() {
		super.onEnable();
		if (getSetting(4).asToggle().state && mc.player != null)
			slot = mc.player.inventory.selectedSlot;

		int obby = -1;
		for (int i = 0; i < 9; i++) {
			if (mc.player.inventory.getStack(i).getItem() == Items.NETHERITE_BLOCK
					|| mc.player.inventory.getStack(i).getItem() == Items.OBSIDIAN) {
				obby = i;
				break;
			}
		}

		if (obby == -1) {
			CapyLogger.errorMessage("No netherite/obsidian in hotbar!");
			setToggled(false);
			return;
		}

		if (getSetting(1).asToggle().state) {
			double playerX = Math.floor(mc.player.getX());
			double playerZ = Math.floor(mc.player.getZ());
			mc.player.updatePosition(playerX + 0.5, mc.player.getY(), playerZ + 0.5);
			mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(playerX + 0.5, mc.player.getY(),
					playerZ + 0.5, mc.player.isOnGround()));
		}

		placeTick(obby);
	}

	@Subscribe
	public void onTick(EventTick event) {
		if (getSetting(3).asToggle().state && mc.options.keyJump.isPressed()) {
			setToggled(false);
			return;
		}

		int obby = -1;
		for (int i = 0; i < 9; i++) {
			if (mc.player.inventory.getStack(i).getItem() == Items.NETHERITE_BLOCK
					|| mc.player.inventory.getStack(i).getItem() == Items.OBSIDIAN) {
				obby = i;
				break;
			}
		}

		if (obby == -1) {
			CapyLogger.errorMessage("Ran out of netherite/obsidian!");
			setToggled(false);
			return;
		}

		placeTick(obby);
	}

	private void placeTick(int obsidian) {
		int cap = 0;

		if (getSetting(0).asMode().mode == 0) {
			for (BlockPos b : new BlockPos[] { mc.player.getBlockPos().north(), mc.player.getBlockPos().east(),
					mc.player.getBlockPos().south(), mc.player.getBlockPos().west() }) {

				if (cap >= (int) getSetting(5).asSlider().getValue()) {
					return;
				}

				if (getSetting(6).asRotate().state) {
					WorldUtils.facePosAuto(b.getX() + 0.5, b.getY() + 0.5, b.getZ() + 0.5, getSetting(5).asRotate());
				}

				if (WorldUtils.placeBlock(b, obsidian, false, false)) {
					cap++;
				}
			}
		} else {
			Box box = mc.player.getBoundingBox();
			for (BlockPos b : Sets.newHashSet(new BlockPos(box.minX - 1, box.minY, box.minZ),
					new BlockPos(box.minX, box.minY, box.minZ - 1), new BlockPos(box.maxX + 1, box.minY, box.minZ),
					new BlockPos(box.maxX, box.minY, box.minZ - 1), new BlockPos(box.minX - 1, box.minY, box.maxZ),
					new BlockPos(box.minX, box.minY, box.maxZ + 1), new BlockPos(box.maxX + 1, box.minY, box.maxZ),
					new BlockPos(box.maxX, box.minY, box.maxZ + 1))) {

				if (cap >= (int) getSetting(4).asSlider().getValue()) {
					return;
				}

				if (getSetting(5).asRotate().state) {
					WorldUtils.facePosAuto(b.getX() + 0.5, b.getY() + 0.5, b.getZ() + 0.5, getSetting(5).asRotate());
				}

				if (WorldUtils.placeBlock(b, obsidian, false, false)) {
					cap++;
				}
			}
		}

		if (!getSetting(2).asToggle().state) {
			setToggled(false);
		}
	}

	public void onDisable() {
		super.onDisable();
		if (getSetting(4).asToggle().state && mc.player != null)
			mc.player.inventory.selectedSlot = slot;
	}
}