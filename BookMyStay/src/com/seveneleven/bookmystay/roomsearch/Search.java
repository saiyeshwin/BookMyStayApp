package com.seveneleven.bookmystay.roomsearch;
import com.seveneleven.bookmystay.inventory.RoomInventory;
public class Search {
    private RoomInventory inventory;
    public Search(RoomInventory inventory) {
        this.inventory = inventory;
    }
    // Display available room types with price and amenities
    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (String type : inventory.roomType.keySet()) {
            int count = inventory.roomType.get(type);
            double price = inventory.roomCost.get(type);

            if (count > 0) {
                System.out.println("Type: " + type);
                System.out.println("Available: " + count);
                System.out.println("Price: " + price);
                System.out.println("Amenities: " + inventory.roomAmenities.get(type));
            }
        }
    }
    public boolean isRoomAvailable(String type) {
        return inventory.roomType.containsKey(type) && inventory.roomType.get(type) > 0;
    }
}
