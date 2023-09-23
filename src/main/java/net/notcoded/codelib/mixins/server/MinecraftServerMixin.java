package net.notcoded.codelib.mixins.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.notcoded.codelib.server.ServerTime;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
@Environment(EnvType.SERVER)
public class MinecraftServerMixin {
    @Unique
    boolean firstTickPassed = false;

    @Inject(at = @At("HEAD"), method = "tickChildren")
    private void tickHead(CallbackInfo ci) {
        if (!firstTickPassed) {
            firstTickPassed = true;
            ServerTime.firstTick((MinecraftServer) (Object) this);
        }
    }
}
