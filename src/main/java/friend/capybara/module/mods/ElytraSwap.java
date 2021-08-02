package friend.capybara.module.mods;

import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;

public class ElytraSwap extends Module {

    public ElytraSwap() {
        super("ElytraSwap", KEY_UNBOUND, Category.PLAYER, "Swaps elytra out for chestplate and vice versa");
    }
    public void onEnable() {
        if (!ModuleManager.getModule(AutoArmor.class).isToggled()) {
            ModuleManager.getModule(AutoArmor.class).toggle();
        }
        if (ModuleManager.getModule(AutoArmor.class).getSetting(1).asToggle().state == Boolean.TRUE) {
            ModuleManager.getModule(AutoArmor.class).getSetting(1).asToggle().state = Boolean.FALSE;
        } else if (ModuleManager.getModule(AutoArmor.class).getSetting(1).asToggle().state == Boolean.FALSE) {
            ModuleManager.getModule(AutoArmor.class).getSetting(1).asToggle().state = Boolean.TRUE;
        }
        this.setToggled(false);
    }
}
