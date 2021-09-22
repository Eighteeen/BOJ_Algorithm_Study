// 비트마스킹으로 조합해서 시간초과 오지게 나다가 safeCoordinates 안에서만 조합하니까 시간초과 안 남 (쥐엔장)
// 배운 것을 활용한다고 꼭 좋은 건 아니다...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int labRow, labCol;
    static int[][] labStates;
    static boolean[][] isVisited;

    //// SAFE_FLAG 는 빈 공간(BLANK)인데  무조건 바이러스가 퍼지지 않는(안전한) 곳이라는 의미로 쓰이지는 않기 때문에
    //// NOTHING, BLANK를 사용하시는 건 어떨까요?
    //// safeCoordinates도 마찬가지로 벽을 세울 수 있는 후보군(candidates) 같은 표현이 나을 것 같아요 
    //// :22 위 피드백 모두 동감
    //// -> 생각하지 못 한 부분이네요. BLANK로 반영하고, getMaxSafeSpaceAfterStandThreeWalls 함수에서만 candidate 표현을 사용하여 반영했습니다!
    static final int BLANK_FLAG = 0, WALL_FLAG = 1, VIRUS_FLAG = 2;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapInfo = br.readLine().split(" ");
        labRow = Integer.parseInt(mapInfo[0]);
        labCol = Integer.parseInt(mapInfo[1]);

        List<Coordinate> virusCoordinates = new ArrayList<>(), blankCoordinates = new ArrayList<>();
        labStates = new int[labRow][];

        for (int i = 0; i < labRow; i++) {
            labStates[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < labCol; j++) {
                if (labStates[i][j] == VIRUS_FLAG) virusCoordinates.add(Coordinate.twoPointOf(i, j));
                else if (labStates[i][j] == BLANK_FLAG) blankCoordinates.add(Coordinate.twoPointOf(i, j));
            }
        }

        System.out.println(getMaxSafeSpaceAfterStandThreeWalls(virusCoordinates, blankCoordinates));
        br.close();
    }

    static int getMaxSafeSpaceAfterStandThreeWalls(List<Coordinate> virusCoordinates, List<Coordinate> candidateCoordinates) {
        int maxSafeSpace = 0;
        int candidateSpaceSize = candidateCoordinates.size();

        for (int i = 0; i < candidateSpaceSize - 2; i++) {
            Coordinate coordinate1 = candidateCoordinates.get(i);
            int x1 = coordinate1.getX(), y1 = coordinate1.getY();

            for (int j = i + 1; j < candidateSpaceSize - 1; j++) {
                Coordinate coordinate2 = candidateCoordinates.get(j);
                int x2 = coordinate2.getX(), y2 = coordinate2.getY();

                for (int k = j + 1; k < candidateSpaceSize; k++) {
                    Coordinate coordinate3 = candidateCoordinates.get(k);
                    int x3 = coordinate3.getX(), y3 = coordinate3.getY();

                    labStates[x1][y1] = labStates[x2][y2] = labStates[x3][y3] = WALL_FLAG;

                    isVisited = new boolean[labRow][labCol];
                    for (Coordinate virusCoordinate : virusCoordinates) {
                        //// 함수명 직관적!
                        spreadVirus(virusCoordinate);
                    }

                    maxSafeSpace = Math.max(maxSafeSpace, getNumOfSafeSpaceAndReset(candidateCoordinates));
                }
            }
        }

        return maxSafeSpace;
    }

    static void spreadVirus(Coordinate virusCoordinate) {
        int x = virusCoordinate.getX(), y = virusCoordinate.getY();
        if (isVisited[x][y]) return;

        isVisited[x][y] = true;
        labStates[x][y] = VIRUS_FLAG;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx == labRow || ny < 0 || ny == labCol) continue;
            if (isVisited[nx][ny]) continue;
            if (labStates[nx][ny] == BLANK_FLAG) spreadVirus(Coordinate.twoPointOf(nx, ny));
        }
    }

    static int getNumOfSafeSpaceAndReset(List<Coordinate> originalBlankCoordinates) {
        int cnt = 0;
        for (Coordinate coordinate : originalBlankCoordinates) {
            int x = coordinate.getX(), y = coordinate.getY();
            if (labStates[x][y] == BLANK_FLAG) cnt++;
            labStates[x][y] = BLANK_FLAG;
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