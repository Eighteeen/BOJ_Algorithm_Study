import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

class Main {
  static int ROW_SIZE;
  static int COLUMN_SIZE;
  static boolean[][] visited;
  static int nothingCount;

  static int NOTHING = 0;
  static int WALL = 1;
  static int VIRUS = 2;

  public static void main(String[] args) throws Exception {
    ROW_SIZE = Input.nextInt();
    COLUMN_SIZE = Input.nextInt();
    
    List<Coordinate> viruses = new ArrayList<>();
    int[][] map = new int[ROW_SIZE][COLUMN_SIZE];
    visited = new boolean[ROW_SIZE][COLUMN_SIZE];
    
    for (int y = 0; y < ROW_SIZE * COLUMN_SIZE; y++) {
      for (int x = 0; x < COLUMN_SIZE; x++) {
        map[y][x] = Input.nextInt();
        if (map[y][x] == VIRUS) {
          viruses.add(new Coordinate(y, x));
          visited[y][x] = true;
        }
        if (map[y][x] == NOTHING) {
          nothingCount++;
        }
      }
    }

    List<Coordinate> candidatesThreeWall = getCandidatesThreeWall(map, viruses);

    int maxSafeSize = getMaxSafeSizeForAllCandidates(map, viruses, candidates);
    System.out.print(maxSafeSize);
  }

  static List<Coordinate> getCandidatesThreeWall(int[][] map, List<Coordinate> viruses) {
    int[] dy = new int[] { -1, 0, 0, 1 };
    int[] dx = new int[] { 0, -1, 1, 0};

    Queue<Coordinate> queue = new Queue<>();
    for (Coordinate virus : viruses) {
      queue.add(virus);
    }

    List<Coordinate> candidates = new ArrayList<>();
    while (queue.size() > 0) {
      Coordinate coordinate = queue.poll();
      int y = coordinate.y;
      int x = coordinate.x;

      for (int i = 0; i < 4; i++) {
        int aroundY = y + dy[i];
        int aroundX = x + dx[i];
        
        boolean outOfIndex = y < 0 || x < 0 || y >= ROW_SIZE || x >= COLUMN_SIZE;
        if (outOfIndex) {
          continue;
        }
        if (visited[aroundY][aroundX]) {
          continue;
        }
        if (map[aroundY][aroundX] != NOTHING) {
          continue;
        }
        candidates.add(new Coordinate(aroundY, aroundX));
        visited[aroundY][aroundX] = true;
      }
    }

    return candidates;
  }

  static int getMaxSafeSizeForAllCandidates(int[][] map, List<Coordinate> candidates) {
    int maxSafeSize = 0;
    for (int i = 0; i < candidates.size() - 2; i++) {
      for (int j = i + 1; j < candidates.size() - 1; j++) {
        for (int k = j + 1; k < candidates.size(); k++) {
          maxSafeSize = Math.max(maxSafeSize, getSafeSizeWithThreeWall(map, candidates.get(i), candidates.get(j), candidates.get(k)));
        }
      }
    }
    return maxSafeSize;
  }

  static int getSafeSizeWithThreeWall(int[][] map, List<Coordinate> viruses, Coordinate wall1, Coordinate wall2, Coordinate wall3) {
    int infectedCount = 0;

    int[][] newMap = new int[ROW_SIZE][COLUMN_SIZE];
    for (int y = 0; y < ROW_SIZE; y++) {
      for (int x = 0; x < COLUMN_SIZE; x++) {
        newMap[y][x] = map[y][x];
      }
    }
    newMap[wall1.y][wall1.x] = WALL;
    newMap[wall2.y][wall2.x] = WALL;
    newMap[wall3.y][wall3.x] = WALL;

    int[] dy = new int[] { -1, 0, 0, 1 };
    int[] dx = new int[] { 0, -1, 1, 0};

    boolean[][] visited = new boolean[ROW_SIZE][COLUMN_SIZE];
    Queue<Coordinate> queue = new Queue<>();
    for (Coordinate virus : viruses) {
      queue.add(virus);
      visited[virus.y][virus.x] = true;
    }

    List<Coordinate> candidates = new ArrayList<>();
    while (queue.size() > 0) {
      Coordinate coordinate = queue.poll();
      int y = coordinate.y;
      int x = coordinate.x;

      for (int i = 0; i < 4; i++) {
        int aroundY = y + dy[i];
        int aroundX = x + dx[i];
        
        boolean outOfIndex = y < 0 || x < 0 || y >= ROW_SIZE || x >= COLUMN_SIZE;
        if (outOfIndex) {
          continue;
        }
        if (visited[aroundY][aroundX]) {
          continue;
        }
        if (map[aroundY][aroundX] != NOTHING) {
          continue;
        }
        infectedCount++;
        visited[aroundY][aroundX] = true;
      }
    }

    return nothingCount - infectedCount;
  }
}

class Coordinate {
  int y;
  int x;

  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
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