package net.notcoded.codelib.minecraft;

import com.google.gson.JsonParser;
import net.notcoded.codelib.util.http.HttpAPI;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

public class MinecraftAPI {

    public static HashMap<UUID, String> cachedNames = new HashMap<>();

    public static HashMap<String, UUID> cachedUUIDs = new HashMap<>();

    /**
     * Returns the UUID of a name.
     * @param name The name of the player, e.g. NotCoded
     * @return The uuid of the player, e.g. {@code fcbf27a9-535e-466f-ae75-7c7959fba7f0}
     */

    public static UUID getUUID(@NotNull String name) {
        if(name.trim().isEmpty() || name.length() < 3 || name.length() > 15) return null;

        if(cachedUUIDs.get(name) != null) return cachedUUIDs.get(name);

        String response;

        try {
            response = HttpAPI.get(new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s", name)));
        } catch(Exception ignored) { return null; }

        if(response != null && !response.trim().isEmpty())  {
            String uuid = new JsonParser().parse(response).getAsJsonObject().get("id").getAsString();
            if (uuid != null && !uuid.trim().isEmpty() && (uuid.length() == 32 || uuid.length() == 36)) {
                if(cachedUUIDs.get(name) != null) return cachedUUIDs.get(name);
                return UUID.fromString(uuid);
            }
        }
        return null;
    }

    /**
     * Returns the name of an UUID.
     * @param uuid The uuid of a player, e.g. fcbf27a9-535e-466f-ae75-7c7959fba7f0
     * @return The name of a player, e.g. {@code NotCoded}
     */

    public static String getName(@NotNull UUID uuid){
        if(cachedNames.get(uuid) != null) return cachedNames.get(uuid);

        String response;

        try {
            response = HttpAPI.get(new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s", uuid)));
        } catch(Exception ignored) { return null; }

        if(response != null && !response.trim().isEmpty()) {
            String name = new JsonParser().parse(response).getAsJsonObject().get("name").getAsString();

            if (name != null && !name.trim().isEmpty() && (name.length() > 2 && name.length() < 16)) {
                cachedUUIDs.put(name, uuid);
                return name;
            }
        }

        return null;
    }
}