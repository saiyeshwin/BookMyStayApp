// Use Case-01: Room Inventory Setup & Management
// Initialize room types (Single, Double, Suite)
// Store room counts and prices
// Supports dynamic inventory updates for price and number of rooms
// Provides availability status
// @author Developer
// @version 1.0
package com.seveneleven.bookmystay.main;
import java.util.*;
import com.seveneleven.bookmystay.inventory.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        System.out.println("Room Inventory");
        System.out.print("\nEnter count for Single rooms: ");
        int singleCount = sc.nextInt();
        System.out.print("Enter price for Single room: ");
        double singlePrice = sc.nextDouble();
        inventory.addRoomType("Single", singleCount, singlePrice);

        System.out.print("\nEnter count for Double rooms: ");
        int doubleCount = sc.nextInt();
        System.out.print("Enter price for Double room: ");
        double doublePrice = sc.nextDouble();
        inventory.addRoomType("Double", doubleCount, doublePrice);

        System.out.print("\nEnter count for Suite rooms: ");
        int suiteCount = sc.nextInt();
        System.out.print("Enter price for Suite room: ");
        double suitePrice = sc.nextDouble();
        inventory.addRoomType("Suite", suiteCount, suitePrice);

        boolean flag = true;
        while (flag) {
            System.out.println("\nBookMyStay Inventory");
            System.out.println("1.Show Availability");
            System.out.println("2.Update Room Count");
            System.out.println("3.Update Room Price");
            System.out.println("4.Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> inventory.showAvailability();
                case 2 -> {
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String typeCount = sc.nextLine();
                    System.out.print("Enter new count: ");
                    int newCount = sc.nextInt();
                    sc.nextLine();
                    inventory.updateRoomCount(typeCount, newCount);
                }
                case 3 -> {
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String typePrice = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    inventory.updateRoomPrice(typePrice, newPrice);
                }
                case 4 -> {
                    System.out.println("Exiting");
                    flag = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
