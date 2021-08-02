package friend.capybara.module.mods;

import friend.capybara.module.Category;
import friend.capybara.module.Module;

public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", KEY_UNBOUND, Category.MOVEMENT, "Stops you walking off blocks");
    }
}
