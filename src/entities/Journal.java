package entities;

import enums.Status;

public class Journal {
    private int id;
    private Employee employee;
    private Book book;
    private Status status;
    private int date;

    public Journal(int id, Employee employee, Book book, Status status, int date) {
        this.id = id;
        this.employee = employee;
        this.book = book;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int data) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
