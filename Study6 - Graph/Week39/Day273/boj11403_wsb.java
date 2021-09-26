import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int graphSize;
    static boolean[][] isExistEdges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int EXIST_EDGE_FLAG = 1;

        graphSize = Integer.parseInt(br.readLine());
        isExistEdges = new boolean[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++) {
            int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < graphSize; j++) {
                isExistEdges[i][j] = (inputArr[j] == EXIST_EDGE_FLAG);
            }
        }

        setIsExistEdges();

        System.out.print(getStringOfIsExistEdges());
        br.close();
    }

    static void setIsExistEdges() {
        for (int k = 0; k < graphSize; k++) {
            for (int i = 0; i < graphSize; i++) {
                for (int j = 0; j < graphSize; j++) {
                    if (isExistEdges[i][j]) continue;
                    if (isExistEdges[i][k] && isExistEdges[k][j]) isExistEdges[i][j] = true;
                }
            }
        }
    }

    static String getStringOfIsExistEdges() {
        StringBuilder sb = new StringBuilder();

        for (boolean[] isExistEdgeLine : isExistEdges) {
            for (boolean isExist : isExistEdgeLine) {
                sb.append(isExist ? 1 : 0).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}