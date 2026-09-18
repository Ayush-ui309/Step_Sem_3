public class TrafficSignalStreak {
    static void findLongestStreak(String signalLog) {
        int maxCount = 1;
        char maxChar = signalLog.charAt(0);
        int currentCount = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentCount++;
            } else {
                currentCount = 1;
            }
            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxChar = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxCount + " times");
    }
    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
    }
}