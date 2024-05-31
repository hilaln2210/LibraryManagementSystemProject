package com.library.algorithm;

import java.util.PriorityQueue;

/**
 * מחלקה זו מממשת אלגוריתם לניהול הזמנות המבוסס על תור עדיפויות.
 */
public class PriorityBookingAlgorithm implements IAlgoBooking {
    private PriorityQueue<String> bookingQueue;

    /**
     * קונסטרקטור ליצירת אובייקט חדש של PriorityBookingAlgorithm.
     */
    public PriorityBookingAlgorithm() {
        this.bookingQueue = new PriorityQueue<>();
    }

    /**
     * מוסיף הזמנה לתור.
     * @param bookId מזהה הספר להזמנה
     */
    @Override
    public void addBooking(String bookId) {
        bookingQueue.add(bookId);
    }

    /**
     * מסיר הזמנה מהתור.
     * @param bookId מזהה הספר להסרה
     */
    @Override
    public void removeBooking(String bookId) {
        bookingQueue.remove(bookId);
    }

    /**
     * מחזיר את ההזמנה הבאה בתור מבלי להסיר אותה.
     * @return מזהה הספר הבא בתור
     */
    @Override
    public String getNextBooking() {
        return bookingQueue.peek();
    }
}
