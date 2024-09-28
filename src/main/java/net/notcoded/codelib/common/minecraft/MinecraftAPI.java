package net.notcoded.codelib.common.minecraft;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.notcoded.codelib.common.util.http.HttpAPI;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import static net.notcoded.codelib.common.util.version.VersionUtil.parseString;

public class MinecraftAPI {
    public static HashMap<String, String> cachedNames = new HashMap<>();

    public static HashMap<String, String> cachedUUIDs = new HashMap<>();

    /**
     * Returns the UUID of a name.
     * @param name The name of the player, e.g. NotCoded
     * @return The uuid of the player, e.g. {@code fcbf27a9-535e-466f-ae75-7c7959fba7f0}
     */

    public static String getUUID(String name) {
        if(name.trim().isEmpty()) return null;
        if(cachedUUIDs.containsKey(name.toLowerCase(Locale.ROOT))) return cachedUUIDs.get(name.toLowerCase(Locale.ROOT));

        String response;
        try {
            response = HttpAPI.get(new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s", name)));
        } catch (Exception ignored) {
            return null;
        }

        if (response == null || response.trim().isEmpty()) return null;

        JsonElement result = parseString(response);
        String uuid = result.getAsJsonObject().get("id").getAsString();

        if (uuid != null && !uuid.trim().isEmpty() && (uuid.length() == 32 || uuid.length() == 36)) {
            cachedNames.put(uuid, name.toLowerCase(Locale.ROOT));
            return uuid;
        }

        return response;
    }

    /**
     * Returns the name of an UUID.
     * @param uuid The uuid of a player, e.g. fcbf27a9-535e-466f-ae75-7c7959fba7f0
     * @return The name of a player, e.g. {@code NotCoded}
     */

    public static String getName(String uuid) {
        if (cachedNames.containsKey(uuid.toLowerCase(Locale.ROOT))) return cachedNames.get(uuid.toLowerCase(Locale.ROOT));

        String response;

        try {
            response = HttpAPI.get(new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s", uuid)));
        } catch (Exception ignored) {
            return null;
        }

        if (response == null || response.trim().isEmpty()) return null;

        JsonElement result = parseString(response);
        String name = result.getAsJsonObject().get("name").getAsString();

        if (name != null && !name.trim().isEmpty()) {
            cachedUUIDs.put(name, uuid.toLowerCase(Locale.ROOT));
            return name;
        }

        return null;
    }
}