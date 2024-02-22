package dto;

import entities.Book;

public class BookInfoDataModel {
    private Book book;

    public BookInfoDataModel(Book book){
        this.book = book;
    }

    public Book getBook(){
        return book;
    }
}
