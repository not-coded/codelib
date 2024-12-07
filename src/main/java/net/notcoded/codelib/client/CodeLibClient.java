package net.notcoded.codelib.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class CodeLibClient implements ClientModInitializer {
    public static MinecraftClient client;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();
    }
}
