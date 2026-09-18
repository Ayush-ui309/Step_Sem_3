package main.java.programming_construct_and_oops_fundamental.class_problems;
public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty() || totalFare < 0
                || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare split");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        long cents = Math.round(totalFare * 100);
        long baseShare = cents / passengerCount;
        long remainder = cents % passengerCount;
        double[] shares = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++) {
            long share = baseShare + (i == passengerCount - 1 ? remainder : 0);
            shares[i] = share / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        if (confirmed < 0 || expected < 0 || confirmed > expected) {
            throw new IllegalArgumentException("Invalid confirmation counts");
        }

        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter split = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(java.util.Arrays.toString(split.fareBreakdown()));

        FareSplitter provisional = new FareSplitter("TRIP003");
        System.out.println(java.util.Arrays.toString(provisional.fareBreakdown()));
    }
}