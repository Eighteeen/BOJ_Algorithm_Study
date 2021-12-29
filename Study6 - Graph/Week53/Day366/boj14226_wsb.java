import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        System.out.println(getMinTimeOfMakeToEoticon(S + 1, 1, S));
        br.close();
    }

    static int getMinTimeOfMakeToEoticon(int size, int from, int to) {
        Queue<Coordinate> bfsQueue = new LinkedList<>();
        int[][] minTimes = new int[size + 1][size + 1];
        boolean[][] visited = new boolean[size + 1][size + 1];

        bfsQueue.offer(Coordinate.twoPointOf(from, 0));
        visited[from][0] = true;

        while (!bfsQueue.isEmpty()) {
            Coordinate current = bfsQueue.poll();
            int currentScreen = current.getX();
            int currentClipboard = current.getY();
            if (currentScreen == to) {
                return minTimes[currentScreen][currentClipboard];
            }

            int nextTime = minTimes[currentScreen][currentClipboard] + 1;

            int copyClipboard = currentScreen;
            if (!(isOutOfSize(currentScreen, copyClipboard, size) ||
                visited[currentScreen][copyClipboard])) {
                    visited[currentScreen][copyClipboard] = true;
                    minTimes[currentScreen][copyClipboard] = nextTime;
                    bfsQueue.offer(Coordinate.twoPointOf(currentScreen, copyClipboard));
            }

            int pasteScreen = currentScreen + currentClipboard;
            if (!(isOutOfSize(pasteScreen, currentClipboard, size) ||
            visited[pasteScreen][currentClipboard])) {
                visited[pasteScreen][currentClipboard] = true;
                minTimes[pasteScreen][currentClipboard] = nextTime;
                bfsQueue.offer(Coordinate.twoPointOf(pasteScreen, currentClipboard));
            }

            int deleteScreen = currentScreen - 1;
            if (!(isOutOfSize(deleteScreen, currentClipboard, size) ||
                visited[deleteScreen][currentClipboard])) {
                    visited[deleteScreen][currentClipboard] = true;
                    minTimes[deleteScreen][currentClipboard] = nextTime;
                    bfsQueue.offer(Coordinate.twoPointOf(deleteScreen, currentClipboard));
            }
        }

        return -1;
    }

    static boolean isOutOfSize(int x, int y, int size) {
        return isOutOfSize(x, size) || isOutOfSize(y, size);
    }

    static boolean isOutOfSize(int point, int size) {
        return (point < 0 || point > size);
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