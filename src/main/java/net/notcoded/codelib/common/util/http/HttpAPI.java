package net.notcoded.codelib.common.util.http;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpAPI {

    private static String userAgent = "Mozilla/5.0 (compatible; codelib; +https://github.com/not-coded/codelib)";

    /**
     * Same as {@link HttpAPI#get(URL)} but with the ability to set the User Agent.
     * @see HttpAPI#get(URL)
     */
    public static String get(@NotNull URL url, @NotNull String userAgent) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
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

    /**
     * Sends a GET request to an url.
     * @param url The url, e.g. <a href="https://notcoded.is-a.dev">https://notcoded.is-a.dev</a>
     * @return The response.
     */
    public static String get(@NotNull URL url) {
        return get(url, userAgent);
    }
}