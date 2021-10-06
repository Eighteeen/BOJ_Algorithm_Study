import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//// 무난 깔끔해요 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();
    
    boolean[][] map = new boolean[ROW_SIZE][COLUMN_SIZE];

    for (int y = 0; y < ROW_SIZE; y++) {
      String row = Input.nextLine();
      
      for (int x = 0; x < COLUMN_SIZE; x++) {
        map[y][x] = row.charAt(x) == '1';
      }
    }

    int shortestDistance = getShortestDistanceWithOneWallBreak(map);
    System.out.print(shortestDistance);
  }

  static int getShortestDistanceWithOneWallBreak(boolean[][] map) {
    Queue<WallMapPoint> queue = new LinkedList<>();
    queue.add(new WallMapPoint(0, 0));

    //// shortestDistance를 초기화해서 반환하는 함수가 따로 있으면 더 깔끔할 것 같아요!
    //// => 반영해봤습니다!
    int[][][] shortestDistance = init3dArray(2, map.length, map[0].length, 1000000);
    shortestDistance[0][0][0] = 1;

    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    final boolean WALL = true;

    while (queue.size() > 0) {
      WallMapPoint current = queue.poll();
      int y = current.y;
      int x = current.x;
      
      int nextDistance = shortestDistance[current.brokeWall ? 1 : 0][y][x] + 1;

      for (int i = 0; i < 4; i++) {
        int aroundY = y + dy[i];
        int aroundX = x + dx[i];

        boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= map.length || aroundX >= map[0].length;
        if (outOfIndex) {
          continue;
        }
        
        boolean brokeWall = current.brokeWall;
        if (brokeWall && map[aroundY][aroundX] == WALL) {
          continue;
        }
        
        brokeWall = brokeWall || map[aroundY][aroundX]; 
        if (shortestDistance[brokeWall ? 1 : 0][aroundY][aroundX] <= nextDistance) {
          continue;
        }
        
        shortestDistance[brokeWall ? 1 : 0][aroundY][aroundX] = nextDistance;
        queue.add(new WallMapPoint(aroundY, aroundX, brokeWall));
      }
    }

    int destinationDistance = Math.min(shortestDistance[0][map.length - 1][map[0].length - 1], shortestDistance[1][map.length - 1][map[0].length - 1]);
    return destinationDistance == 1000000 ? -1 : destinationDistance;
  }

  static int[][][] init3dArray(int zSize, int ySize, int xSize, int value) {
    int[][][] array = new int[zSize][ySize][xSize];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        for (int k = 0; k < array[0][0].length; k++) {
            array[i][j][k] = value;
        }
      }
    }
    return array;
  }
}

class WallMapPoint {
  int y;
  int x;
  boolean brokeWall;

  public WallMapPoint(int y, int x) {
    this(y, x, false);
  }

  public WallMapPoint(int y, int x, boolean brokeWall) {
    this.y = y;
    this.x = x;
    this.brokeWall = brokeWall;
  }

  public String toString() {
    return String.format("(%d %d %s)", y, x, brokeWall);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

    return null;
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }

  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
  }

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
  }

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
  }

  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
  }

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
  }

  private static void makeTokensIfNeed() {
    makeTokensIfNotReadedYet();
    makeTokensIfHasNoToken();
  }

  private static void makeTokensIfNotReadedYet() {
    if (tokenizer == null) {
      tokenizer = makeTokens();
    }
  }

  private static void makeTokensIfHasNoToken() {
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}