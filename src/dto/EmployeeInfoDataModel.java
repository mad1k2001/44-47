package dto;

import entities.Book;
import entities.Employee;

public class EmployeeInfoDataModel {
    private Employee employee;

    public EmployeeInfoDataModel(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }
}
