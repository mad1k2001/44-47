package lesson44;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksDataModel {
    private List<Book> books = new ArrayList<>();

    public BooksDataModel() {
        books.addAll(List.of(
                new Book(0,"Tolkin", "Властелин Колец"),
                new Book(1,"Гарри Гаррисон", "Автостопом по галактике"),
                new Book(2,"Братья Стругацких", "Машина желаний"),
                new Book(3,"Сергей Лукьяненко, Ник Перумов", "Не время для драконов"),
                new Book(4,"Джоан Роулинг", "Гарри Поттер")
        ));
    }

    public List<Book> getBooks() {
        return books;
    }
}
