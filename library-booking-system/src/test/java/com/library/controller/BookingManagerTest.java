package com.library.controller;

import com.library.algorithm.FCFSAlgorithm;
import com.library.algorithm.PriorityBookingAlgorithm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * מחלקה זו מכילה בדיקות יחידה (unit tests) לאלגוריתמים השונים באמצעות JUnit.
 * הבדיקות מוודאות שהאלגוריתמים פועלים כראוי.
 */
public class BookingManagerTest {
    @Test
    public void testFCFSAlgorithm() {
        BookingManager manager = new BookingManager(new FCFSAlgorithm());
        manager.addBooking("1");
        manager.addBooking("2");
        assertEquals("1", manager.getNextBooking());
        manager.removeBooking("1");
        assertEquals("2", manager.getNextBooking());
    }

    @Test
    public void testPriorityBookingAlgorithm() {
        BookingManager manager = new BookingManager(new PriorityBookingAlgorithm());
        manager.addBooking("2");
        manager.addBooking("1");
        assertEquals("1", manager.getNextBooking());
        manager.removeBooking("1");
        assertEquals("2", manager.getNextBooking());
    }
}
