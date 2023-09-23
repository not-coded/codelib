package net.notcoded.codelib.players;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.notcoded.codelib.CodeLib;
import net.notcoded.codelib.server.ServerTime;
import net.minecraft.server.level.ServerPlayer;

@Environment(EnvType.SERVER)
public class AccuratePlayer {
    public ServerPlayer player;
    public AccuratePlayer(ServerPlayer player) {
        this.player = player;
    }

    /**
     * Gets player from the server.
     * @return Accurate player.
     */
    public ServerPlayer get() {
        ServerPlayer player = CodeLib.server.getPlayerList().getPlayer(this.player.getUUID());
        if(this.player != player) this.player = player;

        return this.player;
    }
}
