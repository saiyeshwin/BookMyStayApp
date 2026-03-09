package com.seveneleven.bookmystay.booking;
import java.util.*;

public class BookingQueueService {
    private Queue<Reservation> bookingQueue;

    public BookingQueueService() {
        bookingQueue = new LinkedList<>();
    }

    // Accept booking request
    public void addBookingRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
        System.out.println("Booking request added: " + reservation);
    }

    // Process requests in FIFO order
    public void processBookings(ReservationAllocator allocator) {
        while (!bookingQueue.isEmpty()) {
            Reservation current = bookingQueue.poll();
            System.out.println("Processing: " + current);
            try {
                Thread.sleep(2500); // simulate delay between guests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            allocator.confirmReservation(current); // allocator handles history internally
        }
    }
}
