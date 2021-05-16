import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] byteMessages = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int byteMessage : byteMessages) {
            sb.append(unscrambledByteMessage(byteMessage)).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    static int unscrambledByteMessage(int byteMessage) {
        int unscrambledNum = 0;

        while (scrambledByteMessage(unscrambledNum) != byteMessage) {
            unscrambledNum++;
        }

        return unscrambledNum;
    }

    static int scrambledByteMessage(int num) {
        int shiftNum = num << 1;
        if (shiftNum >= 256) shiftNum -= 256;
        return num ^ shiftNum;
    }
}