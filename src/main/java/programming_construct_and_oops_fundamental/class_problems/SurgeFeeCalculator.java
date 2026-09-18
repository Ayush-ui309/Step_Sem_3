public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;
    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum rate cannot be negative");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Value and delay must be non-negative");
        }
        if (delayMinutes == 0) return 0.0;
        int firstTier = Math.min(delayMinutes, 5);
        int secondTier = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdTier = Math.max(delayMinutes - 15, 0);
        double tiered = orderValue * (firstTier * 0.005
                + secondTier * 0.01 + thirdTier * 0.02);
        double floor = orderValue * minimumSurgePercent / 100.0;
        return Math.max(tiered, floor);
    }
    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);
        System.out.println("0 min: Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("1 min: Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("16 min: Rs " + calculator.calculateSurgeFee(500, 16));
    }
}