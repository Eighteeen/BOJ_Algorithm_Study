import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//// 깔끔 : 22
class Main {
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < T; i++) {
      int rowSize = Input.nextInt();
      int colSize = Input.nextInt();
      int cabbagesCount = Input.nextInt();

      visited = new boolean[rowSize][colSize];
      boolean[][] cabbages = new boolean[rowSize][colSize];

      for (int j = 0; j < cabbagesCount; j++) {
        int y = Input.nextInt();
        int x = Input.nextInt();

        cabbages[y][x] = true;
      }

      result.append(getLeastWorms(cabbages)).append('\n');
    }

    System.out.print(result);
  }

  static int getLeastWorms(boolean[][] cabbages) {
    int worms = 0;
    for (int y = 0; y < cabbages.length; y++) {
      for (int x = 0; x < cabbages[0].length; x++) {
        if (!cabbages[y][x]) continue;
        worms += isThereNoVisitedVertex(cabbages, new Coordinate(y, x)) ? 1 : 0;
      }
    }

    return worms;
  }

  static boolean isThereNoVisitedVertex(boolean[][] cabbages, Coordinate startVertex) {
    int y = startVertex.y;
    int x = startVertex.x;
    
    if (visited[y][x]) {
      return false;
    }
    visited[y][x] = true;

    if (!cabbages[y][x]) {
      return false;
    }

    int[] dy = new int[] { -1, 0, 0, 1 };
    int[] dx = new int[] { 0, -1, 1, 0 };

    for (int i = 0; i < 4; i++) {
      int aroundY = y + dy[i];
      int aroundX = x + dx[i];

      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= cabbages.length || aroundX >= cabbages[0].length;
      if (outOfIndex || visited[aroundY][aroundX]) {
        continue;
      }
      isThereNoVisitedVertex(cabbages, new Coordinate(aroundY, aroundX));
    }
    
    return true;
  }
}

class Coordinate {
  public int y;
  public int x;
  
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