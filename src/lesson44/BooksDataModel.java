package lesson44;

import java.util.ArrayList;
import java.util.List;

public class BooksDataModel {
    private List<Book> books = new ArrayList<>();
    public BooksDataModel() {
        books.addAll(List.of(
                new Book("Tolkin", "Властелин Колец"),
                new Book("Гарри Гаррисон", "Автостопом по галактике"),
                new Book("Братья Стругацких", "Машина желаний"),
                new Book("Сергей Лукьяненко, Ник Перумов", "Не время для драконов"),
                new Book("Джоан Роулинг", "Гарри Поттер")
        ));
    }

    public List<Book> getBooks(){
        return books;
    }

    public static class Book{
        private String author;
        private String name;

        private Book(String author, String name) {
            this.author = author;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }
    }
}
