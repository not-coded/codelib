package net.notcoded.codelib.players;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.notcoded.codelib.CodeLib;
import net.notcoded.codelib.util.server.ServerUtils;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Environment(EnvType.SERVER)
public class AccuratePlayer {

    private static final List<AccuratePlayer> accuratePlayerList = new ArrayList<>();

    public UUID uuid;
    public String name;

    private AccuratePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;

        accuratePlayerList.add(this);
    }

    public static AccuratePlayer create(@NotNull ServerPlayer player) {
        return create(player.getUUID(), player.getScoreboardName());
    }

    public static AccuratePlayer create(UUID uuid, String name) {
        for(AccuratePlayer accuratePlayer : accuratePlayerList) {
            if(accuratePlayer.uuid == null || accuratePlayer.get() == null) {
                accuratePlayerList.remove(accuratePlayer);
                continue;
            }

            if(accuratePlayer.uuid.equals(uuid)) return accuratePlayer;

        }

        return new AccuratePlayer(uuid, name);
    }

    /**
     * Gets player from the server.
     * @return Accurate player.
     */
    public ServerPlayer get() {
        if(this.uuid == null) {
            accuratePlayerList.remove(this);
            return null;
        }

        ServerPlayer player = CodeLib.server.getPlayerList().getPlayer(this.uuid);

        if(player == null) {
            accuratePlayerList.remove(this);
            return null;
        }

        if(this.name.isEmpty() && !player.getScoreboardName().isEmpty()) this.name = player.getScoreboardName();

        return player;
    }
}
