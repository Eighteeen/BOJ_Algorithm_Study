import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;

    static int dLen = 3;
    static int[] dx = {0, 1, 1}, dy = {1, 0, 1};

    static int houseSize;
    static Coordinate destination;
    static boolean[][] houseWalls;
    static int caseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        houseSize = Integer.parseInt(br.readLine());
        houseWalls = new boolean[houseSize][houseSize];
        destination = Coordinate.twoPointOf(houseSize - 1, houseSize - 1);

        for (int i = 0; i < houseSize; i++) {
            int[] wallInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < houseSize; j++) {
                houseWalls[i][j] = wallInfo[j] == 1;
            }
        }

        countCasesOfMovePipeToDestination(Coordinate.twoPointOf(0, 1), HORIZONTAL);
        System.out.println(caseCnt);
        br.close();
    }

    static void countCasesOfMovePipeToDestination(Coordinate endOfPipe, int pipeDirection) {
        if (endOfPipe.equals(destination)) {
            caseCnt++;
            return;
        }

        int x = endOfPipe.getX(), y = endOfPipe.getY();

        for (int i = 0; i < dLen; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfHouseSize(nx, ny)) continue;
            Coordinate next = Coordinate.twoPointOf(nx, ny);

            if (isPossibleMove(next, pipeDirection, i)) {
                countCasesOfMovePipeToDestination(next, i);
            }
        }
    }

    static boolean isPossibleMove(Coordinate nextCoordinate, int currentDirection, int nextDirection) {
        if (isImpossibleRotate(currentDirection, nextDirection)) return false;

        int x = nextCoordinate.getX(), y = nextCoordinate.getY();
        if (nextDirection == DIAGONAL) {
            return isPossibleDiagonalMove(x, y);
        }

        return !houseWalls[x][y];
    }

    static boolean isImpossibleRotate(int currentDirection, int nextDirection) {
        return (currentDirection == HORIZONTAL && nextDirection == VERTICAL) ||
            (currentDirection == VERTICAL && nextDirection == HORIZONTAL);
    }

    static boolean isPossibleDiagonalMove(int x, int y) {
        int dLen = 2;
        int[] dx = {-1, 0}, dy = {0, -1};

        for (int i = 0; i < dLen; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfHouseSize(nx, ny)) continue;

            if (houseWalls[nx][ny]) return false;
        }

        return !houseWalls[x][y];
    }

    static boolean isOutOfHouseSize(int x, int y) {
        return isOutOfSize(x, houseSize) || isOutOfSize(y, houseSize);
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

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Coordinate other = (Coordinate) obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }
}