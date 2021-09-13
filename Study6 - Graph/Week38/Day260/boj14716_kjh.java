import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static boolean[][] visited;
  static List<Coordinate>[][] adjacencyList;

  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();
    
    visited = new boolean[ROW_SIZE][COLUMN_SIZE];
    adjacencyList = new ArrayList[ROW_SIZE][COLUMN_SIZE];

    int[] dy = new int[] { -1, -1, 0, -1};
    int[] dx = new int[] { -1, 0, -1, 1};

    for (int y = 0; y < ROW_SIZE; y++) {
      for (int x = 0; x < COLUMN_SIZE; x++) {
        boolean isDot = Input.nextChar() == '1';
        if (!isDot) {
          continue;
        }

        adjacencyList[y][x] = new ArrayList<Coordinate>();

        for (int i = 0; i < 4; i++) {
          int aroundY = y + dy[i];
          int aroundX = x + dx[i];

          boolean outOfIndex = aroundY >= ROW_SIZE || aroundX >= COLUMN_SIZE || aroundY < 0 || aroundX < 0;
          if (outOfIndex) {
            continue;
          }

          if (adjacencyList[aroundY][aroundX] != null) {
            adjacencyList[y][x].add(new Coordinate(aroundY, aroundX));
            adjacencyList[aroundY][aroundX].add(new Coordinate(y, x));
          }
        }
      }
    }

    int count = 0;
    for (int y = 0; y < ROW_SIZE; y++) {
      for (int x = 0; x < COLUMN_SIZE; x++) {
        count += isThereAnyNotVisitedVertex(adjacencyList, new Coordinate(y, x)) ? 1 : 0;
      }
    }

    System.out.print(count);
  }

  static boolean isThereAnyNotVisitedVertex(List<Coordinate>[][] adjacencyList, Coordinate startVertex) {
    int y = startVertex.y;
    int x = startVertex.x;
    
    if (visited[y][x]) {
      return false;
    }
    visited[y][x] = true;

    if (adjacencyList[y][x] == null) {
      return false;
    }

    for (Coordinate adjacency : adjacencyList[y][x]) {
      if (visited[adjacency.y][adjacency.x]) {
        continue;
      }
      isThereAnyNotVisitedVertex(adjacencyList, adjacency);
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