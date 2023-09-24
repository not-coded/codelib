package net.notcoded.codelib.util.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.notcoded.codelib.CodeLib;

import java.util.ArrayList;

import static net.notcoded.codelib.CodeLib.server;

@Environment(EnvType.SERVER)
public class ServerUtils {

    public static void firstTick(MinecraftServer server) {
        CodeLib.server = server;
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
        commands.forEach(ServerUtils::runCommand);
    }
}
