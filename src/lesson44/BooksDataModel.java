package lesson44;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksDataModel {
    private List<Book> books = new ArrayList<>();

    public BooksDataModel() {
        books.addAll(List.of(
                new Book(0,"Tolkin", "Властелин Колец", "lalalala"),
                new Book(1,"Гарри Гаррисон", "Автостопом по галактике", "okookokokokok"),
                new Book(2,"Братья Стругацких", "Машина желаний", "hahahahaha"),
                new Book(3,"Сергей Лукьяненко, Ник Перумов", "Не время для драконов", "rararararar"),
                new Book(4,"Джоан Роулинг", "Гарри Поттер","Погрузись в волшебную страну с избранным мальчиком")
        ));
    }

    public List<Book> getBooks() {
        return books;
    }
}
