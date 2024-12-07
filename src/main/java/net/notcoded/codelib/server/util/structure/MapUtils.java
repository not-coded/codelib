package net.notcoded.codelib.server.util.structure;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.io.File;

@Environment(EnvType.SERVER)
public class MapUtils {
    /**
     * Gets the world name of a level.
     * @param worldDirectoryName The world directory name (e.g. world [/<STRONG>world</STRONG>/...])
     * @param identifier The namespace (e.g. minecraft:overworld)
     * @return If the file was deleted successfully
     */
    public boolean deleteStructure(String worldDirectoryName, Identifier identifier) {
        return new File(String.format("/%s/generated/%s/structures", worldDirectoryName, identifier.getNamespace()), identifier.getPath() + ".nbt").delete();
    }
}
