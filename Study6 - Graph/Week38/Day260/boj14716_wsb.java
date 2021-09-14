import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔!
class Main {
    static int[][] bannerStates;
    static int bannerRow, bannerCol;
    
    static final int NOT_LETTER_FLAG = 0;

    static boolean[][] isVisited;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0}, dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] bannerInfo = br.readLine().split(" ");
        bannerRow = Integer.parseInt(bannerInfo[0]);
        bannerCol = Integer.parseInt(bannerInfo[1]);

        bannerStates = new int[bannerRow][];
        for (int i = 0; i < bannerRow; i++) {
            bannerStates[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getNumOfBannerLetter());
        br.close();
    }

    static int getNumOfBannerLetter() {
        isVisited = new boolean[bannerRow][bannerCol];
        int cntLetter = 0;

        for (int i = 0; i < bannerRow; i++) {
            for (int j = 0; j < bannerCol; j++) {
                if (isVisited[i][j] || bannerStates[i][j] == NOT_LETTER_FLAG) continue;
                cntLetter++;
                dfsBanner(Coordinate.twoPointOf(i, j));
            }
        }

        return cntLetter;
    }

    static void dfsBanner(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        if (isVisited[x][y] || bannerStates[x][y] == NOT_LETTER_FLAG) return;
        isVisited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx == bannerRow || ny < 0 || ny == bannerCol) continue;
            dfsBanner(Coordinate.twoPointOf(nx, ny));
        }
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