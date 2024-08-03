package com.library.management;

import java.util.List;

public interface IBookDao {
    void addBook(Book book);
    Book getBook(int id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);
}
