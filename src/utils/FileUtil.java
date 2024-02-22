package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Book;
import entities.Employee;
import entities.Journal;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private FileUtil() {
    }

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path BOOKS_PATH = Paths.get("data/books.json");
    private static final Path EMPLOYEES_PATH = Paths.get("data/employee.json");
    private static final Path JOURNAL_PATH = Paths.get("data/journal.json");


    public static List<Book> readBook() {
        try{
            String str = Files.readString(BOOKS_PATH);
            return GSON.fromJson(str, new TypeToken<List<Book>>() {
            }.getType());
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Employee> readEmployee()  {
       try {
           String str = Files.readString(EMPLOYEES_PATH);
           return GSON.fromJson(str, new TypeToken<List<Employee>>() {
           }.getType());
       } catch (IOException e){
           e.printStackTrace();
       }
       return new ArrayList<>();
    }

    public static List<Journal> readJournal(){
        try {
            String str = Files.readString(JOURNAL_PATH);
            return GSON.fromJson(str, new TypeToken<List<Journal>>() {
            }.getType());
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void writeJournal(List<Journal> tasks) {
        String json = GSON.toJson(tasks);
        try {
            Files.writeString(JOURNAL_PATH, json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}