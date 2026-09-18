import java.util.ArrayList;
import java.util.List;
public class WordFrequency {
    static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        String cleaned = feedback.toLowerCase().replace(",", "").replace(".", "");
        String[] words = cleaned.split(" ");
        List<String> wordList = new ArrayList<String>();
        List<Integer> countList = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals("")) {
                continue;
            }
            boolean isStopWord = false;
            for (int j = 0; j < stopWords.length; j++) {
                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                }
            }
            if (isStopWord) {
                continue;
            }
            int index = wordList.indexOf(word);
            if (index == -1) {
                wordList.add(word);
                countList.add(1);
            } else {
                countList.set(index, countList.get(index) + 1);
            }
        }
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = 0; j < wordList.size() - 1 - i; j++) {
                if (countList.get(j) < countList.get(j + 1)) {
                    int tempCount = countList.get(j);
                    countList.set(j, countList.get(j + 1));
                    countList.set(j + 1, tempCount);
                    String tempWord = wordList.get(j);
                    wordList.set(j, wordList.get(j + 1));
                    wordList.set(j + 1, tempWord);
                }
            }
        }
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i) + ": " + countList.get(i));
        }
    }
    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}