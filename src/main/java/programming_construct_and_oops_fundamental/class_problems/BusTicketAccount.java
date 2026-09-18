package main.java.programming_construct_and_oops_fundamental.class_problems;
public class BusTicketAccount {
    private static String depotCode;
    private final String bookingId;
    protected final double ticketFare;
    private static int processedCount;
    private static int nullSkipped;
    private static int sleeperCount;
    private static int regularCount;
    private static double grandTotalPenalties;

    static {
        depotCode = "DEPOT-01";
        processedCount = 0;
        grandTotalPenalties = 0.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty() || ticketFare < 0) {
            throw new IllegalArgumentException("Invalid ticket account");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
        }

        int firstTier = Math.min(minutesLate, 5);
        int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTier = Math.max(minutesLate - 15, 0);

        return ticketFare * (firstTier * 0.005
                + secondTier * 0.01 + thirdTier * 0.02);
    }

    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) return;

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        double penalty = account.calculatePenalty(minutesLate);
        boolean sleeper = account instanceof SleeperAccount;
        double chargedPenalty = sleeper ? penalty * 0.5 : penalty;
        double settledAmount = amount + chargedPenalty;

        processedCount++;
        grandTotalPenalties += chargedPenalty;

        if (sleeper) sleeperCount++;
        else regularCount++;

        System.out.printf("%s | %s | settled: Rs %.1f | penalty: Rs %.1f%n",
                account.bookingId, sleeper ? "sleeper" : "regular",
                settledAmount, chargedPenalty);
    }

    public static void processBatch(BusTicketAccount[] accounts,
            double[] amounts, int[] minutesLateArray) {

        if (accounts == null || amounts == null || minutesLateArray == null
                || accounts.length != amounts.length
                || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Parallel arrays must have equal lengths");
        }

        processedCount = 0;
        nullSkipped = 0;
        sleeperCount = 0;
        regularCount = 0;
        grandTotalPenalties = 0.0;

        BusTicketAccount processor = new BusTicketAccount("BATCH");

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
            } else {
                processor.processAccount(accounts[i], amounts[i], minutesLateArray[i]);
            }
        }

        System.out.println(processedCount + " processed | " + nullSkipped
                + " null skipped | " + sleeperCount + " sleeper | "
                + regularCount + " regular");

        System.out.println("grand total penalties = Rs " + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        processBatch(accounts, amounts, minutesLate);
    }
}

class SleeperAccount extends BusTicketAccount {
    SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}