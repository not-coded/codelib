package net.notcoded.codelib.util.world.structure;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.notcoded.codelib.server.ServerTime;
import net.notcoded.codelib.util.world.WorldUtil;

/**
 * Uses Structure Blocks to paste a map using /setblock.
 */
public class StructureMap {

    // Default
    public ResourceLocation id;
    public Rotation rotation;
    public boolean cleanUp;

    // Parameters pre-set (without map)
    public BlockPos placePos;

    public BlockPos pastePos;

    public boolean forceLoad;

    public StructureMap(ResourceLocation structureId, Rotation rotation, boolean cleanUp) {
        this.id = structureId;
        this.rotation = rotation;
        this.cleanUp = cleanUp;
    }

    public StructureMap(ResourceLocation structureId, Rotation rotation, boolean cleanUp, BlockPos placePos, BlockPos pastePos, boolean forceLoad) {
        this.id = structureId;
        this.rotation = rotation;
        this.cleanUp = cleanUp;

        this.placePos = placePos;
        this.pastePos = pastePos;
        this.forceLoad = forceLoad;
    }


    /**
     * Pastes a map.
     * @param level The level where is going to get pasted in.
     * @param placePos Where the structure block is going to get placed in.
     * @param pastePos Where the map is going to get pasted in.
     * @param forceLoad If the chunk should get force loaded.
     */
    public void pasteMap(ServerLevel level, BlockPos placePos, BlockPos pastePos, boolean forceLoad) {
        String stringRotation = this.rotation.id;
        ResourceLocation name = WorldUtil.getWorldName(WorldUtil.getWorldName(level));
        String start = "execute in " + name.getNamespace() + ":" + name.getPath();


        if(forceLoad) ServerTime.runCommand(start + " run forceload add 0 0");

        if(!stringRotation.trim().isEmpty() && this.rotation != Rotation.NO_ROTATION) {
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s,rotation:\"%s\"}", start, placePos.getX(), placePos.getY(), placePos.getZ(), this.id.getNamespace(), this.id.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ(), stringRotation));
        } else if(this.rotation == Rotation.NO_ROTATION){
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s}", start, placePos.getX(), placePos.getY(), placePos.getZ(), this.id.getNamespace(), this.id.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ()));
        }

        if(this.cleanUp) {
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:redstone_block", start, placePos.getX() + 1, placePos.getY(), placePos.getZ()));

            ServerTime.runCommand(String.format("%s if block %s %s %s minecraft:structure_block run setblock %s %s %s air", start, placePos.getX(), placePos.getY(), placePos.getZ(), placePos.getX(), placePos.getY(), placePos.getZ()));
            ServerTime.runCommand(String.format("%s if block %s %s %s minecraft:redstone_block run setblock %s %s %s air", start, placePos.getX() + 1, placePos.getY(), placePos.getZ(), placePos.getX() + 1, placePos.getY(), placePos.getZ()));
        }
    }

    /**
     * Pastes a map with some pre-set parameters.
     * @param level The level where is going to get pasted in.
     */
    public void pasteMap(ServerLevel level) {
        String stringRotation = this.rotation.id;
        ResourceLocation name = WorldUtil.getWorldName(WorldUtil.getWorldName(level));
        String start = "execute in " + name.getNamespace() + ":" + name.getPath();

        ResourceLocation ResourceLocation = this.id;
        BlockPos placePos = this.placePos;
        BlockPos pastePos = this.pastePos;
        boolean forceLoad = this.forceLoad;

        if(forceLoad) ServerTime.runCommand(start + " run forceload add 0 0");

        if(!stringRotation.trim().isEmpty() && this.rotation != Rotation.NO_ROTATION) {
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s,rotation:\"%s\"}", start, placePos.getX(), placePos.getY(), placePos.getZ(), ResourceLocation.getNamespace(), ResourceLocation.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ(), stringRotation));
        } else if(this.rotation == Rotation.NO_ROTATION){
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s}", start, placePos.getX(), placePos.getY(), placePos.getZ(), ResourceLocation.getNamespace(), ResourceLocation.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ()));
        }

        if(this.cleanUp) {
            ServerTime.runCommand(String.format("%s run setblock %s %s %s minecraft:redstone_block", start, placePos.getX() + 1, placePos.getY(), placePos.getZ()));

            ServerTime.runCommand(String.format("%s if block %s %s %s minecraft:structure_block run setblock %s %s %s air", start, placePos.getX(), placePos.getY(), placePos.getZ(), placePos.getX(), placePos.getY(), placePos.getZ()));
            ServerTime.runCommand(String.format("%s if block %s %s %s minecraft:redstone_block run setblock %s %s %s air", start, placePos.getX() + 1, placePos.getY(), placePos.getZ(), placePos.getX() + 1, placePos.getY(), placePos.getZ()));
        }
    }

    /**
     * Returns a command which you can use in the console.
     * @param level The level where is going to get pasted in.
     * @return The command (/setblock ...).
     */
    public String returnCommand(ServerLevel level) {
        ResourceLocation name = WorldUtil.getWorldName(WorldUtil.getWorldName(level));
        String start = "execute in " + name.getNamespace() + ":" + name.getPath();

        ResourceLocation ResourceLocation = this.id;
        BlockPos placePos = this.placePos;
        BlockPos pastePos = this.pastePos;

        if(this.rotation != Rotation.NO_ROTATION) {
            return String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s,rotation:\"%s\"}", start, placePos.getX(), placePos.getY(), placePos.getZ(), ResourceLocation.getNamespace(), ResourceLocation.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ(), this.rotation.id);
        } else {
            return String.format("%s run setblock %s %s %s minecraft:structure_block{mode:'LOAD',name:'%s:%s',posX:%s,posY:%s,posZ:%s}", start, placePos.getX(), placePos.getY(), placePos.getZ(), ResourceLocation.getNamespace(), ResourceLocation.getPath(), pastePos.getX(), pastePos.getY(), pastePos.getZ());
        }
    }
}
