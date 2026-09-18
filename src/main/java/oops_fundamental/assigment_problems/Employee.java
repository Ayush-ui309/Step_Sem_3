public class Employee {
    private String empId, empName;
    private double salary;
    public Employee(String empId, String empName, double salary) {
        this.empId = empId; this.empName = empName; this.salary = salary;
    }
    public double getSalary() { return salary; }
    public static void main(String[] args) {
        Employee emp = new Employee("E101", "Plain", 40000);
        ManagerEmployee mgr = new ManagerEmployee("M102", "Manager", 70000, 8000);
        InternEmployee intern = new InternEmployee("I103", "Intern", 12000, 10000);
        Employee[] staff = {emp, mgr, intern};
        for (Employee e : staff) {
            if (e instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs " + ((ManagerEmployee) e).effectiveSalary());
            } else if (e instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs " + ((InternEmployee) e).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + e.getSalary());
            }
        }
    }
}
class ManagerEmployee extends Employee {
    private double teamBonus;
    public ManagerEmployee(String id, String name, double sal, double bonus) {
        super(id, name, sal); this.teamBonus = bonus;
    }
    public double effectiveSalary() { return getSalary() + teamBonus; }
}
class InternEmployee extends Employee {
    private double stipendCap;
    public InternEmployee(String id, String name, double sal, double cap) {
        super(id, name, sal); this.stipendCap = cap;
    }
    public double effectiveSalary() { return Math.min(getSalary(), stipendCap); }
}