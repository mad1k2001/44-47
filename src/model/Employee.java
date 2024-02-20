package model;

import java.util.List;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name, List<Integer> issuedBooks) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
