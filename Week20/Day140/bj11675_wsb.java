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

    //// 형용사 형태(복호화된 바이트 메시지)보다는 동사 형태(바이트 메시지를 복호화한다)가 더 좋을 것 같습니다!
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