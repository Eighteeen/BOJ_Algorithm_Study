import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

//// 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < T; i++) {
      int boardSize = Input.nextInt();
      Coordinate from = new Coordinate(Input.nextLine());
      Coordinate to = new Coordinate(Input.nextLine());

      result.append(calcMinMoves(boardSize, from, to)).append('\n');
    }

    System.out.print(result);
  }

  static int calcMinMoves(int boardSize, Coordinate from, Coordinate to) {
    int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1 };
    int[] dx = { -2, -1, 1, 2, -2, -1, 1, 2 };

    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(from);

    int[][] minMoves = new int[boardSize][boardSize];
    final int NOT_INITIALIZED = -1;
    for (int i = 0; i < boardSize; i++) {
      Arrays.fill(minMoves[i], NOT_INITIALIZED);
    }
    minMoves[from.y][from.x] = 0;

    while (queue.size() > 0) {
      Coordinate current = queue.poll();
      int y = current.y;
      int x = current.x;

      for (int i = 0; i < 8; i++) {
        int moveY = y + dy[i];
        int moveX = x + dx[i];

        boolean outOfIndex = moveY < 0 || moveX < 0 || moveY >= boardSize || moveX >= boardSize;
        if (outOfIndex) {
          continue;
        }

        if (minMoves[moveY][moveX] == NOT_INITIALIZED) {
          minMoves[moveY][moveX] = minMoves[y][x] + 1;
          queue.add(new Coordinate(moveY, moveX));
        }
      }
    }
    
    return minMoves[to.y][to.x];
  }
}

class Coordinate {
  public int y;
  public int x;

  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
  }

  public Coordinate(String coordinate) {
    String[] yx = coordinate.split(" ");
    this.y = Integer.parseInt(yx[0]);
    this.x = Integer.parseInt(yx[1]);
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