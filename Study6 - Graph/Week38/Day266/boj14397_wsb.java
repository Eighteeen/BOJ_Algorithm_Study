import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    static int mapRow, mapCol;
    static char WATER_FLAG = '.', LAND_FLAG = '#';
    static int[] dx = {-1, -1, 0, 1, 1, 0};
    //// 올.. 간단한 초기화법 배워가요
    static int[][] dy = {{-1, 0, 1, 0, -1, -1}, {0, 1, 1, 1, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapInfo = br.readLine().split(" ");
        mapRow = Integer.parseInt(mapInfo[0]);
        mapCol = Integer.parseInt(mapInfo[1]);

        char[][] mapStates = new char[mapRow][];
        for (int i = 0; i < mapRow; i++) {
            mapStates[i] = br.readLine().toCharArray();
        }

        System.out.println(getLenOfBeach(mapStates));
        br.close();
    }

    static int getLenOfBeach(char[][] mapStates) {
        int len = 0;

        for (int i = 0; i < mapRow; i++) {
            int dyOrder = i % 2;

            for (int j = 0; j < mapCol; j++) {
                if (mapStates[i][j] == WATER_FLAG) continue;

                for (int k = 0; k < 6; k++) {
                    int nx = i + dx[k], ny = j + dy[dyOrder][k];
                    if (nx < 0 || nx == mapRow || ny < 0 || ny == mapCol) continue;
                    if (mapStates[nx][ny] == WATER_FLAG) len++;
                }
            }
        }

        return len;
    }
}