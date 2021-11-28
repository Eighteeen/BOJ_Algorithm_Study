import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int[] CHESS_PIECES = {1, 1, 2, 2, 2, 8};
        int[] chessWhitePieces = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < CHESS_PIECES.length; i++) {
            sb.append(CHESS_PIECES[i] - chessWhitePieces[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}