package net.notcoded.codelib.server.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.notcoded.codelib.server.CodeLibServer;

import java.util.List;

import static net.notcoded.codelib.server.CodeLibServer.server;

@Environment(EnvType.SERVER)
public class ServerUtils {

    public static void firstTick(MinecraftServer server) {
        CodeLibServer.server = server;
    }

    /**
     * Runs a command as the server (console).
     * Runs {@link #runCommand(String, boolean)} but with the giveFeedback parameter set to true.
     * @param command The command.
     * @return The command return value (1 is usually successful and 0 is unsuccessful) <STRONG>[WARNING ON >1.21: return value always returns 1]</STRONG>
     */
    public static int runCommand(String command) {
        return runCommand(command, true);
    }

    /**
     * Runs a command as the server (console).
     * @param command The command.
     * @param giveFeedback Whether it should output in console/to opped players.
     * @return The command return value (1 is usually successful and 0 is unsuccessful) <STRONG>[WARNING ON >1.21: return value always returns 1]</STRONG>
     */
    public static int runCommand(String command, boolean giveFeedback) {
        ServerCommandSource commandSourceStack = server.getCommandSource();
        if(!giveFeedback) commandSourceStack.withSilent();

        //? if <=1.18.2 {
        /*return server.getCommandManager().execute(commandSourceStack, command);
        *///?} else if >=1.19.4 {
        /*? if <=1.20.1 {*/  /*return *//*?}*/ server.getCommandManager().executeWithPrefix(commandSourceStack, command);
        //?}

        //? if >=1.21 {
        return 1;
        //?}
    }

    /**
     * Runs multiple commands as the server (console).
     * Runs {@link #runCommands(List, boolean)}} but with the {@code giveFeedback} parameter set to true.
     * @param commands The commands.
     */

    public static void runCommands(List<String> commands) {
        commands.forEach(ServerUtils::runCommand);
    }

    /**
     * Runs multiple commands as the server (console).
     * Runs {@link #runCommand(String, boolean)} for each {@code String} in {@code List<String>}.
     * @param commands The commands.
     * @param giveFeedback Whether it should output in console/to opped players.
     */

    public static void runCommands(List<String> commands, boolean giveFeedback) {
        commands.forEach(cmd -> runCommand(cmd, giveFeedback));
    }
}
