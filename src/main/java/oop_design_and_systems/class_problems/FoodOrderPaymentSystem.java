package main.java.oop_design_and_systems.class_problems;

import java.util.*;

public class FoodOrderPaymentSystem {

    interface PaymentMethod {
        boolean pay(double amount);
        String getName();
    }

    static class CreditCardPayment implements PaymentMethod {
        public boolean pay(double amount) {
            return true;
        }

        public String getName() {
            return "Credit Card";
        }
    }

    static class DigitalWalletPayment implements PaymentMethod {
        public boolean pay(double amount) {
            return false;
        }

        public String getName() {
            return "Digital Wallet";
        }
    }

    static class CashOnDelivery implements PaymentMethod {
        public boolean pay(double amount) {
            return true;
        }

        public String getName() {
            return "Cash on Delivery";
        }
    }

    static class FoodItem {
        private String name;
        private double price;

        FoodItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        double getPrice() {
            return price;
        }
    }

    static class LineItem {
        private FoodItem foodItem;
        private int quantity;

        LineItem(FoodItem foodItem, int quantity) {
            this.foodItem = foodItem;
            this.quantity = quantity;
        }

        double getSubtotal() {
            return foodItem.getPrice() * quantity;
        }

        String getName() {
            return foodItem.getName();
        }

        int getQuantity() {
            return quantity;
        }
    }

    static class Customer {
        private String name;
        private List<Order> orders = new ArrayList<>();

        Customer(String name) {
            this.name = name;
        }

        Order createOrder(int orderId) {
            Order order = new Order(orderId, this);
            orders.add(order);
            return order;
        }

        String getName() {
            return name;
        }
    }

    static class Order {
        private int orderId;
        private Customer customer;
        private List<LineItem> items = new ArrayList<>();
        private String status = "Pending";

        Order(int orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            System.out.println("Order #" + orderId + " created.");
        }

        void addItem(FoodItem item, int quantity) {
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return;
            }

            items.add(new LineItem(item, quantity));
            System.out.println("Added " + item.getName() + " (Qty " + quantity + ").");
        }

        double calculateTotal() {
            double total = 0;

            for (LineItem item : items) {
                total += item.getSubtotal();
            }

            return total;
        }

        void placeOrder(PaymentMethod paymentMethod) {
            if (items.isEmpty()) {
                System.out.println("Cannot place order: Order must contain at least one item.");
                return;
            }

            System.out.println("Order #" + orderId + " placed successfully.");

            boolean success = paymentMethod.pay(calculateTotal());

            if (success) {
                status = "Paid";
                System.out.println("Payment via " + paymentMethod.getName() + " successful.");
                System.out.println("Order status: " + status);
                System.out.println("Notification: Order #" + orderId + " placed and paid.");
            } else {
                status = "Pending Payment";
                System.out.println("Payment via " + paymentMethod.getName() + " failed.");
                System.out.println("Order status: " + status);
                System.out.println("Notification: Order #" + orderId + " placed, awaiting payment.");
            }
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Ayush");

        Order order1 = customer.createOrder(123);
        order1.placeOrder(new CreditCardPayment());

        order1.addItem(new FoodItem("Pizza", 200), 2);
        order1.addItem(new FoodItem("Soda", 50), 1);
        order1.placeOrder(new CreditCardPayment());

        Order order2 = customer.createOrder(124);
        order2.addItem(new FoodItem("Burger", 100), 1);
        order2.placeOrder(new DigitalWalletPayment());
    }
}