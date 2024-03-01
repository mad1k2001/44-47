package entities;

import enums.Status;

public class Book {
    private int id;
    private String title;
    private String description;
    private String author;
    private int data;
    private Status status;
    private String imageUrl;


    public Book(int id, String title, String description, String author, int data, Status status, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.data = data;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}