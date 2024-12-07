package net.notcoded.codelib.common.util.pos;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPos {

    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public EntityPos(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public EntityPos(double x, double y, double z) {
        this(x, y, z, 0, 0);
    }

    public EntityPos(Entity entity) {
        this(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(0f), entity.getPitch(0f));
    }

    public EntityPos(BlockPos blockPos) {
        this(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public EntityPos(Vec3d vec3) {
        this(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public EntityPos clone() {
        return new EntityPos(this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public EntityPos c() {
        return this.clone();
    }

    public EntityPos add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public EntityPos add(EntityPos entityPos) {
        this.x += entityPos.x;
        this.y += entityPos.y;
        this.z += entityPos.z;
        return this;
    }

    public EntityPos between(EntityPos pos2) {
        return new EntityPos((this.x + pos2.x) / 2, (this.y + pos2.y) / 2, (this.z + pos2.z) / 2);
    }

    public BlockPos toBlockPos() {
        return new BlockPos((int) this.x, (int) this.y, (int) this.z);
    }

    public BlockVec3 toBlockVec3() {
        return new BlockVec3(this.x, this.y, this.z);
    }

    public double distance(EntityPos entityPos2) {
        double x = this.x - entityPos2.x;
        double y = this.y - entityPos2.y;
        double z = this.z - entityPos2.z;
        return Math.sqrt(x*x + y*y + z*z);
    }

    @Environment(EnvType.SERVER)
    public void teleportPlayer(ServerWorld level, ServerPlayerEntity player) {
        player.teleport(level, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    @Override
    public String toString() {
        return String.format("[x: %s, y: %s, z: %s, yaw: %s, pitch: %s]", this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public boolean isInRadius(EntityPos entityPos2, float radius) {
        double x = this.x - entityPos2.x;
        double y = this.y - entityPos2.y;
        double z = this.z - entityPos2.z;
        return x*x + y*y + z*z <= radius*radius;
    }

}
