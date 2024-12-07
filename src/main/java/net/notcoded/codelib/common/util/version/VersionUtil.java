package net.notcoded.codelib.common.util.version;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.util.Identifier;

public class VersionUtil {
    public static JsonElement parseString(String response) {
        //? if >=1.18 {
        return JsonParser.parseString(response);
        //?} elif <1.18 {
        /*return new JsonParser().parse(response);
        *///?}
    }

    public static Identifier identifier(String namespace, String path) {
        //? if >=1.21 {
        return Identifier.of(namespace, path);
        //?} elif <=1.20.1 {
        /*return new Identifier(namespace, path);
        *///?}
    }

    public static Identifier identifier(String id) {
        //? if >=1.21 {
        return Identifier.of(id);
        //?} elif <=1.20.1 {
        /*return new Identifier(id);
         *///?}
    }
}