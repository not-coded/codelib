package net.notcoded.codelib.common.util.version;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class VersionUtil {
    public static JsonElement parseString(String response) {
        //? if >=1.18 {
        return JsonParser.parseString(response);
        //?} elif <1.18 {
        /*return new JsonParser().parse(response);        *///?}
    }
}