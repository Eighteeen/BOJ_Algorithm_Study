import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class Main {
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();

    visited = new boolean[VERTEX_AMOUNT + 1];
    List<Integer>[] adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(VERTEX_AMOUNT + 1).toArray(List[]::new);

    for (int i = 0; i < VERTEX_AMOUNT - 2; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      adjacencyList[numA].add(numB);
      adjacencyList[numB].add(numA);
    }

    for (int num = 1; num <= VERTEX_AMOUNT; num++) {
      if (isThereAnyNotVisitedVertex(adjacencyList, num)) {
        System.out.printf("%d ", num);
      }
    }
  }

  //// 함수명이 약간 어색하게 느껴져요 이전에 방문을 해서 true인 경우가 있는데,
    //// 이런 경우에는 방문하지 않은 정점이라는 함수뜻에서 혼돈이 생기는 것 같습니다.
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