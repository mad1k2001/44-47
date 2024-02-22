package dto;

import entities.Book;
import entities.Employee;

public class EmployeeInfoDataModel {
     Employee employee;

    public EmployeeInfoDataModel(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }
}
