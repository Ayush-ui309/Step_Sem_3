public class LibraryMemberFixed {
    String name, memberId;
    int booksIssued;
    static String libraryName = "Campus Library";
    static int memberCount = 1000;
    public LibraryMemberFixed(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }
    void printMemberCard() {
        System.out.println(name + " " + memberId);
    }
    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
    public static void main(String[] args) {
        LibraryMemberFixed m1 = new LibraryMemberFixed("Aditi", 2);
        LibraryMemberFixed m2 = new LibraryMemberFixed("Rohan", 1);
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMemberFixed.printTotalMembers();
    }
}