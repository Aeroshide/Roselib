package com.aeroshide.rose_bush.config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static Config instance;
    private final Path configPath;
    private Map<String, Object> data;

    /** @deprecated use Config.getInstance(Path) instead */
    @Deprecated
    public Config(String configFileName) throws IOException {
        this.configPath = Paths.get(configFileName).toAbsolutePath().normalize();
        loadConfig();
    }

    public Config(Path configPath) throws IOException {
        this.configPath = configPath;
        loadConfig();
    }

    public Object getOption(String key) {
        return data.get(key);
    }

    public void setOption(String key, Object value) throws IOException {
        data.put(key, value);
        saveConfig();
    }

    public boolean exists() {
        return Files.exists(configPath);
    }

    private void loadConfig() throws IOException {
        if (!Files.exists(configPath)) {
            data = new HashMap<>();
            saveConfig();
        } else {
            try (Reader reader = Files.newBufferedReader(configPath)) {
                Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
                data = new Gson().fromJson(reader, type);
            } catch (JsonSyntaxException e) {
                throw new IOException("Broken JSON in " + configPath, e);
            }
        }
    }

    private void saveConfig() throws IOException {
        Path parent = configPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (Writer writer = Files.newBufferedWriter(configPath,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(data, writer);
        }
    }

    @Deprecated
    public static Config getInstance(String configFileName) throws IOException {
        if (instance == null) {
            instance = new Config(configFileName);
        }
        return instance;
    }

    public static Config getInstance(Path configPath) throws IOException {
        if (instance == null || !instance.configPath.equals(configPath)) {
            instance = new Config(configPath);
        }
        return instance;
    }
}
