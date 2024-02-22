package entities;

import enums.Status;

public class Book {
    private int id;
    private String title;
    private String description;
    private String author;
    private int data;
    private Status status;
    private int employeeId;


    public Book(int id, String title, String description, String author, int data, Status status, int employeeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.data = data;
        this.status = status;
        this.employeeId = employeeId;
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
