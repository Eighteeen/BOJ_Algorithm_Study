import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  static int ROW_SIZE;
  static int COLUMN_SIZE;

  public static void main(String[] args) throws Exception {
    ROW_SIZE = Input.nextInt();
    COLUMN_SIZE = Input.nextInt();

    boolean[][] adjacencyMatrix = new boolean[ROW_SIZE][COLUMN_SIZE];
    for (int i = 0; i < ROW_SIZE; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < COLUMN_SIZE; j++) {
        adjacencyMatrix[i][j] = row.charAt(j) == '1';
      }
    }

    int[][] shortestDistances = getShortestDistances(adjacencyMatrix);
    System.out.print(shortestDistances[ROW_SIZE - 1][COLUMN_SIZE - 1]);
  }

  static int[][] getShortestDistances(boolean[][] adjacencyMatrix) {
    int[][] shortestDistances = new int[ROW_SIZE][COLUMN_SIZE];
    shortestDistances[0][0] = 1;

    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, -1, 1};

    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0));

    while (queue.size() > 0) {
      Point point = queue.poll();

      for (int i = 0; i < 4; i++) {
        int aroundX = point.x + dx[i];
        int aroundY = point.y + dy[i];

        boolean isOutOfMaze = aroundX < 0 || aroundY < 0 || aroundX >= ROW_SIZE || aroundY >= COLUMN_SIZE;
        if (isOutOfMaze) {
          continue;
        }
        boolean isWall = adjacencyMatrix[aroundX][aroundY] == false;
        if (isWall) {
          continue;
        }
        
        final int UNDEFINED = 0;
        if (shortestDistances[aroundX][aroundY] == UNDEFINED) {
          shortestDistances[aroundX][aroundY] = shortestDistances[point.x][point.y] + 1;
          queue.add(new Point(aroundX, aroundY));
        }
      }
    }

    return shortestDistances;
  }
}

class Point {
  public int x;
  public int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
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