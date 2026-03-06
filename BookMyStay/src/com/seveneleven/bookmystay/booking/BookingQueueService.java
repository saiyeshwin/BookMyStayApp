package com.seveneleven.bookmystay.booking;
import java.util.*;
public class BookingQueueService {
    private Queue<Reservation> bookingQueue;
    public BookingQueueService() {
        bookingQueue = new LinkedList<>();
    }
    
    public void addBookingRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
        System.out.println("Booking request added: " + reservation);
    }
    
    public void processBookings() {
        while (!bookingQueue.isEmpty()) {
            Reservation current = bookingQueue.poll();
            System.out.println("Processing: " + current);
            try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            System.out.println("Completed booking for: " + current.getGuestName());
        }
    }
}
