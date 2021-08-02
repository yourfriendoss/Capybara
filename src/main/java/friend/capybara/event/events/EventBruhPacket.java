package friend.capybara.event.events;

import friend.capybara.event.Event;
import net.minecraft.network.Packet;

public class EventBruhPacket extends Event {

	private final Packet<?> packet;

	public EventBruhPacket(final Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return this.packet;
	}

	public static class Receive extends EventBruhPacket {
		public Receive(final Packet<?> packet) {
			super(packet);
		}
	}

	public static class Send extends EventBruhPacket {
		public Send(final Packet<?> packet) {
			super(packet);
		}
	}
}
