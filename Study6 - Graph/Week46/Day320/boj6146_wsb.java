import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int dLen = 4;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int mapXsize, mapYsize;
    static boolean[][] puddleMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] streetInfo = br.readLine().split(" ");
        int X = Integer.parseInt(streetInfo[0]), Y = Integer.parseInt(streetInfo[1]);
        int N = Integer.parseInt(streetInfo[2]);

        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;
        //// 클래스 이름에서도 이미 드러나는데 변수 이름에서도 Coordinate를 또 쓰는건 동어반복인 것 같습니다
        List<Coordinate> puddleCoordinates = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            String[] puddleInfo = br.readLine().split(" ");
            int puddleX = Integer.parseInt(puddleInfo[0]);
            int puddleY = Integer.parseInt(puddleInfo[1]);

            puddleCoordinates.add(Coordinate.twoPointOf(puddleX, puddleY));

            minX = Math.min(minX, puddleX);
            maxX = Math.max(maxX, puddleX);
            minY = Math.min(minY, puddleY);
            maxY = Math.max(maxY, puddleY);
        }

        //// 와우 최적화 하셨군요 : 22
        setMapSize(--minX, ++maxX, --minY, ++maxY);
        makePuddleMap(puddleCoordinates, minX, minY);

        System.out.println(
            getMinDistanceAvoidPuddle(
                //// 요 함수가 어떤 역할을 하는지 알기 어려웠어요. 좌표 두개를 넣으면 좌표가 나온다? 어떤 좌표가 나온다는거지?
                getCoordinateInPuddleMap(0, 0, minX, minY),
                getCoordinateInPuddleMap(X, Y, minX, minY)
            )
        );
        br.close();
    }

    static void setMapSize(int minX, int maxX, int minY, int maxY) {
        mapXsize = Math.abs(minX) + Math.abs(maxX) + 1;
        mapYsize = Math.abs(minY) + Math.abs(maxY) + 1;
    }

    static void makePuddleMap(List<Coordinate> puddleCoordinates, int minX, int minY) {
        puddleMap = new boolean[mapXsize][mapYsize];
        for (Coordinate puddleCoordinate : puddleCoordinates) {
            int x = puddleCoordinate.getX(), y = puddleCoordinate.getY();
            puddleMap[x - minX][y - minY] = true;
        }
    }

    static Coordinate getCoordinateInPuddleMap(int x, int y, int minX, int minY) {
        return Coordinate.twoPointOf(x - minX, y - minY);
    }

    static int getMinDistanceAvoidPuddle(Coordinate from, Coordinate to) {
        Queue<Coordinate> bfsQueue = new LinkedList<>();
        int[][] minMovements = new int[mapXsize][mapYsize];
        bfsQueue.offer(from);

        while (!bfsQueue.isEmpty()) {
            Coordinate current = bfsQueue.poll();
            if (current.equals(to)) break;

            int x = current.getX(), y = current.getY();
            for (int i = 0; i < dLen; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (isOutOfSize(mapXsize, nx) ||
                    isOutOfSize(mapYsize, ny) ||
                    puddleMap[nx][ny] ||
                    minMovements[nx][ny] > 0) continue;
                minMovements[nx][ny] = minMovements[x][y] + 1;
                bfsQueue.offer(Coordinate.twoPointOf(nx, ny));
            }
        }

        return minMovements[to.getX()][to.getY()];
    }

    static boolean isOutOfSize(int size, int point) {
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