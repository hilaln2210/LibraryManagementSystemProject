package com.library.management;

import com.library.controller.BookingManager;
import com.library.algorithm.FCFSAlgorithm;
import com.library.algorithm.PriorityBookingAlgorithm;

public class Main {
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
