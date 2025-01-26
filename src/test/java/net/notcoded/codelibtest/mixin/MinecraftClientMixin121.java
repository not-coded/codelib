package net.notcoded.codelibtest.mixin;

import net.minecraft.client.MinecraftClient;
import net.notcoded.codelib.common.mixinhelper.annotation.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@MinecraftVersion(minecraft = {">=1.19", "<=1.21"})
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin121 {
    @Inject(at = @At("HEAD"), method = "run")
    private void init(CallbackInfo info) {
        // This code is injected into the start of MinecraftClient.run()V
        System.out.println("This mixin is running on the minecraft version that is above (or equal to) 1.19 but under (or equal to) 1.21");
    }
}
