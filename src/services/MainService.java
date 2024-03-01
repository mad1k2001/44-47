package services;

import dto.*;
import entities.Book;
import entities.Employee;
import enums.Status;
import utils.FileUtil;

import java.util.*;

public class MainService {
    private List<Book> books;
    private List<Employee> employees;

    public MainService() {
        this.books = FileUtil.readBook();
        this.employees = FileUtil.readEmployee();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public BookInfoDataModel getBookInfoDataModel(int id) {
        return getBookById(id).map(BookInfoDataModel::new).orElse(null);
    }

    public EmployeeInfoDataModel getEmployeeInfoDataModel(int id) {
        return getEmployeeById(id).map(EmployeeInfoDataModel::new).orElse(null);
    }

    public Optional<Book> getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst();
    }

    public boolean putBookToEmployee(Employee employee, Book book) {
        if (book.getStatus() == Status.ISSUED) {
            return false;
        }
        employee.getCurrentBooks().add(book);
        book.setStatus(Status.ISSUED);

        updateEmployeeInList(employee);
        updateBookInList(book);

        return true;
    }

    public boolean returnBookFromEmployee(Employee employee, Book book) {
        if (!employee.getCurrentBooks().contains(book)) {
            return false;
        }
        employee.getCurrentBooks().remove(book);
        employee.getPastBooks().add(book);

        book.setStatus(Status.FREE);

        updateEmployeeInList(employee);
        updateBookInList(book);

        return true;
    }

    public void registerUser(Employee user) {
        employees.add(user);
        FileUtil.writeEmployee(employees);
    }

    private void updateEmployeeInList(Employee employee) {
        employees.replaceAll(e -> e.getId() == employee.getId() ? employee : e);
        FileUtil.writeEmployee(employees);
    }

    private void updateBookInList(Book book) {
        books.replaceAll(b -> b.getId() == book.getId() ? book : b);
        FileUtil.writeBook(books);
    }
}

