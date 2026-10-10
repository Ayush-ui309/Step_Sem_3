import java.util.*;

public class VehicleRentalSystem {

    static abstract class Vehicle {
        private String name;
        private boolean available = true;

        Vehicle(String name) {
            this.name = name;
        }

        abstract double calculateCharge(int days);

        String getName() {
            return name;
        }

        boolean isAvailable() {
            return available;
        }

        void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class StandardCar extends Vehicle {
        StandardCar(String name) {
            super(name);
        }

        double calculateCharge(int days) {
            return days * 50.0;
        }
    }

    static class LuxuryCar extends Vehicle {
        LuxuryCar(String name) {
            super(name);
        }

        double calculateCharge(int days) {
            return days * 100.0;
        }
    }

    static class SUV extends Vehicle {
        SUV(String name) {
            super(name);
        }

        double calculateCharge(int days) {
            return days * 80.0;
        }
    }

    static class Rental {
        private Vehicle vehicle;
        private int days;
        private boolean active;

        Rental(Vehicle vehicle, int days) {
            this.vehicle = vehicle;
            this.days = days;
            this.active = true;
        }

        void returnVehicle() {
            if (!active) {
                System.out.println(vehicle.getName() + " has already been returned.");
                return;
            }

            vehicle.setAvailable(true);
            active = false;
            System.out.println(vehicle.getName() + " returned. Now available.");
        }
    }

    static class RentalService {
        private List<Rental> rentals = new ArrayList<>();

        void rentVehicle(Vehicle vehicle, int days) {
            if (days <= 0) {
                System.out.println("Rental duration must be greater than zero.");
                return;
            }

            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getName() + " is not available.");
                return;
            }

            vehicle.setAvailable(false);
            Rental rental = new Rental(vehicle, days);
            rentals.add(rental);

            System.out.println(vehicle.getName() + " rented for " + days + " days.");
            System.out.printf("Total charge: $%.2f%n", vehicle.calculateCharge(days));
        }

        void returnVehicle(Vehicle vehicle) {
            for (Rental rental : rentals) {
                if (rental.vehicle == vehicle && rental.active) {
                    rental.returnVehicle();
                    return;
                }
            }

            System.out.println("No active rental found for " + vehicle.getName() + ".");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle luxuryCar = new LuxuryCar("Luxury Car A");
        Vehicle standardCar = new StandardCar("Standard Car B");

        service.rentVehicle(luxuryCar, 3);
        service.rentVehicle(standardCar, 5);
        service.rentVehicle(luxuryCar, 2);
        service.returnVehicle(luxuryCar);
        service.rentVehicle(luxuryCar, 2);
    }
}