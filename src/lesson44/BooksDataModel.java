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
        private boolean issued;
        private String issuedTo;

        private Book(String author, String name) {
            this.author = author;
            this.name = name;
            this.issued = false;
            this.issuedTo = null;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }

        public String getIssuedTo() {
            return issuedTo;
        }

        public void setIssuedTo(String issuedTo) {
            this.issuedTo = issuedTo;
        }
    }
}
