package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileUtil<T> {
    private static final Gson gson = new GsonBuilder().create();
    private final Path path;

    public FileUtil(Path path) {
        this.path = path;
    }

    public void writeObjects(ArrayList<T> objects) {
        try (FileWriter writer = new FileWriter(path.toString())) {
            String json = gson.toJson(objects);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> readObjects() {
        try {
            String json = Files.readString(path);
            Type objectType = new TypeToken<ArrayList<T>>() {}.getType();
            return gson.fromJson(json, objectType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}