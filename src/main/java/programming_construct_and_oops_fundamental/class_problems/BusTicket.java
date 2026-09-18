package main.java.programming_construct_and_oops_fundamental.class_problems;
import java.util.HashSet;
import java.util.Set;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isMeaningful(passengerName) || !isMeaningful(destination)) {
            throw new IllegalArgumentException("Invalid passenger or destination");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    private static boolean isMeaningful(String value) {
        return value != null && value.trim().matches("[A-Za-z]+(?:[ -][A-Za-z]+)*");
    }

    public void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Ticket is already checked in");
        }
        checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        Set<String> acceptedPairs = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        if (rawBookings != null) {
            for (String[] attempt : rawBookings) {
                if (attempt == null || attempt.length < 2) {
                    rejected++;
                    continue;
                }

                try {
                    BusTicket ticket = new BusTicket(attempt[0], attempt[1]);
                    String key = ticket.passengerName + "|" + ticket.destination;

                    if (acceptedPairs.contains(key)) {
                        duplicates++;
                    } else {
                        acceptedPairs.add(key);
                        ticket.markCheckedIn();
                        valid++;
                    }
                } catch (IllegalArgumentException ex) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates);
    }
}