package net.notcoded.codelib.util.http;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAPI {

    public static String userAgent = "Mozilla/5.0 (compatible; codelib; +https://github.com/not-coded/codelib)";


    /**
     * Sends a GET request to an url.
     * @param url The url, e.g. https://notcoded.is-a.dev/
     * @return The response.
     */
    public static String get(@NotNull URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (Exception ignored) { return null; }
    }
}