/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package friend.capybara.utils;

import friend.capybara.Capybara;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CapyLogger {
	static MinecraftClient client = MinecraftClient.getInstance();

	public static void infoMessage(String s) {
		try {
			client.inGameHud.getChatHud().addMessage(new LiteralText(getBHText(Formatting.GRAY) + " " + s));
		} catch (Exception e) {
			System.out.println("[" + Capybara.NAME + "] INFO: " + s);
		}
	}

	public static void warningMessage(String s) {
		try {
			client.inGameHud.getChatHud().addMessage(new LiteralText(getBHText(Formatting.GRAY) + " " + s));
		} catch (Exception e) {
			System.out.println("[" + Capybara.NAME + "] WARN: " + s);
		}
	}

	public static void errorMessage(String s) {
		try {
			client.inGameHud.getChatHud().addMessage(new LiteralText(getBHText(Formatting.GRAY) + " " + s));
		} catch (Exception e) {
			System.out.println("[" + Capybara.NAME + "] ERROR: " + s);
		}
	}

	public static void noPrefixMessage(String s) {
		try {
			client.inGameHud.getChatHud().addMessage(new LiteralText(s));
		} catch (Exception e) {
			System.out.println(s);
		}
	}

	public static void noPrefixMessage(Text text) {
		try {
			client.inGameHud.getChatHud().addMessage(text);
		} catch (Exception e) {
			System.out.println(text.asString());
		}
	}

	public static void actionBarMessage(String s) {
		try {
			client.player.sendMessage(new LiteralText(getBHText(Formatting.GRAY) + " " + s), true);
		} catch (Exception e) {
			System.out.println("[" + Capybara.NAME + "] INFO: " + s);
		}
	}

	private static String getBHText(Formatting color) {
		return color + "\u00a7f[\u00A73" + Capybara.NAME + "\u00a7f]";
	}
}
