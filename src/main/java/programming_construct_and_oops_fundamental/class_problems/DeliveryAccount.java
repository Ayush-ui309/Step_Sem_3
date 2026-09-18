public class DeliveryAccount {
    private static String campusCode;
    private static int processedCount;
    private static int nullSkipped;
    private static int premiumCount;
    private static int regularCount;
    private static double grandTotalSurgeFees;
    private final String studentId;
    protected final double orderValue;
    static {
        campusCode = "CAMPUS-01";
        processedCount = 0;
        nullSkipped = 0;
        premiumCount = 0;
        regularCount = 0;
        grandTotalSurgeFees = 0.0;
    }
    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty() || orderValue < 0) {
            throw new IllegalArgumentException("Invalid delivery account");
        }
        this.studentId = studentId.trim();
        this.orderValue = orderValue;
    }
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }
    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay cannot be negative");
        }
        int firstTier = Math.min(delayMinutes, 5);
        int secondTier = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdTier = Math.max(delayMinutes - 15, 0);
        return orderValue * (firstTier * 0.005
                + secondTier * 0.01 + thirdTier * 0.02);
    }
    void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) return;
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        double fee = account.calculateSurgeFee(delayMinutes);
        boolean premium = account instanceof PremiumAccount;
        double chargedFee = premium ? fee * 0.5 : fee;
        double settledAmount = amount + chargedFee;
        processedCount++;
        grandTotalSurgeFees += chargedFee;
        if (premium) premiumCount++; else regularCount++;
        System.out.printf("%s | %s | settled: Rs %.1f | surge: Rs %.1f%n",
                account.studentId, premium ? "premium" : "regular",
                settledAmount, chargedFee);
    }
    public static void processBatch(DeliveryAccount[] accounts,
                                    double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null
                || accounts.length != amounts.length
                || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException(
                    "Parallel arrays must have equal lengths");
        }
        processedCount = 0;
        nullSkipped = 0;
        premiumCount = 0;
        regularCount = 0;
        grandTotalSurgeFees = 0.0;
        DeliveryAccount processor = new DeliveryAccount("BATCH");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
            } else {
                processor.processAccount(accounts[i], amounts[i],
                        delayMinutesArray[i]);
            }
        }
        System.out.println(processedCount + " processed | " + nullSkipped
                + " null skipped | " + premiumCount + " premium | "
                + regularCount + " regular");
        System.out.println("grand total surge fees = Rs "
                + grandTotalSurgeFees);
    }
    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500), null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};
        processBatch(accounts, amounts, delays);
    }
}
class PremiumAccount extends DeliveryAccount {
    PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}