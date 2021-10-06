import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 : 22
class Main {
    static int MAP_ROW, MAP_COL;
    static boolean[][] wallMap;
    static int[][][] minDistances;

    static final int NORMAL = 0, BROKE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapInfo = br.readLine().split(" ");
        MAP_ROW = Integer.parseInt(mapInfo[0]);
        MAP_COL = Integer.parseInt(mapInfo[1]);

        wallMap = new boolean[MAP_ROW][MAP_COL];

        for (int i = 0; i < MAP_ROW; i++) {
            String row = br.readLine();
            for (int j = 0; j < MAP_COL; j++) {
                wallMap[i][j] = row.charAt(j) == '1';
            }
        }

        System.out.println(getAnswer());
        br.close();
    }

    static int getAnswer() {
        int distance = getDestinationDistance();
        return (distance == 0 ? -1 : distance);
    }

    static int getDestinationDistance() {
        setMinDistances();

        int x = MAP_ROW - 1, y = MAP_COL - 1;
        if (minDistances[BROKE][x][y] == 0) return minDistances[NORMAL][x][y];
        if (minDistances[NORMAL][x][y] == 0) return minDistances[BROKE][x][y];

        return Math.min(minDistances[BROKE][x][y], minDistances[NORMAL][x][y]);
    }

    static void setMinDistances() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<WallMapPoint> pointQueue = new LinkedList<>();
        pointQueue.offer(WallMapPoint.twoPointOf(0, 0));

        minDistances = new int[2][MAP_ROW][MAP_COL];
        minDistances[NORMAL][0][0] = 1;

        while (!pointQueue.isEmpty()) {
            WallMapPoint currentPoint = pointQueue.poll();
            int x = currentPoint.getX(), y = currentPoint.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                WallMapPoint nextPoint = WallMapPoint.twoPointOf(nx, ny);

                if (isOutOfMap(nextPoint) || isImpossiblePath(currentPoint, nextPoint)) continue;
                
                if (isBreakWallPath(currentPoint, nextPoint)) nextPoint.breakWall();
                if (isVisited(nextPoint)) continue;

                //// 함수로 분리해내서 깔끔해졌네요. 근데 거리를설정한다setDistance로는 함수의 동작을 추측하기 어려운 것 같아요
                //// -> 함수명 변경하여 반영했습니다!
                updateNextDistance(currentPoint, nextPoint);
                pointQueue.offer(nextPoint);
            }
        }
    }

    static boolean isOutOfMap(WallMapPoint point) {
        int x = point.getX();
        int y = point.getY();
        return (x < 0 || x == MAP_ROW || y < 0 || y == MAP_COL);
    }

    static boolean isImpossiblePath(WallMapPoint current, WallMapPoint next) {
        return (current.isBrokeWall() && wallMap[next.getX()][next.getY()]);
    }

    static boolean isVisited(WallMapPoint point) {
        return minDistances[getBrokeStateNum(point.isBrokeWall())][point.getX()][point.getY()] != 0;
    }

    static boolean isBreakWallPath(WallMapPoint current, WallMapPoint next) {
        return (current.isBrokeWall() || wallMap[next.getX()][next.getY()]);
    }

    static void updateNextDistance(WallMapPoint current, WallMapPoint next) {
        int currentBrokeStateNum = getBrokeStateNum(current.isBrokeWall());
        int nextBrokeStateNum = getBrokeStateNum(next.isBrokeWall());
        int cx = current.getX(), cy = current.getY();
        int nx = next.getX(), ny = next.getY();

        minDistances[nextBrokeStateNum][nx][ny] = minDistances[currentBrokeStateNum][cx][cy] + 1;
    }
    
    static int getBrokeStateNum(boolean brokeState) {
        return (brokeState ? BROKE : NORMAL);
    }
}

class WallMapPoint {
    private int x, y;
    private boolean isBrokeWall = false;

    public static WallMapPoint twoPointOf(int x, int y) {
        return new WallMapPoint(x, y);
    }

    private WallMapPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void breakWall() {
        isBrokeWall = true;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isBrokeWall() { return isBrokeWall; }
}