import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//// 깔끔무난
class Main {
  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    boolean[][] adjacencyMatrix = new boolean[VERTEX_AMOUNT][VERTEX_AMOUNT];

    for (int i = 0; i < VERTEX_AMOUNT; i++) {
      for (int j = 0; j < VERTEX_AMOUNT; j++) {
        adjacencyMatrix[i][j] = Input.nextInt() == 1;
      }
    }

    markAccessiblePaths(adjacencyMatrix);

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < VERTEX_AMOUNT; i++) {
      for (int j = 0; j < VERTEX_AMOUNT; j++) {
        result.append(adjacencyMatrix[i][j] ? 1 : 0).append(' ');
      }
      result.append('\n');
    }

    System.out.print(result);
  }

  static void markAccessiblePaths(boolean[][] adjacencyMatrix) {
    for (int vertexNumber = 0; vertexNumber < adjacencyMatrix.length; vertexNumber++) {
      markAccessibleVertices(adjacencyMatrix, vertexNumber);
    }
  }

  static void markAccessibleVertices(boolean[][] adjacencyMatrix, int startVertex) {
    final boolean CONNECTED = true;
    final boolean NOT_CONNECTED = false;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(startVertex);
    
    boolean[][] visited = new boolean[adjacencyMatrix.length][adjacencyMatrix.length];
    while (queue.size() > 0) {
      int current = queue.poll();

      for (int adjacency = 0; adjacency < adjacencyMatrix.length; adjacency++) {
        if (adjacencyMatrix[current][adjacency] == NOT_CONNECTED) {
          continue;
        }
        if (visited[current][adjacency]) {
          continue;
        }
        visited[current][adjacency] = true;
        queue.add(adjacency);
        adjacencyMatrix[startVertex][adjacency] = CONNECTED;
      }
    }
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