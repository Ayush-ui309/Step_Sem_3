public class SrmStudent1 {
    String name;
    String regNo;
    int attendance;
    public SrmStudent1(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }
    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }
    boolean isEligible() {
        return attendance >= 75;
    }
    static double classAverage(SrmStudent1[] students) {
        int total = 0;
        for (SrmStudent1 student : students) {
            total += student.attendance;
        }
        return (double) total / students.length;
    }
    public static void main(String[] args) {
        SrmStudent1[] students = {
            new SrmStudent1("Ravi", "RA231100301011", 82),
            new SrmStudent1("Anitha", "RA231100301012", 68),
            new SrmStudent1("Karthik", "RA231100301013", 91),
            new SrmStudent1("Meera", "RA231100301014", 74),
            new SrmStudent1("Suresh", "RA231100301015", 60)
        };
        for (SrmStudent1 student : students) {
            String status = student.isEligible() ? "Eligible" : "Detained";
            System.out.println(student.name + " - " + student.attendance + "% - " + status);
        }
        System.out.println("Class average: " + SrmStudent1.classAverage(students) + "%");
    }
}