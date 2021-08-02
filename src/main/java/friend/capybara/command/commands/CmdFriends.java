package friend.capybara.command.commands;

import friend.capybara.Capybara;
import friend.capybara.command.Command;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileHelper;

public class CmdFriends extends Command {

    @Override
    public String getAlias() {
        return "friend";
    }

    @Override
    public String getDescription() {
        return "Manage friends";
    }

    @Override
    public String getSyntax() {
        return "friend add [user] | friend del [user] | friend remove [user] | friend list | friend clear";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length < 2) {
                CapyLogger.errorMessage("No username selected");
                CapyLogger.errorMessage(getSyntax());
                return;
            }

            Capybara.friendMang.add(args[1]);
            CapyLogger.infoMessage("Added \"" + args[1] + "\" to the friend list");
        } else if (args[0].equalsIgnoreCase("del")) {
            if (args.length < 2) {
                CapyLogger.errorMessage("No username selected");
                CapyLogger.errorMessage(getSyntax());
                return;
            }

            Capybara.friendMang.remove(args[1].toLowerCase());
            CapyLogger.infoMessage("Deleted \"" + args[1] + "\" from the friend list");
        } else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                CapyLogger.errorMessage("No username selected");
                CapyLogger.errorMessage(getSyntax());
                return;
            }

            Capybara.friendMang.remove(args[1].toLowerCase());
            CapyLogger.infoMessage("Removed \"" + args[1] + "\" from the friend list");
        } else if (args[0].equalsIgnoreCase("list")) {
            String text = "";

            for (String f : Capybara.friendMang.getFriends()) {
                text += "\n\u00a72" + f;
            }

            CapyLogger.infoMessage(text);
        } else if (args[0].equalsIgnoreCase("clear")) {
            Capybara.friendMang.getFriends().clear();

            CapyLogger.infoMessage("Cleared Friend list");
        } else {
            CapyLogger.errorMessage("Invalid Syntax!\n" + getSyntax());
        }

        FileHelper.SCHEDULE_SAVE_FRIENDS = true;
    }

}
