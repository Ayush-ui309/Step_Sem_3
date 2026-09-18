public class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;
    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }
    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }
    public double getDue() {
        return totalFee - amountPaid;
    }
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA101", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA102", 200000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA103", 180000, 20);
        plain.pay(150000);
        hostel.payInTwoInstallments(60000);
        FeeAccount[] accounts = {plain, hostel, scholarship};
        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + account.getDue());
            } else if (account instanceof ScholarshipFeeAccount) {
                System.out.println("Scholarship account effective due: Rs " + ((ScholarshipFeeAccount) account).effectiveDue());
            } else {
                System.out.println("Plain account due: Rs " + account.getDue());
            }
        }
    }
}
class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }
    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}
class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;
    public ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }
    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}