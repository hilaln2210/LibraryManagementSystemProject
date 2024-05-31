package com.library.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * מחלקה זו מממשת את האלגוריתם "First Come First Serve" לניהול הזמנות בספריה.
 * האלגוריתם פועל לפי העיקרון של מי שהגיע ראשון מקבל שירות ראשון.
 */
public class FCFSAlgorithm implements IAlgoBooking {
    private Queue<String> bookingQueue;

    /**
     * קונסטרקטור ליצירת אובייקט חדש של FCFSAlgorithm.
     */
    public FCFSAlgorithm() {
        this.bookingQueue = new LinkedList<>();
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
