// 문제 실패 : 시간 초과에서 계속 벗어나지 못 함.
// 줄일 수 있는 방법이 떠올랐는데 시간 부족으로 아직 방법 적용을 못 함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int labRow, labCol;
    static final int SAFE_FLAG = 0, WALL_FLAG = 1, VIRUS_FLAG = 2, ADD_WALL_NUM = 3;
    static List<Coordinate> virusCoordinates;
    static List<Coordinate> safeCoordinates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapInfo = br.readLine().split(" ");
        labRow = Integer.parseInt(mapInfo[0]);
        labCol = Integer.parseInt(mapInfo[1]);

        virusCoordinates = new ArrayList<>();
        safeCoordinates = new ArrayList<>();
        int[][] labStates = new int[labRow][];
        for (int i = 0; i < labRow; i++) {
            labStates[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < labCol; j++) {
                if (labStates[i][j] == VIRUS_FLAG) virusCoordinates.add(Coordinate.twoPointOf(i, j));
                else if (labStates[i][j] == SAFE_FLAG) safeCoordinates.add(Coordinate.twoPointOf(i, j));
            }
        }

        int maxSafeSpace = 0;

        int labSize = labRow * labCol;
        long labBitmaskRange = (1L << labSize);
        if (labBitmaskRange == 1) labBitmaskRange = Long.MAX_VALUE;
        for (long i = 1; i < labBitmaskRange; i++) {
            if (Long.bitCount(i) != ADD_WALL_NUM) continue;

            boolean isAlreadyExistSomething = false;
            List<Coordinate> addWallCoordinates = new ArrayList<>();

            int cntAddWall = 0;
            for (int j = 0; j < labSize; j++) {
                if ((i & (1 << j)) == 0) continue;

                int x = j / labCol, y = j % labCol;
                if (labStates[x][y] != SAFE_FLAG) {
                    isAlreadyExistSomething = true;
                    break;
                }

                addWallCoordinates.add(Coordinate.twoPointOf(x, y));
                if (++cntAddWall == ADD_WALL_NUM) break;
            }

            if (isAlreadyExistSomething) continue;

            copyLabStates = deepCopy(labStates);
            for (Coordinate addWallCoordinate : addWallCoordinates) {
                copyLabStates[addWallCoordinate.getX()][addWallCoordinate.getY()] = WALL_FLAG;
            }

            isVisited = new boolean[labRow][labCol];
            for (Coordinate virusCoordinate : virusCoordinates) {
                spreadVirus(virusCoordinate);
            }

            maxSafeSpace = Math.max(maxSafeSpace, getNumOfSafeSpace(copyLabStates));
        }

        System.out.println(maxSafeSpace);
        br.close();
    }

    // 문제 푸는 중
    static boolean[][] isVisited;
    static int[][] copyLabStates;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    
    static void spreadVirus(Coordinate virusCoordinate) {
        int x = virusCoordinate.getX(), y = virusCoordinate.getY();
        if (isVisited[x][y]) return;

        isVisited[x][y] = true;
        copyLabStates[x][y] = VIRUS_FLAG;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx == labRow || ny < 0 || ny == labCol) continue;
            if (isVisited[nx][ny] || copyLabStates[nx][ny] != SAFE_FLAG) continue;
            spreadVirus(Coordinate.twoPointOf(nx, ny));
        }
    }

    static int getNumOfSafeSpace(int[][] labStates) {
        int cnt = 0;
        for (Coordinate safeCoordinate : safeCoordinates) {
            int x = safeCoordinate.getX(), y = safeCoordinate.getY();
            if (labStates[x][y] == SAFE_FLAG) cnt++;
        }
        return cnt;
    }

    static int[][] deepCopy(int[][] arr) {
        int row = arr.length, col = arr[0].length;
        int[][] copyArr = new int[row][col];

        for (int i = 0; i < row; i++) {
            System.arraycopy(arr[i], 0, copyArr[i], 0, col);
        }

        return copyArr;
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