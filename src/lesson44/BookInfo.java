package lesson44;

import model.Book;

public class BookInfo {
    Book book;

    public BookInfo(){
        book = new Book(4, "Джоан Роулинг", "Гарри Поттер");
    }

    public Book getBook(){
        return book;
    }
}
