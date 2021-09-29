import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import java.util.LinkedList;

//// 깔끔해요
class Main {
  static Vertex[] vertices;

  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    final int EDGE_AMOUNT = Input.nextInt();
    
    AtomicInteger counter = new AtomicInteger(0);
    vertices = Stream
      .generate(() -> new Vertex(counter.getAndIncrement()))
      .limit(VERTEX_AMOUNT + 1)
      .toArray(Vertex[]::new);
    
    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int vertexA = Input.nextInt();
      int vertexB = Input.nextInt();
      vertices[vertexA].addAdjacency(vertices[vertexB]);
      vertices[vertexB].addAdjacency(vertices[vertexA]);
    }

    System.out.print(countDepthOneOrTwo(vertices[1]));
  }
  
  static int countDepthOneOrTwo(Vertex startVertex) {
    Queue<Vertex> queue = new LinkedList<>();
    queue.add(startVertex);

    boolean[] visited = new boolean[vertices.length];
    visited[startVertex.number] = true;

    int count = 0;
    while (queue.size() > 0) {
      Vertex current = queue.poll();
      if (current.depth >= 2) {
        break;
      }

      for (Vertex adjacency : current.adjacencies) {
        if (visited[adjacency.number]) {
          continue;
        }
        visited[adjacency.number] = true;
        queue.add(adjacency);
        adjacency.depth = current.depth + 1;
        count++;
      }
    }
    return count;
  }
}

class Vertex {
  public int number;
  public int depth;
  public List<Vertex> adjacencies;
  
  public Vertex(int number) {
    this.number = number;
    adjacencies = new ArrayList<>();
  }

  public void addAdjacency(Vertex adjacency) {
    adjacencies.add(adjacency);
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