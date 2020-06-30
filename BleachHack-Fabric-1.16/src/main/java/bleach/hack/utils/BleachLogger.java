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
package bleach.hack.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class BleachLogger {

	public static void infoMessage(String s) {
		try{ MinecraftClient.getInstance().inGameHud.getChatHud()
			.addMessage(new LiteralText("\u00a75[BleachHack] \u00a79\u00a7lINFO: \u00a79" + s));
		} catch (Exception e) { System.out.println("[BleachHack] INFO: " + s); }
	}
	
	public static void warningMessage(String s) {
		try{ MinecraftClient.getInstance().inGameHud.getChatHud()
			.addMessage(new LiteralText("\u00a75[BleachHack] \u00a7e\u00a7lWARN: \u00a7e" + s));
		} catch (Exception e) { System.out.println("[BleachHack] WARN: " + s); }
	}
	
	public static void errorMessage(String s) {
		try{ MinecraftClient.getInstance().inGameHud.getChatHud()
			.addMessage(new LiteralText("\u00a75[BleachHack] \u00a7c\u00a7lERROR: \u00a7c" + s));
		} catch (Exception e) { System.out.println("[BleachHack] ERROR: " + s); }
	}
	
	public static void noPrefixMessage(String s) {
		try{ MinecraftClient.getInstance().inGameHud.getChatHud()
			.addMessage(new LiteralText(s));
		} catch (Exception e) { System.out.println(s); }
	}
}