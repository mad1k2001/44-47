package model;

public class Book {
    private int id;
    private String author;
    private String name;
    private String description;
    private boolean issued;
    private String issuedTo;

    public Book(int id, String author, String name, String description) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.issued = false;
        this.issuedTo = null;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

