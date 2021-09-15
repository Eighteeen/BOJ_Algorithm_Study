import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int labRow, labCol;
    static final int SAFE_FLAG = 0, WALL_FLAG = 1, VIRUS_FLAG = 3, ADD_WALL_NUM = 3;
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

            for (int j = 0; j < labSize; j++) {
                if ((i & (1 << j)) == 0) continue;

                int x = j / labCol, y = j % labCol;
                if (labStates[x][y] != SAFE_FLAG) {
                    isAlreadyExistSomething = true;
                    break;
                }
                addWallCoordinates.add(Coordinate.twoPointOf(x, y));
            }

            if (isAlreadyExistSomething) continue;

            int[][] copyLabStates = labStates.clone();
            for (Coordinate addWallCoordinate : addWallCoordinates) {
                copyLabStates[addWallCoordinate.getX()][addWallCoordinate.getY()] = WALL_FLAG;
            }
            spreadVirus(copyLabStates);

            maxSafeSpace = Math.max(maxSafeSpace, getNumOfSafeSpace(copyLabStates));
        }

        System.out.println(maxSafeSpace);
        br.close();
    }

    static void spreadVirus(int[][] labStates) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        boolean[][] isVisited = new boolean[labRow][labCol];
        
        Queue<Coordinate> coordinateQueue = new LinkedList<>();
        for (Coordinate virusCoordinate : virusCoordinates) {
            coordinateQueue.offer(virusCoordinate);
        }

        while (!coordinateQueue.isEmpty()) {
            Coordinate coordinate = coordinateQueue.poll();
            int x = coordinate.getX(), y = coordinate.getY();

            if (isVisited[x][y]) continue;
            isVisited[x][y] = true;
            labStates[x][y] = VIRUS_FLAG;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx == labRow || ny < 0 || ny == labCol) continue;
                if (labStates[nx][ny] != SAFE_FLAG) continue;
                coordinateQueue.offer(Coordinate.twoPointOf(nx, ny));
            }
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