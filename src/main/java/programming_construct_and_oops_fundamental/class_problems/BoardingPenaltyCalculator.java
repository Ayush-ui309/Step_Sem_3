package main.java.programming_construct_and_oops_fundamental.class_problems;
public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum rate cannot be negative");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Fare and delay must be non-negative");
        }

        if (minutesLate == 0) return 0.0;

        int firstTier = Math.min(minutesLate, 5);
        int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTier = Math.max(minutesLate - 15, 0);

        double tiered = ticketFare * (firstTier * 0.005
                + secondTier * 0.01 + thirdTier * 0.02);

        double floor = ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(tiered, floor);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1.0);

        System.out.println("0 min: Rs " + calculator.calculatePenalty(1000, 0));
        System.out.println("1 min: Rs " + calculator.calculatePenalty(1000, 1));
        System.out.println("16 min: Rs " + calculator.calculatePenalty(1000, 16));
    }
}