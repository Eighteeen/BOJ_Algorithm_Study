import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int maxResult = Integer.MIN_VALUE;
  static int minResult = Integer.MAX_VALUE;

  static int mapSize;
  static char[][] map;

  static int[] dy = { 0, 1 };
  static int[] dx = { 1, 0 };

  public static void main(String[] args) throws Exception {
    mapSize = Input.nextInt();

    map = new char[mapSize][mapSize];
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        map[i][j] = Input.nextChar();
      }
    }
    
    updateMinMaxResult(0, 0, '+', 0);

    System.out.printf("%d %d", maxResult, minResult);
  }

  static void updateMinMaxResult(int y, int x, char operator, int result) {
    char current = map[y][x];

    if (current >= '0' && current <= '5') {
      if (operator == '+') {
        result += current - '0';
      } else if (operator == '-') {
        result -= current - '0';
      } else if (operator == '*') {
        result *= current - '0';
      }
    } else {
      operator = current;
    }

    if (y == mapSize - 1 && x == mapSize - 1) {
      maxResult = Math.max(maxResult, result);
      minResult = Math.min(minResult, result);
      return;
    }


    for (int i = 0; i < 2; i++) {
      int aroundY = y + dy[i];
      int aroundX = x + dx[i];

      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= mapSize || aroundX >= mapSize;
      if (outOfIndex) {
        continue;
      }

      updateMinMaxResult(aroundY, aroundX, operator, result);
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