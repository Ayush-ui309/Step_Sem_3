public class Canteen {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        if (canteenCode == null || canteenCode.trim().isEmpty()
                || canteenName == null || canteenName.trim().isEmpty()
                || trustScore < 0 || trustScore > 5) {
            throw new IllegalArgumentException("Invalid canteen details");
        }
        this.canteenCode = canteenCode.trim();
        this.canteenName = canteenName.trim();
        this.trustScore = trustScore;
    }
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }
    public int compareTo(Canteen other) {
        int scoreOrder = Integer.compare(other.trustScore, trustScore);
        if (scoreOrder != 0) return scoreOrder;
        int codeOrder = canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeOrder != 0) return codeOrder;
        int lengthOrder = Integer.compare(canteenName.length(), other.canteenName.length());
        if (lengthOrder != 0) return lengthOrder;
        return canteenName.compareToIgnoreCase(other.canteenName);
    }
    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null) return new Canteen[0];
        Canteen[] ranked = new Canteen[canteens.length];
        for (int i = 0; i < canteens.length; i++) ranked[i] = canteens[i];
        for (int i = 1; i < ranked.length; i++) {
            Canteen current = ranked[i];
            int j = i - 1;
            while (j >= 0 && ranked[j].compareTo(current) > 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = current;
        }
        return ranked;
    }
}