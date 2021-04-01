import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    static String zipString;
    static int zipLen;
    static int checkIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        zipString = br.readLine();
        zipLen = zipString.length();
        System.out.println(unZipStringLen());
        
        br.close();
    }

    static int unZipStringLen() {
        Stack<Integer> compressStack = new Stack<>();

        int unZipRepeatLen = 0;
        while (checkIdx < zipLen) {
            char check = zipString.charAt(checkIdx++);
            if (check != '(' && check != ')') {
                compressStack.push(check - '0');
                continue;
            }

            if (check == '(') unZipRepeatLen += compressStack.pop() * unZipStringLen();
            else break;
        }

        return unZipRepeatLen + compressStack.size();
    }
}