import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static final char RED = 'R', GREEN = 'G', BLUE = 'B';
    static int gridNum;
    static char[][] colors;
    static boolean[][] visited;

    static int dLen = 4;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        gridNum = Integer.parseInt(br.readLine());
        colors = new char[gridNum][];
        for (int i = 0; i < gridNum; i++) {
            colors[i] = br.readLine().toCharArray();
        }

        int cntRedArea = getNumOfColorArea(RED);
        int cntGreenArea = getNumOfColorArea(GREEN);
        int cntBlueArea = getNumOfColorArea(BLUE);

        int cntRedGreenArea = getNumOfColorsArea(RED, GREEN);

        sb.append(cntRedArea + cntGreenArea + cntBlueArea)
            .append(" ")
            .append(cntRedGreenArea + cntBlueArea);

        System.out.println(sb);
        br.close();
    }

    static int getNumOfColorArea(char color) {
        int cnt = 0;
        visited = new boolean[gridNum][gridNum];

        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                if (visited[i][j] || colors[i][j] != color) continue;
                visitColorArea(i, j, color);
                cnt++;
            }
        }
        return cnt;
    }

    static void visitColorArea(int x, int y, char color) {
        if (isOutOfSize(gridNum, x) || isOutOfSize(gridNum, y) || visited[x][y] || colors[x][y] != color) return;
        visited[x][y] = true;
        for (int i = 0; i < dLen; i++) {
            visitColorArea(x + dx[i], y + dy[i], color);
        }
    }

    static int getNumOfColorsArea(char color1, char color2) {
        int cnt = 0;
        visited = new boolean[gridNum][gridNum];

        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                if (visited[i][j] || (colors[i][j] != color1 && colors[i][j] != color2)) continue;
                visitColorsArea(i, j, color1, color2);
                cnt++;
            }
        }
        return cnt;
    }

    static void visitColorsArea(int x, int y, char color1, char color2) {
        if (isOutOfSize(gridNum, x) || isOutOfSize(gridNum, y) || visited[x][y] || (colors[x][y] != color1 && colors[x][y] != color2)) return;
        visited[x][y] = true;
        for (int i = 0; i < dLen; i++) {
            visitColorsArea(x + dx[i], y + dy[i], color1, color2);
        }
    }

    static boolean isOutOfSize(int size, int point) {
        return (point < 0 || point >= size);
    }
}