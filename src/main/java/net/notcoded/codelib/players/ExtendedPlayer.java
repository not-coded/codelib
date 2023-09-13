package net.notcoded.codelib.players;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.notcoded.codelib.server.ServerTime;
import net.notcoded.codelib.util.pos.EntityPos;
import net.notcoded.codelib.util.item.ItemStackUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExtendedPlayer extends ServerPlayer {

    /**
     * Gives more functions to the player
     */

    public AccuratePlayer player;
    public ExtendedPlayer(AccuratePlayer player) {
        super(ServerTime.server, player.get().serverLevel(), player.get().getGameProfile());
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

    public void sendSound(SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch, long minVolume) {
        sendSound(Holder.direct(SoundEvent.createVariableRangeEvent(soundEvent.getLocation())), soundSource, volume, pitch, minVolume);
    }

    public void sendSound(Holder<SoundEvent> soundEvent, SoundSource soundSource, float volume, float pitch, long minVolume) {
        sendSound(new EntityPos(this.player.get()), soundEvent, soundSource, volume, pitch, minVolume);
    }

    public void sendSound(EntityPos position, Holder<SoundEvent> soundEvent, SoundSource soundSource, float volume, float pitch, long minVolume) {
        this.player.get().connection.send(new ClientboundSoundPacket(soundEvent, soundSource, position.x, position.y, position.z, 16f * volume, pitch, minVolume));
    }

    public void stopSound() {
        this.player.get().connection.send(new ClientboundStopSoundPacket(null, null));
    }

    public void sendTitle(String title, String sub, int in, int stay, int out) {
        ServerPlayer serverPlayer = this.player.get();

        serverPlayer.connection.send(new ClientboundSetTitlesAnimationPacket(in, stay, out));
        serverPlayer.connection.send(new ClientboundSetTitleTextPacket(
                Component.literal(title)));
        serverPlayer.connection.send(new ClientboundSetSubtitleTextPacket(
                Component.literal(sub)));
    }

    public void sendActionbar(String string){
        this.player.get().connection.send(new ClientboundSetActionBarTextPacket(
                Component.literal(string))
        );
    }

    public void sendDefaultTitleLength() {
        this.player.get().connection.send(new ClientboundSetTitlesAnimationPacket(10, 60, 20));
    }
}
