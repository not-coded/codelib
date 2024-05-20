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

    public UUID uuid;
    public String name;

    public AccuratePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public AccuratePlayer(@NotNull ServerPlayer player) {
        this.uuid = player.getUUID();
        this.name = player.getScoreboardName();
    }

    /**
     * @deprecated
     * @see #AccuratePlayer(ServerPlayer)
     */

    public static AccuratePlayer create(@NotNull ServerPlayer player) {
        return create(player.getUUID(), player.getScoreboardName());
    }

    /**
     * @deprecated
     * @see #AccuratePlayer(UUID, String)
     */
    public static AccuratePlayer create(UUID uuid, String name) {
        return new AccuratePlayer(uuid, name);
    }

    /**
     * Gets player from the server.
     * @return Accurate player.
     */
    public ServerPlayer get() {
        if(this.uuid == null) return null;
        ServerPlayer player = CodeLib.server.getPlayerList().getPlayer(this.uuid);

        if(player == null) return null;

        if(this.name.isEmpty() && !player.getScoreboardName().isEmpty()) this.name = player.getScoreboardName();
        return player;
    }
}
