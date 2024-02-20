package model;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private List<Book> issuedBooks;

    public Employee(int id, String name, List<Book> issuedBooks) {
        this.id = id;
        this.name = name;
        this.issuedBooks = issuedBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<Book> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}
