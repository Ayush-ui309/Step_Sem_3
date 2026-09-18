public class WordReversal {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reversedWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord = reversedWord + word.charAt(j);
            }
            result = result + reversedWord;
            if (i < words.length - 1) {
                result = result + " ";
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }
}