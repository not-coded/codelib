package net.notcoded.codelib.server;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;

public class ServerTime {
    public static MinecraftServer server;

    public static void firstTick(MinecraftServer server) {
        ServerTime.server = server;
    }


    /**
     * Runs a command as the server (console).
     * @param command The command.
     */
    public static void runCommand(String command) {
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack(), command);
    }

    /**
     * Runs multiple commands as the server (console).
     * @param commands The commands.
     */

    public static void runCommands(ArrayList<String> commands) {
        commands.forEach(ServerTime::runCommand);
    }
}
