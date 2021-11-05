import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//// 예전에 푸신 것 같은데 dx, dy로  설정해서 푸는게 훨씬 깔끔했을 것 같아요!! : 22 한번에 알아보기 힘들었어요
//// => 업그레이드 완! 피드백 감사!
class Main {
  static boolean[][] visited;
  static List<Coordinate>[][] adjacencyList;

  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();
    
    int[] dy = { 0, -1, -1, -1 };
    int[] dx = { -1, -1, 0, 1 };

    while (true) {
      int columnSize = Input.nextInt();
      int rowSize = Input.nextInt();
      if (columnSize + rowSize == 0) {
        break;
      }

      adjacencyList = new ArrayList[rowSize][columnSize];
      visited = new boolean[rowSize][columnSize];

      for (int y = 0; y < rowSize; y++) {
        for (int x = 0; x < columnSize; x++) {
          char block = Input.nextChar();
          if (block == '0') {
            continue;
          }
          
          adjacencyList[y][x] = new ArrayList<>();

          for (int i = 0; i < 4; i++) {
            int aroundY = y + dy[i];
            int aroundX = x + dx[i];

            boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= adjacencyList.length || aroundX >= adjacencyList[0].length;
            if (outOfIndex) {
              continue;
            }
            boolean isOcean = adjacencyList[aroundY][aroundX] == null;
            if (isOcean) {
              continue;
            }

            adjacencyList[y][x].add(new Coordinate(aroundY, aroundX));
            adjacencyList[aroundY][aroundX].add(new Coordinate(y, x));
          }
        }
      }
      
      result.append(countConnectedComponents(adjacencyList)).append('\n');
    }

    System.out.print(result);
  }

  static int countConnectedComponents(List<Coordinate>[][] adjacencyList) {
    int count = 0;
    for (int y = 0; y < adjacencyList.length; y++) {
      for (int x = 0; x < adjacencyList[0].length; x++) {
        if (adjacencyList[y][x] == null) {
          continue;
        }
        count += isThereAnyNotVisitedVertex(adjacencyList, y, x) ? 1 : 0;
      }
    }
    return count;
  }

  static boolean isThereAnyNotVisitedVertex(List<Coordinate>[][] adjacencyList, int startY, int startX) {
    if (visited[startY][startX]) {
      return false;
    }
    visited[startY][startX] = true;

    for (Coordinate adjacency : adjacencyList[startY][startX]) {
      int y = adjacency.y;
      int x = adjacency.x;
      if (visited[y][x]) {
        continue;
      }
      isThereAnyNotVisitedVertex(adjacencyList, y, x);
    }
    
    return true;
  }
}

class Coordinate {
  int y;
  int x;
  
  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
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
