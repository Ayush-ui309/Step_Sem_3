public class srmStudent2 {
    String name;
    String regNo;
    int attendance;
    static String university = "SRMIST";
    static int admissionCount = 10;
    public srmStudent2(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA231100301" + admissionCount;
    }
    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }
    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 10));
    }
    public static void main(String[] args) {
        BrokenSrmStudent s1 = new BrokenSrmStudent("Ravi", "RA231100301011", 82);
        BrokenSrmStudent s2 = new BrokenSrmStudent("Meera", "RA231100301012", 74);
        System.out.println(s1.name);
        System.out.println(s2.name);
        srmStudent2 m1 = new srmStudent2("Ravi", 82);
        srmStudent2 m2 = new srmStudent2("Meera", 74);
        m1.printIdCard();
        m2.printIdCard();
        srmStudent2.printTotalAdmissions();
    }
}
class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;
    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}