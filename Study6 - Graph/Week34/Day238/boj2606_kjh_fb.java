import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    final int EDGE_AMOUNT = Input.nextInt();

    //// vertex 의 복수형은 vertices라 전체적으로 수정하는게 어떨까용!?
    //// => 땡삼
    Vertex[] vertices = Stream.generate(Vertex::new).limit(VERTEX_AMOUNT + 1).toArray(Vertex[]::new);

    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      Vertex vertexA = vertices[numA];
      Vertex vertexB = vertices[numB];
      vertexA.connect(vertexB);
      vertexB.connect(vertexA);
    }

    Vertex startVertex = vertices[1];
    System.out.print(getReachableVertexAmount(startVertex) - 1);
  }

  static int getReachableVertexAmount(Vertex current) {
    current.visited = true;

    int reachableVertexAmount = 0;
    for (Vertex connectedVertex : current.connectedVertexes) {
      if (connectedVertex.visited) {
        continue;
      }
      reachableVertexAmount += getReachableVertexAmount(connectedVertex);
    }

    return 1 + reachableVertexAmount;
  }
}

class Vertex {
  public List<Vertex> connectedVertexes;
  public boolean visited;

  public Vertex() {
    connectedVertexes = new ArrayList<>();
  }

  public void connect(Vertex vertex) {
    connectedVertexes.add(vertex);
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