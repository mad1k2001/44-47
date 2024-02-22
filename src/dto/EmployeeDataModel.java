package dto;

import entities.Employee;

import java.util.List;

public class EmployeeDataModel {
    private List<Employee> employees;

    public EmployeeDataModel(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
