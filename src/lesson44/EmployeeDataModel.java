package lesson44;

import model.Employee;
import model.Book; // Import the Book class if it's in a separate package

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataModel {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeDataModel() {
        List<Book> books = new ArrayList<>();
        employees.add(new Employee(0, "Иванов Иван Иванович", new ArrayList<>(books)));
        employees.add(new Employee(1, "Петров Петр Петрович", new ArrayList<>(books)));
        employees.add(new Employee(2, "Сидоров Сидор Сидорович", new ArrayList<>(books)));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}