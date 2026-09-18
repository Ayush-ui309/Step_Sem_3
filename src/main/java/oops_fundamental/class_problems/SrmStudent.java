public class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;
    static int totalStudents = 0;
    SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }
    String fullStatus() {
        String roomStatus = room == null ? "unallotted" : room.roomNo;
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStatus;
    }
    public static void main(String[] args) {
        HostelFeeAccount fee1 = new HostelFeeAccount("RA101", 200000);
        HostelFeeAccount fee2 = new HostelFeeAccount("RA102", 200000);
        HostelFeeAccount fee3 = new HostelFeeAccount("RA103", 200000);
        fee1.pay(60000);
        fee2.pay(20000);
        fee3.pay(-5000);
        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 1);
        room1.allot("Ravi");
        room2.allot("Anitha");
        SrmStudent s1 = new SrmStudent("Ravi", "RA101", fee1, room1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA102", fee2, room2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA103", fee3, null);
        SrmStudent[] students = {s1, s2, s3};
        for (SrmStudent student : students) {
            System.out.println(student.fullStatus());
        }
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;
    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }
    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }
    double getDue() {
        return totalFee - amountPaid;
    }
}
class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }
}
class HostelRoom {
    String roomNo;
    int beds;
    int occupied;
    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }
    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }
}