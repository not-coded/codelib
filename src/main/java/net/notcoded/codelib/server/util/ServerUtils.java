package net.notcoded.codelib.server.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.notcoded.codelib.server.CodeLibServer;

import java.util.ArrayList;
import java.util.List;

import static net.notcoded.codelib.server.CodeLibServer.server;

@Environment(EnvType.SERVER)
public class ServerUtils {

    public static void firstTick(MinecraftServer server) {
        CodeLibServer.server = server;
    }

    /**
     * Runs a command as the server (console).
     * @param command The command.
     */
    public static void runCommand(String command) {
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack(), command);
    }

    /**
     * Runs a command as the server (console) but with no output (no message in chat or console).
     * @param command The command.
     */
    public static void runSilencedCommand(String command) {
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack().withSuppressedOutput(), command);
    }

    /**
     * Runs multiple commands as the server (console).
     * @param commands The commands.
     */

    public static void runCommands(List<String> commands) {
        commands.forEach(ServerUtils::runCommand);
    }

    /**
     * Runs multiple commands as the server (console) but with no output (no message in chat or console).
     * @param commands The commands.
     */

    public static void runSilencedCommand(List<String> commands) {
        commands.forEach(ServerUtils::runCommand);
    }
}
