import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int dLen = 4;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int mapXsize, mapYsize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] streetInfo = br.readLine().split(" ");
        int X = Integer.parseInt(streetInfo[0]), Y = Integer.parseInt(streetInfo[1]);
        int N = Integer.parseInt(streetInfo[2]);

        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;

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

        minX--;
        minY--;
        maxX++;
        maxY++;
        mapXsize = Math.abs(minX) + Math.abs(maxX) + 1;
        mapYsize = Math.abs(minY) + Math.abs(maxY) + 1;

        boolean[][] toSinaStreet = new boolean[mapXsize][mapYsize];
        for (Coordinate puddleCoordinate : puddleCoordinates) {
            int x = puddleCoordinate.getX(), y = puddleCoordinate.getY();
            // x = Math.abs(minX) 
        }

        System.out.println(sb);
        br.close();
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