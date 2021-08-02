package friend.capybara.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.module.mods.NoKeyBlock;

@Mixin(SharedConstants.class)
public class MixinSharedConstants {

    @Overwrite
    public static boolean isValidChar(char chr) {
        Module noKeyBlock = ModuleManager.getModule(NoKeyBlock.class);

        if (!noKeyBlock.isToggled()) {
            return chr != 167 && chr >= ' ' && chr != 127;
        }

        return (noKeyBlock.getSetting(0).asToggle().state || chr != 167)
                && (noKeyBlock.getSetting(1).asToggle().state || chr >= ' ')
                && (noKeyBlock.getSetting(2).asToggle().state || chr != 127);
    }
}