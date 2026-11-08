import java.util.*;

public class Main {
    static int prec(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }

    static void apply(Deque<Integer> nums, Deque<Character> ops) {
        char op = ops.pop();
        int b = nums.pop();
        int a = nums.pop();
        switch (op) {
            case '+': nums.push(a + b); break;
            case '-': nums.push(a - b); break;
            case '*': nums.push(a * b); break;
            case '/': nums.push(a / b); break;
        }
    }

    static int evaluate(String expr) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == ' ') continue;

            if (Character.isDigit(ch)) {
                int val = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = val * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                i--; // step back; the for loop will advance
                nums.push(val);
            } else if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                while (ops.peek() != '(') apply(nums, ops);
                ops.pop(); // discard '('
            } else { // + - * /
                while (!ops.isEmpty() && ops.peek() != '('
                        && prec(ops.peek()) >= prec(ch)) {
                    apply(nums, ops);
                }
                ops.push(ch);
            }
        }

        while (!ops.isEmpty()) apply(nums, ops);
        return nums.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));  // 12
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4")); // 4
    }
}
