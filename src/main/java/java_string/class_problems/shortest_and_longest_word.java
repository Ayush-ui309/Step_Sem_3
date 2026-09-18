public class shortest_and_longest_word {
    static String[] findShortestAndLongestWord(String text) {
        String[] words = text.split("\\s+");

        String shortest = words[0];
        String longest = words[0];

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < shortest.length()) {
                shortest = words[i];
            }
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }

        return new String[]{shortest, longest};
    }

    public static void main(String[] args) {
        String text = "Java is an object oriented programming language";
        String[] result = findShortestAndLongestWord(text);

        System.out.println("Shortest: \"" + result[0] + "\" (" + result[0].length()
                + ") | Longest: \"" + result[1] + "\" (" + result[1].length() + ")");
    }
}