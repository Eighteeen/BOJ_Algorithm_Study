import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int START_POINT = Input.nextInt();
    final int END_POINT = Input.nextInt();
    
    int shortestDistance = getShortestDistance(START_POINT, END_POINT);
    System.out.print(shortestDistance);
  }

  static int getShortestDistance(int startPoint, int endPoint) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(startPoint);
    
    int[] shortestDistanceTo = new int[100001];
    shortestDistanceTo[startPoint] = 0;

    boolean[] visited = new boolean[100001];
    visited[startPoint] = true;

    while (queue.size() > 0) {
      int current = queue.poll();

      int[] adjacencies = {
        current - 1,
        current + 1,
        current * 2
      };

      for (int adjacency : adjacencies) {
        if (adjacency < 0 || adjacency > 100000) {
          continue;
        }
        if (visited[adjacency]) {
          continue;
        }
        visited[adjacency] = true;
        queue.add(adjacency);
        shortestDistanceTo[adjacency] = shortestDistanceTo[current] + 1;
      }
    }

    return shortestDistanceTo[endPoint];
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