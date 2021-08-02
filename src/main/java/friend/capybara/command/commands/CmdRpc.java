package friend.capybara.command.commands;

import friend.capybara.command.Command;
import friend.capybara.module.ModuleManager;
import friend.capybara.module.mods.DiscordRPCMod;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileHelper;

public class CmdRpc extends Command {

    @Override
    public String getAlias() {
        return "rpc";
    }

    @Override
    public String getDescription() {
        return "Sets custom discord rpc text";
    }

    @Override
    public String getSyntax() {
        return "rpc [top text] [bottom text]";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if (args.length != 2) {
            CapyLogger.errorMessage(getSyntax());
        }

        ((DiscordRPCMod) ModuleManager.getModule(DiscordRPCMod.class)).setText(args[0], args[1]);

        CapyLogger.infoMessage("Set RPC to " + args[0] + ", " + args[1]);

        FileHelper.saveMiscSetting("discordRPCTopText", args[0]);
        FileHelper.saveMiscSetting("discordRPCBottomText", args[1]);
    }

}
