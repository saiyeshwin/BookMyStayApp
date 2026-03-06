// Use Case-03: Booking Request (First-Come-First-Served)
// Accept booking requests
// Enforce arrival order and give a gap of 2500ms between each booking
// @author Developer
// @version 3.0
package com.seveneleven.bookmystay.main;
import java.util.*;

import com.seveneleven.bookmystay.booking.*;
import com.seveneleven.bookmystay.inventory.*;
import com.seveneleven.bookmystay.roomsearch.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        BookingQueueService bookingService=new BookingQueueService();
        System.out.println("Room Inventory");
        System.out.print("\nEnter count for Single rooms: ");
        int singleCount = sc.nextInt();
        System.out.print("Enter price for Single room: ");
        double singlePrice = sc.nextDouble();
        System.out.print("Enter number of amenities for Single room: ");
        int singleAmenityCount = sc.nextInt();
        sc.nextLine(); 
        List<String> singleAmenities = new ArrayList<>();
        for (int i = 0; i < singleAmenityCount; i++) {
            System.out.print("Enter amenity " + (i+1) + ": ");
            singleAmenities.add(sc.nextLine());
        }
        inventory.addRoomType("Single", singleCount, singlePrice, singleAmenities);

        System.out.print("\nEnter count for Double rooms: ");
        int doubleCount = sc.nextInt();
        System.out.print("Enter price for Double room: ");
        double doublePrice = sc.nextDouble();
        System.out.print("Enter number of amenities for Double room: ");
        int doubleAmenityCount = sc.nextInt();
        sc.nextLine();
        List<String> doubleAmenities = new ArrayList<>();
        for (int i = 0; i < doubleAmenityCount; i++) {
            System.out.print("Enter amenity " + (i+1) + ": ");
            doubleAmenities.add(sc.nextLine());
        }
        inventory.addRoomType("Double", doubleCount, doublePrice, doubleAmenities);

        System.out.print("\nEnter count for Suite rooms: ");
        int suiteCount = sc.nextInt();
        System.out.print("Enter price for Suite room: ");
        double suitePrice = sc.nextDouble();
        System.out.print("Enter number of amenities for Suite room: ");
        int suiteAmenityCount = sc.nextInt();
        sc.nextLine();
        List<String> suiteAmenities = new ArrayList<>();
        for (int i = 0; i < suiteAmenityCount; i++) {
            System.out.print("Enter amenity " + (i+1) + ": ");
            suiteAmenities.add(sc.nextLine());
        }
        inventory.addRoomType("Suite", suiteCount, suitePrice, suiteAmenities);


        Search searchService = new Search(inventory);
        boolean flag = true;
        while (flag) {
            System.out.println("\nBookMyStay System");
            System.out.println("1.Show Inventory Availability (Admin)");
            System.out.println("2.Update Room Count (Admin)");
            System.out.println("3.Update Room Price (Admin)");
            System.out.println("4.Search Available Rooms (Guest)");
            System.out.println("5.Check Specific Room Availability (Guest)");
            System.out.println("6.Add Booking Request (Guest)");
            System.out.println("7.Process All Bookings");
            System.out.println("8.Exit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> inventory.showAvailability();
                case 2 -> {
                    System.out.print("Enter room type (Single/Double/Suite):");
                    String typeCount = sc.nextLine();
                    System.out.print("Enter new count:");
                    int newCount = sc.nextInt();
                    sc.nextLine();
                    inventory.updateRoomCount(typeCount, newCount);
                }
                case 3 -> {
                    System.out.print("Enter room type (Single/Double/Suite):");
                    String typePrice = sc.nextLine();
                    System.out.print("Enter new price:");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    inventory.updateRoomPrice(typePrice, newPrice);
                }
                case 4 -> searchService.showAvailableRooms();
                case 5 -> {
                    System.out.print("Enter room type to check:");
                    String typeCheck = sc.nextLine();
                    if (searchService.isRoomAvailable(typeCheck)) {
                        System.out.println(typeCheck+" is available for booking.");
                    } 
                    else {
                        System.out.println(typeCheck+" is not available");
                    }
                }
                case 6 -> {
                    System.out.print("Enter guest name: ");
                    String guestName = sc.nextLine();
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String roomType = sc.nextLine();
                    Reservation reservation = new Reservation(guestName, roomType);
                    
                    bookingService.addBookingRequest(reservation);
                }
                case 7 -> bookingService.processBookings();
                case 8 -> {
                    System.out.println("Exiting");
                    flag = false;
                }
                default -> System.out.println("Invalid choice. Please try again");
            }
        }
    }
}