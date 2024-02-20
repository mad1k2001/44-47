package lesson44;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataModel {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeDataModel() {
        employees.addAll(List.of(
                new Employee(0,"Иванов Иван Иванович", new ArrayList<>()),
                new Employee(1,"Петров Петр Петрович", new ArrayList<>()),
                new Employee(2,"Сидоров Сидор Сидорович", new ArrayList<>())
        ));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}