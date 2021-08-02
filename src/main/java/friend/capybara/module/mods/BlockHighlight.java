package friend.capybara.module.mods;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventWorldRender;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.setting.base.SettingSlider;
import friend.capybara.utils.RenderUtils;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class BlockHighlight extends Module {
    public BlockHighlight() {
        super("BlockHighlight", KEY_UNBOUND, Category.RENDER, "Highlights blocks you are looking at",
            new SettingSlider("R", 1, 255, 100, 0),
            new SettingSlider("G", 1, 255, 140, 0),
            new SettingSlider("B", 1, 255, 255, 0),
            new SettingSlider("A", 1, 100, 100, 0));
    }

    @Subscribe
    public void onDraw(EventWorldRender e) {
        if (mc.crosshairTarget == null || !(mc.crosshairTarget instanceof BlockHitResult)) return;
        BlockPos pos = ((BlockHitResult) mc.crosshairTarget).getBlockPos();
        BreakingESP eps = (BreakingESP) ModuleManager.getModule(BreakingESP.class);
        if (mc.world.isAir(pos) || eps.blocks.containsKey(pos) || eps.blockTime.containsKey(pos)) return;
        RenderUtils.drawFilledBox(pos, (float) getSetting(0).asSlider().getValue() / 255, (float) getSetting(1).asSlider().getValue() / 255,
                (float) getSetting(2).asSlider().getValue() / 255, (float) getSetting(3).asSlider().getValue() / 100);
    }
}
