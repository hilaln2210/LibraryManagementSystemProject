package com.library.management;

import java.util.List;

public class BookService {
    private final IBookDao bookDao;

    public BookService(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }
}
