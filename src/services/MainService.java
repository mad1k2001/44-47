package services;

import dto.BookInfoDataModel;
import dto.BooksDataModel;
import dto.EmployeeDataModel;
import dto.EmployeeInfoDataModel;
import entities.Book;
import entities.Employee;
import utils.FileUtil;

import java.util.List;

public class MainService {
    private List<Book> books;
    private List<Employee> employees;

    public MainService() {
        this.books = FileUtil.readBook();
        this.employees = FileUtil.readEmployee();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public BooksDataModel getBooksDataModel() {
        return new BooksDataModel(books);
    }
    public EmployeeDataModel getEmployeeDataModel(){
        return new EmployeeDataModel(employees);
    }

    public BookInfoDataModel getBookInfoDataModel(){
        return new BookInfoDataModel(getBooksDataModel().getBooks().get(1));
    }

    public EmployeeInfoDataModel getEmployeeInfoDataModel(){
        return new EmployeeInfoDataModel(getEmployeeDataModel().getEmployees().get(0));
    }
}
