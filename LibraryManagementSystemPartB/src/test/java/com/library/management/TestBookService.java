package com.library.management;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class TestBookService {
    private BookService bookService;

    @Before
    public void setUp() {
        IBookDao bookDao = new BookDaoFileImpl();
        bookService = new BookService(bookDao);
    }

    @Test
    public void testAddAndGetBook() {
        Book book = new Book(1, "Title", "Author", 2021);
        bookService.addBook(book);
        Book retrievedBook = bookService.getBook(1);
        assertEquals(book.getId(), retrievedBook.getId());
        assertEquals(book.getTitle(), retrievedBook.getTitle());
        assertEquals(book.getAuthor(), retrievedBook.getAuthor());
        assertEquals(book.getYear(), retrievedBook.getYear());
    }

    // בדיקות נוספות למחלקת השירות
}
