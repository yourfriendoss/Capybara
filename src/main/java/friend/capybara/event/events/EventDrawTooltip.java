package friend.capybara.event.events;

import java.util.List;

import friend.capybara.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;

public class EventDrawTooltip extends Event {
	MinecraftClient client = MinecraftClient.getInstance();

	public Screen screen;
	public MatrixStack matrix;
	public List<? extends OrderedText> text;
	public int x;
	public int y;
	public int mX;
	public int mY;

	public EventDrawTooltip(MatrixStack matrix, List<? extends OrderedText> text, int x, int y, int mX, int mY) {
		this.matrix = matrix;
		screen = client.currentScreen;
		this.text = text;
		this.x = x;
		this.y = y;
		this.mX = mX;
		this.mY = mY;
	}

}
