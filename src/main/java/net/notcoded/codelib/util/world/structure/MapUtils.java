package net.notcoded.codelib.util.world.structure;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

import java.io.File;

@Environment(EnvType.SERVER)
public class MapUtils {
    public boolean deleteStructure(ResourceLocation identifier) {
        return new File(String.format("/world/generated/%s/structures", identifier.getNamespace()), identifier.getPath() + ".nbt").delete();
    }
}
