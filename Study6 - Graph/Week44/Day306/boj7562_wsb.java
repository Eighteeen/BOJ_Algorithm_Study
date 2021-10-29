import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int len = Integer.parseInt(br.readLine());

            String[] startCoordinateInfo = br.readLine().split(" ");
            Coordinate startCoordinate = Coordinate.twoPointOf(
                Integer.parseInt(startCoordinateInfo[0]),
                Integer.parseInt(startCoordinateInfo[1])
            );
            String[] destinationCoordinateInfo = br.readLine().split(" ");
            Coordinate destinationCoordinate = Coordinate.twoPointOf(
                Integer.parseInt(destinationCoordinateInfo[0]),
                Integer.parseInt(destinationCoordinateInfo[1])
            );

            sb.append(getMinMovement(len, startCoordinate, destinationCoordinate)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int getMinMovement(int graphSize, Coordinate from, Coordinate to) {
        Queue<Coordinate> bfsQueue = new LinkedList<>();
        int[][] minMovements = new int[graphSize][graphSize];

        bfsQueue.offer(from);

        while (!bfsQueue.isEmpty()) {
            Coordinate current = bfsQueue.poll();
            if (current.equals(to)) break;

            int x = current.getX(), y = current.getY();
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                Coordinate next = Coordinate.twoPointOf(nx, ny);
                if (isOutOfSize(graphSize, next) || minMovements[nx][ny] > 0) continue;
                minMovements[nx][ny] = minMovements[x][y] + 1;
                bfsQueue.offer(next);
            }
        }

        return minMovements[to.getX()][to.getY()];
    }

    static boolean isOutOfSize(int graphSize, Coordinate point) {
        return (isOutOfSize(graphSize, point.getX()) || isOutOfSize(graphSize, point.getY()));
    }

    static boolean isOutOfSize(int graphSize, int point) {
        return (point < 0 || point >= graphSize);
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