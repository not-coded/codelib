package net.notcoded.codelib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class CodeLib implements ModInitializer {

    protected static EnvType type = EnvType.CLIENT;

    public static Minecraft client;

    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        try {
            loadClient();
        } catch(NoClassDefFoundError ignored){
            loadServer();
        }
    }

    protected void loadServer() {
        type = EnvType.SERVER;
    }

    protected void loadClient() {
        client = Minecraft.getInstance();
    }
}