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
package friend.capybara.command.commands;

import friend.capybara.command.Command;
import friend.capybara.utils.CapyLogger;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringNbtReader;

public class CmdNBT extends Command {

    @Override
    public String getAlias() {
        return "nbt";
    }

    @Override
    public String getDescription() {
        return "NBT stuff";
    }

    @Override
    public String getSyntax() {
        return "nbt [get/copy/set/wipe] <nbt>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args[0].isEmpty()) {
            CapyLogger.errorMessage("Invalid Syntax!");
            CapyLogger.infoMessage(getSyntax());
            return;
        }
        ItemStack item = mc.player.inventory.getMainHandStack();

        if (args[0].equalsIgnoreCase("get")) {
            CapyLogger.infoMessage("\u00a76\u00a7lNBT:\n" + item.getTag() + "");
        } else if (args[0].equalsIgnoreCase("copy")) {
            mc.keyboard.setClipboard(item.getTag() + "");
            CapyLogger.infoMessage("\u00a76Copied\n\u00a7f" + (item.getTag() + "\n") + "\u00a76to clipboard.");
        } else if (args[0].equalsIgnoreCase("set")) {
            try {
                if (args[1].isEmpty()) {
                    CapyLogger.errorMessage("Invalid Syntax!");
                    CapyLogger.infoMessage(getSyntax());
                    return;
                }
                item.setTag(StringNbtReader.parse(args[1]));
                CapyLogger.infoMessage("\u00a76Set NBT of " + item.getItem().getName() + "to\n\u00a7f" + (item.getTag()));
            } catch (Exception e) {
                CapyLogger.errorMessage("Invalid Syntax!");
                CapyLogger.infoMessage(getSyntax());
            }
        } else if (args[0].equalsIgnoreCase("wipe")) {
            item.setTag(new CompoundTag());
        }

    }

}
