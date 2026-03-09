package com.seveneleven.bookmystay.booking;
import java.util.*;
import com.seveneleven.bookmystay.inventory.RoomInventory;

public class ReservationAllocator {
    private Set<String> bookedRoomIds;
    private HashMap<String, Set<String>> roomAllocations;
    private RoomInventory inventory;
    private BookingHistory history; // keep reference to history

    public ReservationAllocator(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
        bookedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    public boolean confirmReservation(Reservation reservation) {
        String type = reservation.getRoomType();

        // Check availability
        if (!inventory.roomType.containsKey(type) || inventory.roomType.get(type) <= 0) {
            System.out.println("No rooms available for type: " + type);
            return false;
        }

        // Generate unique room ID
        String roomId = type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 6);

        // Ensure uniqueness
        if (bookedRoomIds.contains(roomId)) {
            System.out.println("Room ID exists. Retrying allocation");
            return confirmReservation(reservation);
        }

        // Allocate room
        bookedRoomIds.add(roomId);
        roomAllocations.computeIfAbsent(type, k -> new HashSet<>()).add(roomId);
        inventory.updateRoomCount(type, inventory.roomType.get(type) - 1);

        System.out.println("Reservation confirmed for " + reservation.getGuestName());
        System.out.println("Room Type: " + type);
        System.out.println("Room ID: " + roomId);

        // Add to history automatically
        history.addReservation(reservation);

        return true;
    }

    public void showAllocations() {
        System.out.println("\nCurrent Room Allocations:");
        for (String type : roomAllocations.keySet()) {
            System.out.println("Type: " + type + " → " + roomAllocations.get(type));
        }
    }
}
