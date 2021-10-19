import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 끔깔 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int GRID_SIZE = 9;

        int maxNum = 0;
        int maxNumRow = 0, maxNumCol = 0;

        for (int i = 0; i < GRID_SIZE; i++) {
            int[] grid = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[j] > maxNum) {
                    maxNum = grid[j];
                    maxNumRow = i + 1;
                    maxNumCol = j + 1;
                }
            }
        }

        //// StringBuilder는 습관되신건가요
        //// printf만 쓴거랑 차이 없을거같은데
        sb.append(maxNum)
            .append("\n")
            .append(maxNumRow)
            .append(" ")
            .append(maxNumCol);

        System.out.println(sb);
        br.close();
    }
}