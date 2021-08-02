package friend.capybara.module.mods;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonParser;

import friend.capybara.event.events.EventEntityRender;
import friend.capybara.module.Category;
import friend.capybara.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.text.Text;

public class MobOwner extends Module {
	public MobOwner() {
		super("MobOwner", KEY_UNBOUND, Category.RENDER, "Renders owner tags above entity.");

	}

	@SuppressWarnings("serial")
	private final Map<String, String> cachedUUIDs = new HashMap<String, String>() {
		{
		}
	};

	public String getNameFromUUID(String uuid) {

		uuid = uuid.replace("-", "");
		for (Map.Entry<String, String> entries : cachedUUIDs.entrySet()) {
			if (entries.getKey().equalsIgnoreCase(uuid)) {
				return entries.getValue();
			}
		}
		final String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
		System.out.println("Querying " + url + " for player ID");
		try {
			final String nameJson = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
			if (nameJson != null && nameJson.length() > 0) {
				JsonParser parser = new JsonParser();
				String name = parser.parse(nameJson).getAsJsonArray()
						.get(parser.parse(nameJson).getAsJsonArray().size() - 1).getAsJsonObject().get("name")
						.toString();
				cachedUUIDs.put(uuid, name);
				return name;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* Run this again to reduce the amount of requests made to the Mojang API */
		for (Map.Entry<String, String> entries : cachedUUIDs.entrySet()) {
			if (entries.getKey().equalsIgnoreCase(uuid)) {
				return entries.getValue();
			}
		}
		cachedUUIDs.put(uuid, null);
		return null;
	}

	@Subscribe
	public void onLivingRender(EventEntityRender.Render event) {
		if (mc.world == null) {
			return;
		}
		for (final Entity e : mc.world.getEntities()) {
			if (e instanceof TameableEntity) {
				if (((TameableEntity) e).isTamed() && ((TameableEntity) e).getOwnerUuid() != null) {
					String ownername = getNameFromUUID(
							Objects.requireNonNull(((TameableEntity) e).getOwnerUuid()).toString());
					if (ownername != null) {
						ownername = ownername.replace("\"", "");
						String text = "Owner: " + ownername;
						e.setCustomNameVisible(true);
						e.setCustomName(Text.of(text));
					}
				}
			}
			if (e instanceof HorseBaseEntity) {
				if (((HorseBaseEntity) e).isTame() && ((HorseBaseEntity) e).getOwnerUuid() != null) {
					String ownername = getNameFromUUID(
							Objects.requireNonNull(((HorseBaseEntity) e).getOwnerUuid()).toString());
					if (ownername != null) {
						ownername = ownername.replace("\"", "");
						String text = "Owner: " + ownername;
						e.setCustomNameVisible(true);
						e.setCustomName(Text.of(text));
					}
				}
			}
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();
		cachedUUIDs.clear();
	}
}
