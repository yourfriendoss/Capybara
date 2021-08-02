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

import com.mojang.authlib.GameProfile;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;

public class PlayerCopyEntity extends OtherClientPlayerEntity {
	static MinecraftClient mc = MinecraftClient.getInstance();

	public PlayerCopyEntity() {
		this(mc.player.getGameProfile());
	}

	public PlayerCopyEntity(GameProfile profile) {
		this(profile, mc.player.getX(), mc.player.getY(), mc.player.getZ());
	}

	public PlayerCopyEntity(GameProfile profile, double x, double y, double z) {
		super(mc.world, profile);
		setPos(x, y, z);
	}

	public void spawn() {
		mc.world.addEntity(this.getEntityId(), this);
	}

	public void despawn() {
		mc.world.removeEntity(this.getEntityId());
	}

}
