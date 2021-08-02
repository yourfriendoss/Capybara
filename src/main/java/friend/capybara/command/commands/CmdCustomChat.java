package friend.capybara.command.commands;

import java.util.Arrays;

import friend.capybara.command.Command;
import friend.capybara.module.ModuleManager;
import friend.capybara.module.mods.CustomChat;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileHelper;

public class CmdCustomChat extends Command {

    @Override
    public String getAlias() {
        return "customchat";
    }

    @Override
    public String getDescription() {
        return "Changes customchat prefix and suffix";
    }

    @Override
    public String getSyntax() {
        return "customchat current | customchat reset | customchat prefix [prefix] | customchat suffix [suffix]";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args.length == 0) {
            CapyLogger.errorMessage(getSyntax());
            return;
        }

        CustomChat chat = (CustomChat) ModuleManager.getModule(CustomChat.class);
        if (args[0].equalsIgnoreCase("current")) {
            CapyLogger.infoMessage("Current prefix: \"" + chat.prefix + "\", suffix: \"" + chat.suffix + "\"");
        } else if (args[0].equalsIgnoreCase("reset")) {
            chat.prefix = "";
            chat.suffix = " \u25ba \u0432\u2113\u0454\u03b1c\u043d\u043d\u03b1c\u043a";

            CapyLogger.infoMessage("Reset the chat prefix and suffix");
            FileHelper.saveMiscSetting("customChatPrefix", chat.prefix);
            FileHelper.saveMiscSetting("customChatSuffix", chat.suffix);
        } else if (args[0].equalsIgnoreCase("prefix")) {
            chat.prefix = String.join(" ", Arrays.asList(args).subList(1, args.length)).trim() + " ";

            CapyLogger.infoMessage("Set prefix to: \"" + chat.prefix + "\"");
            FileHelper.saveMiscSetting("customChatPrefix", chat.prefix);
        } else if (args[0].equalsIgnoreCase("suffix")) {
            chat.suffix = " " + String.join(" ", Arrays.asList(args).subList(1, args.length)).trim();

            CapyLogger.infoMessage("Set suffix to: \"" + chat.suffix + "\"");
            FileHelper.saveMiscSetting("customChatSuffix", chat.suffix);
        } else {
            CapyLogger.errorMessage(getSyntax());
        }
    }

}