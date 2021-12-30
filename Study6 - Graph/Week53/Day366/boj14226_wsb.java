import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해욧
class Main {
    static int screenSize;
    static Queue<Coordinate> bfsQueue;
    static int[][] minTimes;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        System.out.println(getMinTimeOfMakeToEmoticon(1, S));
        br.close();
    }

    //// from, to로는 약간 설명이 부족한 느낌적느낌
    static int getMinTimeOfMakeToEmoticon(int from, int to) {
        initForMakeToEmoticon(from, to);

        while (!bfsQueue.isEmpty()) {
            Coordinate current = bfsQueue.poll();
            int currentScreen = current.getX();
            int currentClipboard = current.getY();
            if (currentScreen == to) {
                return minTimes[currentScreen][currentClipboard];
            }

            int nextTime = minTimes[currentScreen][currentClipboard] + 1;

            //// 함수 하나로 매개변수를 다르게 해서 더욱 한눈에 알아볼 수 있었어요 :22
            int copyClipboard = currentScreen;
            makeEmoticon(currentScreen, copyClipboard, nextTime);

            int pasteScreen = currentScreen + currentClipboard;
            makeEmoticon(pasteScreen, currentClipboard, nextTime);

            int deleteScreen = currentScreen - 1;
            makeEmoticon(deleteScreen, currentClipboard, nextTime);
        }

        return -1;
    }

    static void initForMakeToEmoticon(int from, int to) {
        screenSize = to + 1;
        bfsQueue = new LinkedList<>();
        minTimes = new int[screenSize + 1][screenSize + 1];
        visited = new boolean[screenSize + 1][screenSize + 1];

        bfsQueue.offer(Coordinate.twoPointOf(from, 0));
        visited[from][0] = true;
    }

    static void makeEmoticon(int screen, int clipboard, int makeTime) {
        if (!(isOutOfSize(screen, clipboard, screenSize) ||
            visited[screen][clipboard])) {
                visited[screen][clipboard] = true;
                minTimes[screen][clipboard] = makeTime;
                bfsQueue.offer(Coordinate.twoPointOf(screen, clipboard));
        }
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
}