import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int LAND_FLAG = 1;

        String input;
        while (!(input = br.readLine()).equals("0 0")) {
            String[] mapInfo = input.split(" ");
            int mapWidth = Integer.parseInt(mapInfo[0]);
            int mapHeight = Integer.parseInt(mapInfo[1]);

            Integer[][] landMap = new Integer[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                landMap[i] = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            }

            Graph graph = Graph.arrayFrom(landMap);
            sb.append(graph.getNumOfComponent(LAND_FLAG, dx, dy)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class Graph<T> {
    private T[][] graph;
    private int col, row;
    private boolean[][] isVisited;
    private T componentFlag;
    private int[] dx, dy;

    public static <T> Graph<T> arrayFrom(T[][] graphArr) {
        return new Graph(graphArr);
    }

    private Graph(T[][] graph) {
        col = graph.length;
        row = graph[0].length;
        this.graph = deepCopy(graph); //// 깊은 복사는 왜 한거에요?? : 22
    }

    public int getNumOfComponent(T componentFlag, int[] dx, int[] dy) {
        this.componentFlag = componentFlag;
        this.dx = dx;
        this.dy = dy;
        isVisited = new boolean[col][row];

        int cnt = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (graph[i][j] == componentFlag && !isVisited[i][j]) {
                    cnt++;
                    DFS(i, j);
                }
            }
        }

        return cnt;
    }

    private void DFS(int x, int y) {
        if (isOutOfArray(col, x) || isOutOfArray(row, y)) return;
        if (isVisited[x][y] || graph[x][y] != componentFlag) return;
        isVisited[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            DFS(nx, ny);
        }
    }

    private boolean isOutOfArray(int length, int index) {
        return index < 0 || index >= length;
    }

    private T[][] deepCopy(T[][] original) {
        T[][] result = (T[][]) new Object[col][row];

        for(int i = 0; i < col; i++){
            System.arraycopy(original[i], 0, result[i], 0, row);
        }

        return result;
    }
}
