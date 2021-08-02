package friend.capybara.command.commands;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

import friend.capybara.command.Command;
import friend.capybara.utils.CapyLogger;
import friend.capybara.utils.file.FileManager;

public class CmdSearch extends Command {

    @Override
    public String getAlias() { return "search"; }

    @Override
    public String getDescription() { return "Manages Search blocks"; }

    @Override
    public String getSyntax() { return "search | search add | search remove | search clear"; }

    @Override
    public void onCommand(String command, String[] args) throws Exception {

        if (args[0].equalsIgnoreCase("")) CapyLogger.infoMessage(getSyntax());
        else if (args[0].equalsIgnoreCase("add")) {
            // mojang moment
            if (Registry.BLOCK.get(new Identifier(args[1])) == Blocks.AIR) {
                CapyLogger.infoMessage("Invalid block!");
                return;
            }
            for (String line : FileManager.readFileLines("SearchBlocks.txt")) {
                if (line.equalsIgnoreCase(args[1])) {
                    CapyLogger.errorMessage(args[1] + " has already been added");
                    return;
                }
            }
            FileManager.appendFile(args[1], "SearchBlocks.txt");
        }
        else if (args[0].equalsIgnoreCase("remove")) {
            List<String> lines = FileManager.readFileLines("SearchBlocks.txt");
            if (lines.isEmpty()) return;
            lines.remove(args[1]);
            FileManager.deleteFile("SearchBlocks.txt");
            FileManager.createEmptyFile("SearchBlocks.txt");
            for (String s : lines) FileManager.appendFile(s, "SearchBlocks.txt");
        }
        else if (args[0].equalsIgnoreCase("clear")) {
            FileManager.deleteFile("SearchBlocks.txt");
            FileManager.createEmptyFile("SearchBlocks.txt");
        }
    }
}
