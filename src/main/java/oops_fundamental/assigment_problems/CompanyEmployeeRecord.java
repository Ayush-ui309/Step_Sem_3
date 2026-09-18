public class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;
    static int totalRecords = 0;
    CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }
    String fullProfile() {
        double pay = employee.getSalary();
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        }
        if (slot == null) {
            return name + " | Pay: Rs " + pay + " | No parking assigned";
        }
        return name + " | Pay: Rs " + pay + " | Slot: " + slot.slotNo;
    }
    public static void main(String[] args) {
        Employee manager = new ManagerEmployee("M102", "Manager", 70000, 8000);
        Employee plain = new Employee("E101", "Plain", 40000);
        Employee intern = new InternEmployee("I103", "Intern", 12000, 10000);
        ParkingSlot[] slots = {
            new ParkingSlot("A2", 1, 0),
            new ParkingSlot("A1", 1, 0)
        };
        ParkingSlot managerSlot = ParkingSlot.findAvailableSlot(slots);
        managerSlot.allot("M102");
        ParkingSlot plainSlot = ParkingSlot.findAvailableSlot(slots);
        plainSlot.allot("E101");
        CompanyEmployeeRecord[] records = {
            new CompanyEmployeeRecord("Divya", "M102", manager, managerSlot),
            new CompanyEmployeeRecord("Karan", "E101", plain, plainSlot),
            new CompanyEmployeeRecord("Meera", "I103", intern, null)
        };
        for (CompanyEmployeeRecord record : records) {
            System.out.println(record.fullProfile());
        }
        System.out.println("Total records: " + totalRecords);
    }
}
class Employee {
    private String empId, empName;
    private double salary;
    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }
    double getSalary() { return salary; }
}
class ManagerEmployee extends Employee {
    private double teamBonus;
    ManagerEmployee(String id, String name, double salary, double bonus) {
        super(id, name, salary);
        this.teamBonus = bonus;
    }
    double effectiveSalary() { return getSalary() + teamBonus; }
}
class InternEmployee extends Employee {
    private double stipendCap;
    InternEmployee(String id, String name, double salary, double cap) {
        super(id, name, salary);
        this.stipendCap = cap;
    }
    double effectiveSalary() { return Math.min(getSalary(), stipendCap); }
}
class ParkingSlot {
    String slotNo;
    int capacity, occupiedCount;
    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }
    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) return slot;
        }
        return null;
    }
}