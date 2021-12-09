import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ! : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int BOARD_SIZE = 8;
        final char CHESS_OBJECT = 'F';

        int result = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int whiteBoardJudgment = i % 2;
            char[] boardState = br.readLine().toCharArray();
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (j % 2 == whiteBoardJudgment && boardState[j] == CHESS_OBJECT) {
                    result++;
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}