package net.notcoded.codelib.players;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.notcoded.codelib.CodeLib;
import net.notcoded.codelib.util.server.ServerUtils;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Environment(EnvType.SERVER)
public class AccuratePlayer {
    private static List<AccuratePlayer> accuratePlayerList = new ArrayList<>();

    public ServerPlayer player;
    public UUID uuid;

    private AccuratePlayer(UUID uuid) {
        this.uuid = uuid;
        this.player = CodeLib.server.getPlayerList().getPlayer(uuid);

        accuratePlayerList.add(this);
    }

    public static AccuratePlayer create(ServerPlayer player) {
        for(AccuratePlayer accuratePlayer : accuratePlayerList) {
            if(accuratePlayer.uuid.equals(player.getUUID())) return accuratePlayer;
        }

        return new AccuratePlayer(player.getUUID());
    }

    /**
     * Gets player from the server.
     * @return Accurate player.
     */
    public ServerPlayer get() {
        ServerPlayer player = CodeLib.server.getPlayerList().getPlayer(this.uuid);

        if(player == null) {
            accuratePlayerList.remove(this);
            return null;
        }

        if(this.player != player) this.player = player;
        return this.player;
    }
}
