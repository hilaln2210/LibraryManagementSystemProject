package com.library.model;

/**
 * מחלקה זו מייצגת ספר בספריה. היא מכילה מאפיינים כמו מזהה, כותרת ושם המחבר.
 */
public class Book {
    private String id;
    private String title;
    private String author;

    /**
     * קונסטרקטור ליצירת אובייקט חדש של Book.
     * @param id מזהה הספר
     * @param title כותרת הספר
     * @param author שם המחבר
     */
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
