import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final char EMPTY = '.', FENCE = '#';
    static final char SHEEP = 'o', WOLF = 'v';

    static int dLen = 4;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static int gardenRow, gardenCol;
    static char[][] gardenState;
    static boolean[][] visited;
    static int sheepCnt, wolfCnt;
    static int sheepCntInsideFence, wolfCntInsideFence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] gardenInfo = br.readLine().split(" ");
        gardenRow = Integer.parseInt(gardenInfo[0]);
        gardenCol = Integer.parseInt(gardenInfo[1]);

        gardenState = new char[gardenRow][gardenCol];
        visited = new boolean[gardenRow][gardenCol];
        for (int i = 0; i < gardenRow; i++) {
            gardenState[i] = br.readLine().toCharArray();
        }

        fightSheepAndWolf();
        System.out.printf("%d %d\n", sheepCnt, wolfCnt);
        br.close();
    }

    static void fightSheepAndWolf() {
        for (int i = 0; i < gardenRow; i++) {
            for (int j = 0; j < gardenCol; j++) {
                if (visited[i][j]) continue;

                sheepCntInsideFence = wolfCntInsideFence = 0;
                fightSheepAndWolfInsideFence(Coordinate.twoPointOf(i, j));
                fightSheepAndWolfInsideFence();
            }
        }
    }

    static void fightSheepAndWolfInsideFence() {
        if (sheepCntInsideFence > wolfCntInsideFence) sheepCnt += sheepCntInsideFence;
        else wolfCnt += wolfCntInsideFence;
    }

    static void fightSheepAndWolfInsideFence(Coordinate start) {
        int x = start.getX(), y = start.getY();
        if (isImpossibleVisit(x, y)) return;
        visited[x][y] = true;

        if (gardenState[x][y] == SHEEP) sheepCntInsideFence++;
        else if (gardenState[x][y] == WOLF) wolfCntInsideFence++;

        for (int i = 0; i < dLen; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (isOutOfGardenSize(nx, ny)) continue;

            fightSheepAndWolfInsideFence(Coordinate.twoPointOf(nx, ny));
        }
    }

    static boolean isImpossibleVisit(int x, int y) {
        return visited[x][y] || gardenState[x][y] == FENCE;
    }

    static boolean isOutOfGardenSize(int x, int y) {
        return isOutOfSize(x, gardenRow) || isOutOfSize(y, gardenCol);
    }

    static boolean isOutOfSize(int point, int size) {
        return (point < 0 || point >= size);
    }
}

class Coordinate {
    private int x, y;

    public static Coordinate twoPointOf(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}