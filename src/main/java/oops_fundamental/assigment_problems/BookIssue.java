package oops_fundamentals.assigment_problems;
public class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }
    double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
    }
    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0.0;
        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }
        return total;
    }
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Karan", 0),
            new BookIssue("DSA Handbook", "Divya", 21),
            new BookIssue("Design Patterns", "Meera", 9)
        };
        for (BookIssue b : issues) {
            String status = b.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(b.title + " " + b.daysOverdue + " days - " + status);
        }
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}