package friend.capybara.module.mods;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.Subscribe;

import friend.capybara.event.events.EventEntityRender;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import friend.capybara.module.ModuleManager;
import friend.capybara.utils.CapyLogger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractDonkeyEntity;

public class DonkeyAlert extends Module {
	public List<String> mob_uuids = new ArrayList<>();

	public DonkeyAlert() {
		super("DonkeyAlert", KEY_UNBOUND, Category.CHAT, "Alerts you if a donkey appears in your render distance");

	}

	@Subscribe
	public void onLivingRender(EventEntityRender.Render event) {
		if (mc.world == null) {
			return;
		}
		for (final Entity e : mc.world.getEntities()) {
			if (e instanceof AbstractDonkeyEntity && !mob_uuids.toString().contains(e.getUuidAsString())) {
				final boolean impact_toggle_state = ModuleManager.getModule(UI.class).getSetting(24).asToggle().state;
				final String coords = (impact_toggle_state ? "\u00A7f" : "") + (int) e.getX()
						+ (impact_toggle_state ? "\u00A79" : "") + " Z: " + (impact_toggle_state ? "\u00A7f" : "")
						+ (int) e.getZ();
				switch (e.getType().toString()) {
				case "entity.minecraft.donkey":
					CapyLogger.infoMessage("Found Donkey! X: " + coords);
					break;
				case "entity.minecraft.llama":
					CapyLogger.infoMessage("Found Llama! X: " + coords);
					break;
				case "entity.minecraft.mule":
					CapyLogger.infoMessage("Found Mule! X: " + coords);
					break;
				}
				mob_uuids.add(e.getUuidAsString());
			}
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();
		mob_uuids.clear();
	}
}
