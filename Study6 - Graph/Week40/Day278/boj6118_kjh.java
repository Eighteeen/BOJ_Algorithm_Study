import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.stream.Stream;

//// 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    final int EDGE_AMOUNT = Input.nextInt();
    
    List<Integer>[] adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(VERTEX_AMOUNT + 1).toArray(ArrayList[]::new);

    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      adjacencyList[numA].add(numB);
      adjacencyList[numB].add(numA);
    }

    int[] distances = getDistancesFromOne(adjacencyList);
    
    int maxDistanceNumber = 0;
    int maxDistance = Arrays.stream(distances).max().getAsInt();
    int maxDistanceCount = 0;

    for (int i = VERTEX_AMOUNT; i >= 1; i--) {
      if (distances[i] == maxDistance) {
        maxDistanceNumber = i;
        maxDistanceCount++;
      }
    }

    System.out.printf("%d %d %d", maxDistanceNumber, maxDistance, maxDistanceCount);
  }

  static int[] getDistancesFromOne(List<Integer>[] adjacencyList) {
    int[] distances = new int[adjacencyList.length];
    boolean[] visited = new boolean[adjacencyList.length];
    Queue<Integer> queue = new LinkedList<>();

    queue.add(1);
    visited[1] = true;

    while (queue.size() > 0) {
      int current = queue.poll();

      for (int adjacency : adjacencyList[current]) {
        if (visited[adjacency]) {
          continue;
        }
        visited[adjacency] = true;
        distances[adjacency] = distances[current] + 1;
        queue.add(adjacency);
      }
    }
    
    return distances;
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