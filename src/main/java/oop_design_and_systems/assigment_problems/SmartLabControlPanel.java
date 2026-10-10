package main.java.oop_design_and_systems.assigment_problems;

import java.util.*;

public class SwiftShipParcelTracker {

    enum ParcelStatus {
        BOOKED,
        PICKED_UP,
        IN_TRANSIT,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CANCELLED
    }

    interface ShippingMethod {
        double calculateCost(double weight);
        String getName();
    }

    static class StandardShipping implements ShippingMethod {
        public double calculateCost(double weight) {
            return 40 + 10 * weight;
        }

        public String getName() {
            return "Standard";
        }
    }

    static class ExpressShipping implements ShippingMethod {
        public double calculateCost(double weight) {
            return 80 + 15 * weight;
        }

        public String getName() {
            return "Express";
        }
    }

    interface NotificationService {
        void sendNotification(String message);
    }

    static class SMSNotification implements NotificationService {
        public void sendNotification(String message) {
            System.out.println("SMS: " + message);
        }
    }

    static class EmailNotification implements NotificationService {
        public void sendNotification(String message) {
            System.out.println("Email: " + message);
        }
    }

    static class Customer {
        private String name;
        private List<NotificationService> notifications = new ArrayList<>();

        Customer(String name) {
            this.name = name;
        }

        void subscribe(NotificationService notification) {
            notifications.add(notification);
        }

        void notifyCustomer(String message) {
            for (NotificationService notification : notifications) {
                notification.sendNotification(message);
            }
        }

        String getName() {
            return name;
        }
    }

    static class Parcel {
        private int trackingId;
        private Customer customer;
        private double weight;
        private ShippingMethod shippingMethod;
        private ParcelStatus status;

        Parcel(int trackingId, Customer customer, double weight,
                ShippingMethod shippingMethod) {
            this.trackingId = trackingId;
            this.customer = customer;
            this.weight = weight;
            this.shippingMethod = shippingMethod;
            this.status = ParcelStatus.BOOKED;
            customer.notifyCustomer("Parcel " + trackingId + " booked.");
        }

        double calculateCost() {
            return shippingMethod.calculateCost(weight);
        }

        void updateStatus(ParcelStatus newStatus) {
            if (status == ParcelStatus.CANCELLED || status == ParcelStatus.DELIVERED) {
                System.out.println("Parcel status cannot be changed.");
                return;
            }

            if (newStatus == ParcelStatus.CANCELLED) {
                if (status != ParcelStatus.BOOKED) {
                    System.out.println("Parcel can only be cancelled while BOOKED.");
                    return;
                }
            } else if (newStatus.ordinal() != status.ordinal() + 1) {
                System.out.println("Invalid status transition from " + status
                        + " to " + newStatus + ".");
                return;
            }

            status = newStatus;
            customer.notifyCustomer("Parcel " + trackingId + " status: " + status + ".");
        }

        void displayDetails() {
            System.out.println("\nTracking ID: " + trackingId);
            System.out.println("Customer: " + customer.getName());
            System.out.println("Shipping: " + shippingMethod.getName());
            System.out.printf("Shipping Cost: ₹%.2f%n", calculateCost());
            System.out.println("Status: " + status);
        }
    }

    static class FragileShipping implements ShippingMethod {
        private ShippingMethod baseShipping;

        FragileShipping(ShippingMethod baseShipping) {
            this.baseShipping = baseShipping;
        }

        public double calculateCost(double weight) {
            return baseShipping.calculateCost(weight) + 50;
        }

        public String getName() {
            return "Fragile " + baseShipping.getName();
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Ayush");
        customer.subscribe(new SMSNotification());
        customer.subscribe(new EmailNotification());

        ShippingMethod standard = new StandardShipping();
        ShippingMethod fragileStandard = new FragileShipping(standard);
        ShippingMethod express = new ExpressShipping();

        Parcel parcel1 = new Parcel(1001, customer, 2.0, fragileStandard);
        parcel1.displayDetails();

        parcel1.updateStatus(ParcelStatus.PICKED_UP);
        parcel1.updateStatus(ParcelStatus.IN_TRANSIT);
        parcel1.updateStatus(ParcelStatus.OUT_FOR_DELIVERY);
        parcel1.updateStatus(ParcelStatus.DELIVERED);
        parcel1.updateStatus(ParcelStatus.CANCELLED);

        Parcel parcel2 = new Parcel(1002, customer, 3.0, express);
        parcel2.displayDetails();
        parcel2.updateStatus(ParcelStatus.CANCELLED);
    }
}