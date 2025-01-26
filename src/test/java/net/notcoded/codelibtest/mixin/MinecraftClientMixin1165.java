package net.notcoded.codelibtest.mixin;

import net.minecraft.client.MinecraftClient;
import net.notcoded.codelib.common.mixinhelper.annotation.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@MinecraftVersion(minecraft = {"1.16.5", "1.17.1"})
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin1165 {
    @Inject(at = @At("HEAD"), method = "run")
    private void init(CallbackInfo info) {
        // This code is injected into the start of MinecraftClient.run()V
        System.out.println("This mixin is running on the minecraft version 1.16.5 or on 1.17.1!");
    }
}
