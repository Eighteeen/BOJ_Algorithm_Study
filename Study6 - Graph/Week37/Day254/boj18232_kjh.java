import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

//// 깔끔
class Main {
  static List<Integer>[] adjacencyList;

  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    final int STATION_AMOUNT = Input.nextInt();
    final int START_VERTEX = Input.nextInt();
    final int END_VERTEX = Input.nextInt();

    adjacencyList = new ArrayList[VERTEX_AMOUNT + 1];

    adjacencyList[1] = new ArrayList<>();
    for (int i = 2; i <= VERTEX_AMOUNT; i++) {
      adjacencyList[i - 1].add(i);
      
      adjacencyList[i] = new ArrayList<>();
      adjacencyList[i].add(i - 1);
    }

    for (int i = 0; i < STATION_AMOUNT; i++) {
      int stationA = Input.nextInt();
      int stationB = Input.nextInt();

      adjacencyList[stationA].add(stationB);
      adjacencyList[stationB].add(stationA);
    }

    int[] shortestDistances = getShortestDistancesFrom(START_VERTEX, adjacencyList);
    System.out.print(shortestDistances[END_VERTEX]);
  }

  static int[] getShortestDistancesFrom(Integer fromVertex, List<Integer>[] adjacencyList) {
    int[] shortestDistances = new int[adjacencyList.length];
    shortestDistances[fromVertex] = 0;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(fromVertex);

    boolean[] visited = new boolean[adjacencyList.length];
    visited[fromVertex] = true;

    while (queue.size() > 0) {
      Integer vertex = queue.poll();

      for (Integer adjacency : adjacencyList[vertex]) {
        if (visited[adjacency]) {
          continue;
        }
        visited[adjacency] = true;
        queue.add(adjacency);
        shortestDistances[adjacency] = shortestDistances[vertex] + 1;
      }
    }

    return shortestDistances;
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