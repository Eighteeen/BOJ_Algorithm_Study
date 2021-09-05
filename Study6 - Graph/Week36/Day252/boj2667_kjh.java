import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Main {
  static boolean[] visited;
  static List<Integer>[] adjacencyList;

  public static void main(String[] args) throws Exception {
    final int MAP_SIZE = Input.nextInt();
    adjacencyList = new ArrayList[MAP_SIZE * MAP_SIZE];

    for (int i = 0; i < MAP_SIZE; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < MAP_SIZE; j++) {
        if (row.charAt(j) == '0') {
          continue;
        }
        
        int current = i * MAP_SIZE + j;
        adjacencyList[current] = new ArrayList<>();
        
        boolean isLeftConnected = current - 1 >= 0 && j >= 1 && adjacencyList[current - 1] != null;
        if (isLeftConnected) {
          adjacencyList[current].add(current - 1);
          adjacencyList[current - 1].add(current);
        }
        
        boolean isTopConnected = current - MAP_SIZE >= 0 && adjacencyList[current - MAP_SIZE] != null;
        if (isTopConnected) {
          adjacencyList[current].add(current - MAP_SIZE);
          adjacencyList[current - MAP_SIZE].add(current);
        }
      }
    }
    
    List<Integer> amountOfHomeEachGroup = new ArrayList<>();

    visited = new boolean[MAP_SIZE * MAP_SIZE];
    for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
      int amountOfHome = getAmountOfHome(i);
      if (amountOfHome > 0) {
        amountOfHomeEachGroup.add(amountOfHome);
      }
    }

    amountOfHomeEachGroup.sort(Comparator.naturalOrder());

    StringBuilder result = new StringBuilder();
    result.append(amountOfHomeEachGroup.size()).append('\n');

    for (Integer amountOfHome : amountOfHomeEachGroup)  {
      result.append(amountOfHome).append('\n');
    }

    System.out.print(result);
  }

  static int getAmountOfHome(int homeIndex) {
    if (visited[homeIndex]) {
      return 0;
    }
    visited[homeIndex] = true;

    if (adjacencyList[homeIndex] == null) {
      return 0;
    }

    int amountOfHome = 1;
    for (Integer adjacency : adjacencyList[homeIndex]) {
      if (visited[adjacency]) {
        continue;
      }
      amountOfHome += getAmountOfHome(adjacency);
    }

    return amountOfHome;
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