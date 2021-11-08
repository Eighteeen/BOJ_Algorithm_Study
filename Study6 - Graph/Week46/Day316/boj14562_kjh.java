import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static final int MAX_VALUE = 100000000;

  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < T; i++) {
      int myScore = Input.nextInt();
      int hisScore = Input.nextInt();

      sb.append(getLeastKicks(myScore, hisScore))
        .append('\n');
    }

    System.out.print(sb);
  }

  static int getLeastKicks(int myScore, int hisScore) {
    if (myScore > hisScore) {
      return MAX_VALUE;
    }
    if (myScore == hisScore) {
      return 0;
    }

    int kickA = 1 + getLeastKicks(myScore * 2, hisScore + 3); 
    int kickB = 1 + getLeastKicks(myScore + 1, hisScore);

    return Math.min(kickA, kickB);
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