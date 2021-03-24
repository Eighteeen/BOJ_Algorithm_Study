import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int[][] parpers;
    static final int ONE_LINE_CUT = 3;
    static final int[] PAPER_CASE_ARR = {-1, 0, 1};
    static int[] cntPapers = new int[ONE_LINE_CUT];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        parpers = new int[N][];
        for (int i = 0; i < N; i++) {
            parpers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        cutPaper(N, 0, 0);
        for (int cnt : cntPapers) sb.append(cnt).append("\n");
        System.out.print(sb);
        br.close();
    }

    static void cutPaper(int size, int row, int col) {
        if (isSamePapers(size, row, col)) {
            cntPaper(parpers[row][col]);
            return;
        }

        int cutSize = size / 3;
        for (int i = 0; i < size; i += cutSize) {
            for (int j = 0; j < size; j += cutSize) {
                cutPaper(cutSize, row + i, col + j);
            }
        }
    }
    
    static boolean isSamePapers(int size, int row, int col) {
        int standard = parpers[row][col];
        int checkRow = row + size, checkCol = col + size;
        for (int i = row; i < checkRow; i++) {
            for (int j = col; j < checkCol; j++) {
                if (parpers[i][j] != standard) return false;
            }
        }
        return true;
    }

    static void cntPaper(int paperNum) {
        for (int i = 0; i < ONE_LINE_CUT; i++) {
            if (PAPER_CASE_ARR[i] == paperNum) {
                cntPapers[i]++;
                return;
            }
        }
    }
}