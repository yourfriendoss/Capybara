package friend.capybara.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;

/**
 * Created by 086 on 11/11/2017.
 */
public class Wrapper {

	static MinecraftClient client = MinecraftClient.getInstance();

	public static MinecraftClient getMinecraft() {
		return MinecraftClient.getInstance();
	}

	public static ClientPlayerEntity getPlayer() {
		return client.player;
	}

	public static World getWorld() {
		return client.world;
	}

}