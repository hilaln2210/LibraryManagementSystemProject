package com.library.controller;

import com.library.algorithm.IAlgoBooking;

/**
 * מחלקה זו משמשת לניהול ההזמנות בספריה באמצעות האלגוריתם שנבחר.
 */
public class BookingManager {
    private IAlgoBooking bookingAlgorithm;

    /**
     * קונסטרקטור ליצירת אובייקט חדש של BookingManager.
     * @param bookingAlgorithm האלגוריתם לניהול ההזמנות
     */
    public BookingManager(IAlgoBooking bookingAlgorithm) {
        this.bookingAlgorithm = bookingAlgorithm;
    }

    /**
     * מוסיף הזמנה למערכת הניהול.
     * @param bookId מזהה הספר להזמנה
     */
    public void addBooking(String bookId) {
        bookingAlgorithm.addBooking(bookId);
    }

    /**
     * מסיר הזמנה מהמערכת הניהול.
     * @param bookId מזהה הספר להסרה
     */
    public void removeBooking(String bookId) {
        bookingAlgorithm.removeBooking(bookId);
    }

    /**
     * מחזיר את ההזמנה הבאה בתור מבלי להסיר אותה.
     * @return מזהה הספר הבא בתור
     */
    public String getNextBooking() {
        return bookingAlgorithm.getNextBooking();
    }
}


/*
חלק ב' – יצירת מחלקה לניהול הזמנות ויישום שלה
דרישות:

יצירת מחלקה לניהול הזמנות שמנהלת את ההזמנות תוך שימוש בממשק ובמימושים שיצרנו.
יישום שמראה את השימוש במחלקה לניהול הזמנות.
 */