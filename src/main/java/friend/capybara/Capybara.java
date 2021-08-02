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
 * 
 * 
 * 
 * Minecraft clients, 
 * Love em or hate them, are a critical part of online Minecraft, whether you're on the Oldest Anarchy Server 
 * In Minecraft, 2B2T, or simply using one to get an unfair advantage in PvP, clients are used daily. 
 * But as with most things, clients are forever compete to gain the upper hand. 
 * This video, we will be talking about the infamous war, between sigma client, and ligma client.
 */

package bleach.hack;

import com.google.common.eventbus.EventBus;

import bleach.hack.module.Module;
import bleach.hack.module.ModuleManager;
import bleach.hack.module.mods.ClickGui;
import bleach.hack.module.mods.UI;
import bleach.hack.utils.FriendManager;
import bleach.hack.utils.Rainbow;
import bleach.hack.utils.file.BleachFileHelper;
import bleach.hack.utils.file.BleachFileMang;
import net.fabricmc.api.ModInitializer;

public class BleachHack implements ModInitializer {

	public static final String NAME = "MonsterRat";

	public static final String VERSION = "v1.0";

	public static final String CLIENT = NAME + " " + VERSION;

	public static EventBus eventBus = new EventBus();

	public static FriendManager friendMang;

	@Override
	public void onInitialize() {
		System.out.println("Detected JRE: " + System.getProperty("java.runtime.version"));
		System.out.println("Pre headless toggle property: " + System.getProperty("java.awt.headless"));
		System.setProperty("java.awt.headless", "false");
		System.out.println("Post headless toggle property: " + System.getProperty("java.awt.headless"));
		BleachFileMang.init();
		BleachFileHelper.readModules();

		ClickGui.clickGui.initWindows();
		BleachFileHelper.readClickGui();
		BleachFileHelper.readPrefix();
		BleachFileHelper.readFriends();

		for (Module m : ModuleManager.getModules())
			m.init();

		eventBus.register(new Rainbow());
		eventBus.register(new ModuleManager());

		BleachFileMang.createFile("drawn.txt");

		for (String s : BleachFileMang.readFileLines("drawn.txt")) {
			for (Module m : ModuleManager.getModules()) {
				if (m.getName().toLowerCase().equals(s.toLowerCase())) {
					m.setDrawn(false);
				}
			}
		}
		if (!BleachFileMang.fileExists("cleanchat.txt")) {
			BleachFileMang.createFile("cleanchat.txt");
			String[] badWords = { "nigger", "fag", "retard", "autism", "chink", "tranny", "fuck", "shit", "nigga" };
			for (String s : badWords)
				BleachFileMang.appendFile(s, "cleanchat.txt");

		}
		// hey bro check my cute ui
		Module ui = ModuleManager.getModule(UI.class);
		if (!ui.isToggled())
			ui.setToggled(true);
	}

}
