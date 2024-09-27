package net.notcoded.codelib.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

public class CodeLibClient implements ClientModInitializer {
    public static Minecraft client;

    @Override
    public void onInitializeClient() {
        client = Minecraft.getInstance();
    }
}
