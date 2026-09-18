public class TypingAccuracyChecker {
    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;
        int length = original.length();
        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i + 1;
            }
        }
        double accuracy = (matched / (double) length) * 100;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, length, accuracy);

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            char oc = original.charAt(firstMismatch - 1);
            char tc = typed.charAt(firstMismatch - 1);
            System.out.println(" | First Mismatch at position " + firstMismatch
                    + " ('" + oc + "' vs '" + tc + "')");
        }
    }
    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
    }
}