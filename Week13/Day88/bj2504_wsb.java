import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    static String brackets;
    static int checkIdx;
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        brackets = br.readLine();
        isPossible = true;
        int calc = calcBrakets();
        System.out.println(isAllCheck() ? calc : 0);

        br.close();
    }

    static int calcBrakets() {
        if (isAllCheck()) return 0;

        int calc = 1;
        Stack<Character> bracketStack = new Stack<>();
        while (true) {
            if (isAllCheck()) {
                isPossible = false;
                return 0;
            }

            char bracket = brackets.charAt(checkIdx);
            if (bracket == '(' || bracket == '[') {
                bracketStack.push(bracket);
                checkIdx++;
                continue;
            }

            switch (bracket) {
                case ')':
                    if (!popItem('(', bracketStack)) return 0;
                    calc *= 2;
                    break;
                case ']':
                    if (!popItem('[', bracketStack)) return 0;
                    calc *= 3;
                    break;
            }
            checkIdx++;
            calc += calcBrakets();

            if (bracketStack.isEmpty()) break;
        }

        return (isPossible ? calc + calcBrakets() : 0);
    }

    static boolean isAllCheck() {
        return checkIdx == brackets.length();
    }

    static boolean popItem(char item, Stack<Character> stack) {
        if (stack.isEmpty() || stack.peek() != item) return false;
        stack.pop();
        return true;
    }
}