package entities;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private static int nextId = 1;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Book> currentBooks;
    private List<Book> pastBooks;

    public Employee(String firstName, String lastName, String email, String password) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.currentBooks = new ArrayList<>();
        this.pastBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void updateNextId(List<Employee> employees) {
        nextId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
    }


    public List<Book> getPastBooks() {
        return pastBooks;
    }

    public void setPastBooks(List<Book> pastBooks) {
        this.pastBooks = pastBooks;
    }

    public List<Book> getCurrentBooks() {
        return currentBooks;
    }

    public void setCurrentBooks(List<Book> currentBooks) {
        this.currentBooks = currentBooks;
    }
}
