package bleach.hack.utils;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordUser;

public class DiscordRPCManager {
	public static DiscordUser user;

	public static void start(String id) {
		System.out.println("Initing Discord RPC...");

		DiscordRPC.discordInitialize(id, new DiscordEventHandlers.Builder().setReadyEventHandler((duser) -> {
			user = duser;
		}).build(), true);
	}

	public static void stop() {
		DiscordRPC.discordShutdown();
	}
}
