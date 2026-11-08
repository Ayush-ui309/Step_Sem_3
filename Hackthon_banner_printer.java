import java.util.*;

public class Main {
    static String expandBanner(String code) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> texts = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;

        for (char ch : code.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                counts.push(k);
                texts.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                int times = counts.pop();
                StringBuilder prev = texts.pop();
                for (int i = 0; i < times; i++) prev.append(cur);
                cur = prev;
            } else {
                cur.append(ch);
            }
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));   // abababcc
        System.out.println(expandBanner("2[x3[y]]z"));   // xyyyxyyyz
    }
}
