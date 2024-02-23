package entities;

import enums.Status;

public class Journal {
    private int id;
    private int employeeId;
    private int bookId;
    private Status status;
    private int date;

    public Journal(int id, int employeeId, int bookId, Status status, int date) {
        this.id = id;
        this.employeeId = employeeId;
        this.bookId = bookId;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
}
