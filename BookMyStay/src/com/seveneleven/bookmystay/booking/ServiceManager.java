package com.seveneleven.bookmystay.booking;
import java.util.*;
public class ServiceManager {
    private Map<String, List<Service>> reservationServices;
    public ServiceManager() {
        reservationServices = new HashMap<>();
    }
    public void addService(String reservationId, Service service) {
        if (!reservationServices.containsKey(reservationId)) {
            reservationServices.put(reservationId, new ArrayList<>());
        }
        reservationServices.get(reservationId).add(service);
        System.out.println("Added service: " + service + " to Reservation ID: " + reservationId);
    }
    public void showServices(String reservationId) {
        if (reservationServices.containsKey(reservationId)) {
            System.out.println("Services for Reservation " + reservationId + ":");
            List<Service> services = reservationServices.get(reservationId);
            for (Service s : services) {
                System.out.println("- " + s);
            }
        } else {
            System.out.println("No services attached for Reservation " + reservationId);
        }
    }
    public double calculateServiceCost(String reservationId) {
        double total = 0.0;
        if (reservationServices.containsKey(reservationId)) {
            List<Service> services = reservationServices.get(reservationId);
            for (Service s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}
