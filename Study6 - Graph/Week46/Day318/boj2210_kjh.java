import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

class Main {
  static Set<String> combinations = new HashSet<>();
  static int[][] map;

  public static void main(String[] args) throws Exception {
    map = new int[5][5];
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        map[y][x] = Input.nextInt();
      }
    }

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        makeCombinations(y, x, 1, String.valueOf(map[y][x]));
      }
    }

    System.out.print(combinations.size());
  }
  
  //// 깔끔해요 : 22
  static void makeCombinations(int y, int x, int size, String combination) {
    if (size == 6) {
      combinations.add(combination);
      return;
    }

    int[] dy = { 0, -1, 0, 1};
    int[] dx = { -1, 0, 1, 0};

    for (int i = 0; i < 4; i++) {
      int aroundY = y + dy[i];
      int aroundX = x + dx[i];
      
      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= 5 || aroundX >= 5;
      if (outOfIndex) {
        continue;
      }

      makeCombinations(aroundY, aroundX, size + 1, combination + map[aroundY][aroundX]);
    }
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