import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.LinkedList;
import java.util.Queue;

class Main {
  static StringBuilder result;
  static List<Integer>[] vertices;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    final int EDGE_AMOUNT = Input.nextInt();
    final int START_VERTEX = Input.nextInt();

    vertices = Stream.generate(ArrayList<Integer>::new).limit(VERTEX_AMOUNT + 1).toArray(ArrayList[]::new);

    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int vertexA = Input.nextInt();
      int vertexB = Input.nextInt();

      insertInAscending(vertices[vertexA], vertexB);
      insertInAscending(vertices[vertexB], vertexA);
    }

    result = new StringBuilder();

    visited = new boolean[VERTEX_AMOUNT + 1];
    fillResultForDFS(START_VERTEX);

    result.append('\n');

    visited = new boolean[VERTEX_AMOUNT + 1];
    fillResultForBFS(START_VERTEX);

    System.out.print(result);
  }

  static void fillResultForDFS(Integer startVertex) {
    visited[startVertex] = true;
    result.append(startVertex).append(' ');

    for (Integer connectedVertex : vertices[startVertex]) {
      if (visited[connectedVertex]) {
        continue;
      }
      fillResultForDFS(connectedVertex);
    }
  }

  static void fillResultForBFS(Integer startVertex) {
    Queue<Integer> queue = new LinkedList<>();

    queue.add(startVertex);
    visited[startVertex] = true;

    while (queue.size() > 0) {
      Integer current = queue.poll();
      result.append(current).append(' ');

      for (Integer connectedVertex : vertices[current]) {
        if (visited[connectedVertex]) {
          continue;
        }
        queue.add(connectedVertex);
        visited[connectedVertex] = true;
      }
    }
  }

  static void insertInAscending(List<Integer> list, Integer toAdd) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) > toAdd) {
        list.add(i, toAdd);
        return;
      }
    }
    list.add(toAdd);
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