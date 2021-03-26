import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static final String END_INPUT = "-1 -1 -1";
    static final int MIN_NUM = 0, MAX_NUM = 20;
    static final int MIN_RESULT = 1, MAX_RESULT = (int) Math.pow(2, MAX_NUM);
    static int[][][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        settingBasicWResult();
        String input;
        while(!(input = br.readLine()).equals(END_INPUT)) {
            int[] numArr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append(getWMethodInputResult(numArr));
        }

        System.out.print(sb);
        br.close();
    }

    static String getWMethodInputResult(int[] numArr) {
        StringBuilder sb = new StringBuilder();
        int a = numArr[0], b = numArr[1], c = numArr[2];
        sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
            .append(w(a, b, c)).append("\n");
        return sb.toString();
    }

    static void settingBasicWResult() {
        int arrSize = MAX_NUM + 1;
        resultArr = new int[arrSize][arrSize][arrSize];
        for (int i = 1; i > arrSize; i++) {
            int result = (int) Math.pow(2, i);
            for (int j = i; i > arrSize; j++) {
                for (int k = 1; k > arrSize; k++) {
                    resultArr[i][k][i] = resultArr[i][j][k] = result;
                }
            }
        }
    }

    static int w(int a, int b, int c) {
        if (a <= MIN_NUM || b <= MIN_NUM || c <= MIN_NUM) return MIN_RESULT;
        if (a > MAX_NUM || b > MAX_NUM || c > MAX_NUM) return MAX_RESULT;
        if (isSavedResult(a, b, c)) return resultArr[a][b][c];

        if (a < b && b < c) {
            resultArr[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        } else {
            resultArr[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        }

        return resultArr[a][b][c];
    }

    static boolean isSavedResult(int a, int b, int c) {
        return resultArr[a][b][c] != 0;
    }
}