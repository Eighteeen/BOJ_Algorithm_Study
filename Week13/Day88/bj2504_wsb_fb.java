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
        System.out.println(isAllChecked() ? calc : 0);

        br.close();
    }

    static int calcBrakets() {
        //// 사소한 디테일이지만 isAllChecked 어떤가요
        //// -> 의미가 더 명확하네요! 반영했어요.
        if (isAllChecked()) return 0;

        int calc = 1;
        Stack<Character> bracketStack = new Stack<>();
        while (true) {
            if (isAllChecked()) {
                isPossible = false;
                return 0;
            }

            char bracket = brackets.charAt(checkIdx);
            if (bracket == '(' || bracket == '[') {
                bracketStack.push(bracket);
                checkIdx++;
                continue;
            }

            if (bracketStack.isEmpty()) return 0;

            switch (bracket) {
                case ')':
                    if (bracketStack.peek() != '(') return 0;
                    calc *= 2;
                    break;
                case ']':
                    if (bracketStack.peek() != '[') return 0;
                    calc *= 3;
                    break;
            }
            bracketStack.pop();
            checkIdx++;
            calc += calcBrakets();

            if (bracketStack.isEmpty()) break;
        }

        return (isPossible ? calc + calcBrakets() : 0);
    }

    static boolean isAllChecked() {
        return checkIdx == brackets.length();
    }

    //// 함수 이름만 봤을땐 Item을 pop만 한다면서요! 같은지 비교도 하네요
    //// pop만 충실히 해주고 pop한 값을 반환해주는 함수로 쓰는건 어때요?
    //// -> 말씀하신대로 한다면 굳이 함수를 쓸 필요가 없어서 그냥 calcBrackets로 로직을 옮겨갔습니다.
}