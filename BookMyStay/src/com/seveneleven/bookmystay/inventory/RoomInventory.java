package com.seveneleven.bookmystay.inventory;
import java.util.*;

public class RoomInventory {
    public HashMap<String,Integer> roomType;
    public HashMap<String,Double> roomCost;
    public HashMap<String,List<String>> roomAmenities;

    public RoomInventory() {
        roomType = new HashMap<>();
        roomCost = new HashMap<>();
        roomAmenities = new HashMap<>();
    }
    
    public void addRoomType(String type, int count, double price, List<String> amenities) {
        roomType.put(type, count);
        roomCost.put(type, price);
        roomAmenities.put(type, amenities);
        System.out.println("Added:" + type);
        System.out.println("Count:" + count);
        System.out.println("Price:" + price);
        System.out.println("Amenities:" + amenities);
    }
     
    public void showAvailability() {
        System.out.println("\nCurrent Room Inventory:");
        for (String type : roomType.keySet()) {
            System.out.println("\nType:"+type);
            System.out.println("Available:"+roomType.get(type));
            System.out.println("Price:"+roomCost.get(type));
            System.out.println("Amenities:"+roomAmenities.get(type));
        }
    }
    
    public void updateRoomCount(String type, int newCount) {
        if (roomType.containsKey(type)) {
            roomType.put(type, newCount);
            System.out.println("Updated count for "+type+":"+newCount);
        } 
        else {
            System.out.println("Room type not found:"+type);
        }
    }

    public void updateRoomPrice(String type, double newPrice) {
        if (roomCost.containsKey(type)) {
            roomCost.put(type, newPrice);
            System.out.println("Updated price for "+type+":"+newPrice);
        } 
        else {
            System.out.println("Room type not found:"+type);
        }
    }
}
