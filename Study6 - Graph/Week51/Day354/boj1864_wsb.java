import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while (!(input = br.readLine()).equals("#")) {
            char[] octopusNumChars = input.toCharArray();
            int len = octopusNumChars.length;

            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += Math.pow(8, len - i - 1) * octopusNumCharToInt(octopusNumChars[i]);
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int octopusNumCharToInt(char octopusNumChar) {
        switch (octopusNumChar) {
            case '-':
                return 0;
            case '\\':
                return 1;
            case '(':
                return 2;
            case '@':
                return 3;
            case '?':
                return 4;
            case '>':
                return 5;
            case '&':
                return 6;
            case '%':
                return 7;
            case '/':
                return -1;
            default:
                return 0;
        }
    }
}