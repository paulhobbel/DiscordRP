package me.paulhobbel.discordrp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtilities {
    private static Gson GSON = new GsonBuilder().create();

    public static <T> T getJsonFromFile(File file, Class<T> clazz) throws IOException, JsonSyntaxException {
        return GSON.fromJson(FileUtils.readFileToString(file, Charset.forName("UTF-8")), clazz);
    }
}
