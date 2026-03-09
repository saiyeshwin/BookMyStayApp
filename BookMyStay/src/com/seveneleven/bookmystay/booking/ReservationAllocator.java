package com.seveneleven.bookmystay.booking;
import java.util.*;
import com.seveneleven.bookmystay.inventory.RoomInventory;
public class ReservationAllocator {
    private Set<String> bookedRoomIds;
    private HashMap<String, Set<String>> roomAllocations;
    private RoomInventory inventory;
    public ReservationAllocator(RoomInventory inventory) {
        this.inventory = inventory;
        bookedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    public boolean confirmReservation(Reservation reservation) {
        String type = reservation.getRoomType();

        if (!inventory.roomType.containsKey(type) || inventory.roomType.get(type) <= 0) {
            System.out.println("No rooms available for type: " + type);
            return false;
        }

        String roomId = type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 6);
        if (bookedRoomIds.contains(roomId)) {
            System.out.println("Room ID exists. Retrying allocation");
            return confirmReservation(reservation);
        }
        
        bookedRoomIds.add(roomId);
        roomAllocations.computeIfAbsent(type, k -> new HashSet<>()).add(roomId);
        inventory.updateRoomCount(type, inventory.roomType.get(type) - 1);
        System.out.println("Reservation confirmed for " + reservation.getGuestName());
        System.out.println("Room Type:" + type);
        System.out.println("Room ID: " + roomId);
        return true;
    }

    public void showAllocations() {
        System.out.println("\nCurrent Room Allocations:");
        for (String type : roomAllocations.keySet()) {
            System.out.println("Type: " + type + " → " + roomAllocations.get(type));
        }
    }
}
