package dto;

import entities.Book;

public class BookInfoDataModel {
    Book book;

    public BookInfoDataModel(Book book){
        this.book = book;
    }

    public Book getBook(){
        return book;
    }
}
