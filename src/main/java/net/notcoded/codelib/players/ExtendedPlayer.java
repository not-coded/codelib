package net.notcoded.codelib.players;

import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.notcoded.codelib.server.ServerTime;
import net.notcoded.codelib.util.pos.EntityPos;
import net.notcoded.codelib.util.item.ItemStackUtil;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExtendedPlayer extends ServerPlayer {

    /**
     * Gives more functions to the player
     */

    public AccuratePlayer player;
    public ExtendedPlayer(AccuratePlayer player) {
        super(ServerTime.server, player.get().getLevel(), player.get().getGameProfile(), player.get().gameMode);
        this.player = player;
    }

    public void removeItem(Item item, int count) {
        for (ItemStack itemStack : ItemStackUtil.getInvItems(this.player.get())) {
            if (itemStack.getItem() != item) continue;

            if (itemStack.getCount() >= count) {
                itemStack.shrink(count);
                break;
            } else {
                count -= itemStack.getCount();
                itemStack.shrink(itemStack.getCount());
            }
        }
    }

    public void sendSound(SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch) {
        sendSound(new EntityPos(this.player.get()), soundEvent, soundSource, volume, pitch);
    }

    public void sendSound(EntityPos position, SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch) {
        this.player.get().connection.send(new ClientboundSoundPacket(soundEvent, soundSource,
                position.x, position.y, position.z, 16f * volume, pitch));
    }

    public void stopSound() {
        this.player.get().connection.send(new ClientboundStopSoundPacket());
    }

    public void sendTitle(String title, String sub, int in, int stay, int out) {
        ServerPlayer serverPlayer = this.player.get();

        serverPlayer.connection.send(new ClientboundSetTitlesPacket(in, stay, out));
        serverPlayer.connection.send(new ClientboundSetTitlesPacket(
                ClientboundSetTitlesPacket.Type.TITLE, new TextComponent(title)));
        serverPlayer.connection.send(new ClientboundSetTitlesPacket(
                ClientboundSetTitlesPacket.Type.SUBTITLE, new TextComponent(sub)));
    }

    public void sendActionbar(String string){
        this.player.get().connection.send(new ClientboundSetTitlesPacket(
                ClientboundSetTitlesPacket.Type.ACTIONBAR,
                new TextComponent(string))
        );
    }

    public void sendDefaultTitleLength() {
        this.player.get().connection.send(new ClientboundSetTitlesPacket(10, 60, 20));
    }
}
