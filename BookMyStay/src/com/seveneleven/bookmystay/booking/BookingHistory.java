package com.seveneleven.bookmystay.booking;
import java.util.*;
public class BookingHistory {
    private List<Reservation> confirmedReservations;
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
        System.out.println("Reservation added to history: " + reservation);
    }
    public void cancelReservation(String guestName, String roomType) {
        boolean found = false;
        Iterator<Reservation> iterator = confirmedReservations.iterator();
        while (iterator.hasNext()) {
            Reservation r = iterator.next();
            if (r.getGuestName().equalsIgnoreCase(guestName) && r.getRoomType().equalsIgnoreCase(roomType)) {
                iterator.remove();
                System.out.println("Cancelled reservation: " + r);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No matching reservation found for cancellation.");
        }
    }
    public void showHistory() {
        System.out.println("\nBooking History Report:");
        if (confirmedReservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation r : confirmedReservations) {
                System.out.println(r);
            }
        }
    }
    public int getTotalReservations() {
        return confirmedReservations.size();
    }
}
