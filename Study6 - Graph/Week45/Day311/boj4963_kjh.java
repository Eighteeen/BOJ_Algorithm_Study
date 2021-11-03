import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class Main {
  static boolean[] visited;
  static List<Integer>[] adjacencyList;

  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    while (true) {
      int columnSize = Input.nextInt();
      int rowSize = Input.nextInt();
      if (columnSize + rowSize == 0) {
        break;
      }

      adjacencyList = new ArrayList[rowSize * columnSize];
      visited = new boolean[rowSize * columnSize];

      for (int i = 0; i < rowSize; i++) {
        for (int j = 0; j < columnSize; j++) {
          char block = Input.nextChar();
          if (block == '0') {
            continue;
          }
          
          int current = i * columnSize + j;
          adjacencyList[current] = new ArrayList<>();
          
          boolean isLeftConnected = current - 1 >= 0 && j >= 1 && adjacencyList[current - 1] != null;
          if (isLeftConnected) {
            adjacencyList[current].add(current - 1);
            adjacencyList[current - 1].add(current);
          }
          
          boolean isTopConnected = current - columnSize >= 0 && adjacencyList[current - columnSize] != null;
          if (isTopConnected) {
            adjacencyList[current].add(current - columnSize);
            adjacencyList[current - columnSize].add(current);
          }

          boolean isLeftTopConnected = j >= 1 && current - columnSize - 1 >= 0 && adjacencyList[current - columnSize - 1] != null;
          if (isLeftTopConnected) {
            adjacencyList[current].add(current - columnSize - 1);
            adjacencyList[current - columnSize - 1].add(current);
          }

          boolean isRightTopConnected = j < columnSize - 1 && current - columnSize + 1 >= 0 && adjacencyList[current - columnSize + 1] != null;
          if (isRightTopConnected) {
            adjacencyList[current].add(current - columnSize + 1);
            adjacencyList[current - columnSize + 1].add(current);
          }
        }
      }
      
      result.append(countConnectedComponents(adjacencyList)).append('\n');
    }

    System.out.print(result);
  }

  static int countConnectedComponents(List<Integer>[] adjacencyList) {
    int count = 0;
    for (int i = 0; i < adjacencyList.length; i++) {
      if (adjacencyList[i] == null) {
        continue;
      }
      count += isThereAnyNotVisitedVertex(adjacencyList, i) ? 1 : 0;
    }
    return count;
  }

  static boolean isThereAnyNotVisitedVertex(List<Integer>[] adjacencyList, int startVertex) {
    if (visited[startVertex]) {
      return false;
    }
    visited[startVertex] = true;

    for (int adjacency : adjacencyList[startVertex]) {
      if (visited[adjacency]) {
        continue;
      }
      isThereAnyNotVisitedVertex(adjacencyList, adjacency);
    }
    
    return true;
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