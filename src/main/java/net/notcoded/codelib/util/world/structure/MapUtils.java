package net.notcoded.codelib.util.world.structure;

import net.minecraft.resources.ResourceLocation;

import java.io.File;

public class MapUtils {
    public boolean deleteStructure(ResourceLocation identifier) {
        return new File(String.format("/world/generated/%s/structures", identifier.getNamespace()), identifier.getPath() + ".nbt").delete();
    }
}
