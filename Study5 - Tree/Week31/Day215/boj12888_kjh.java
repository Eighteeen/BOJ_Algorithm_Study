import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static Long[] leastCarsForEachHeight;

  public static void main(String[] args) throws Exception {
    final int TREE_HEIGHT = Input.nextInt();
    leastCarsForEachHeight = new Long[61];
    leastCarsForEachHeight[0] = 1L;
    leastCarsForEachHeight[1] = 1L;

    Long leastCars = getLeastCars(TREE_HEIGHT);
    System.out.print(leastCars);
  }

  //// 깔끔해요 : 22
  static Long getLeastCars(int height) {
    if (leastCarsForEachHeight[height] != null) {
      return leastCarsForEachHeight[height];
    }

    long leastCarsForInnerTrees = 0L;
    for (int i = height - 2; i >= 1; i--) {
      leastCarsForInnerTrees += getLeastCars(i) * 2;
    }

    long leastCars = leastCarsForInnerTrees + 3;
    leastCarsForEachHeight[height] = leastCars;
    return leastCars;
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