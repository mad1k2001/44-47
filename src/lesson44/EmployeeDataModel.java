package lesson44;
import lesson44.BooksDataModel.Book;
import java.util.List;

public class EmployeeDataModel {
    private String name;
    private List<Book> issuedBooks;

    public EmployeeDataModel(String name, List<Book> issuedBooks) {
        this.name = name;
        this.issuedBooks = issuedBooks;
    }
    public String getName() {
        return name;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void addIssuedBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }
}
