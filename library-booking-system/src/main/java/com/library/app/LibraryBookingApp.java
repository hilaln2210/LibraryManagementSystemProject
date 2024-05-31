package com.library.app;

import com.library.algorithm.FCFSAlgorithm;
import com.library.algorithm.PriorityBookingAlgorithm;
import com.library.controller.BookingManager;

/**
 * מחלקה זו מכילה את נקודת הכניסה הראשית (main) של התוכנית.
 * היא יוצרת אובייקטים של BookingManager ומנהלת הזמנות באמצעות שני האלגוריתמים השונים (FCFSAlgorithm ו-PriorityBookingAlgorithm).
 */
public class LibraryBookingApp {
    public static void main(String[] args) {
        BookingManager fcfsManager = new BookingManager(new FCFSAlgorithm());
        BookingManager priorityManager = new BookingManager(new PriorityBookingAlgorithm());

        fcfsManager.addBooking("Book1");
        fcfsManager.addBooking("Book2");

        System.out.println("Next booking in FCFS: " + fcfsManager.getNextBooking());

        priorityManager.addBooking("Book3");
        priorityManager.addBooking("Book4");

        System.out.println("Next booking in Priority: " + priorityManager.getNextBooking());
    }
}
