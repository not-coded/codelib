package net.notcoded.codelib.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;

@Environment(EnvType.SERVER)
public class CodeLibServer implements DedicatedServerModInitializer {
    public static MinecraftServer server;

    @Override
    public void onInitializeServer() {
    }
}
