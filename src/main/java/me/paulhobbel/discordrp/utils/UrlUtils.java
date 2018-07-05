package me.paulhobbel.discordrp.utils;

import com.google.gson.Gson;
import me.paulhobbel.discordrp.DiscordRP;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class UrlUtils {

    private static String USER_AGENT = DiscordRP.MODID + "/" + DiscordRP.VERSION;

    private static Gson gson = new Gson();

    private UrlUtils() {}

    public static InputStream openStream(String url) throws IOException {
        return openStream(new URL(url));
    }

    public static InputStream openStream(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("User-Agent", USER_AGENT);

        return connection.getInputStream();
    }

    public static <T> T fromJsonUrl(String url, Class<T> clazz) throws IOException {
        return fromJsonUrl(new URL(url), clazz);
    }

    public static <T> T fromJsonUrl(URL url, Class<T> clazz) throws IOException {
        InputStreamReader reader = new InputStreamReader(openStream(url));

        return gson.fromJson(reader, clazz);
    }
}
