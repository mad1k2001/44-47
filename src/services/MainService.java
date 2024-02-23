package services;

import dto.*;
import entities.Book;
import entities.Employee;
import entities.Journal;
import utils.FileUtil;

import java.util.List;

public class MainService {
    private List<Book> books;
    private List<Employee> employees;

    private List<Journal> journals;

    public MainService() {
        this.books = FileUtil.readBook();
        this.employees = FileUtil.readEmployee();
        this.journals = FileUtil.readJournal();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Journal> getJournals(){ return journals;
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

    public JournalDataModel getJournalDataModel(){
        return new JournalDataModel(journals);
    }

}
