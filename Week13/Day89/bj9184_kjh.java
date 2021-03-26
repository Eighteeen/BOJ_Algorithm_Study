import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  static int[][][] excitingValues;

  public static void main(String[] args) throws Exception {
    excitingValues = new int[21][21][21];

    StringBuilder sb = new StringBuilder();
    while (true) {
      int a = Input.nextInt();
      int b = Input.nextInt();
      int c = Input.nextInt();
      if (a == -1 && b == -1 && c == -1) break;

      int excitingValue = excitingFunction(a, b, c);
      sb.append("w(").append(a).append(", ")
        .append(b).append(", ")
        .append(c).append(") = ")
        .append(excitingValue).append('\n');
    }

    System.out.print(sb);
  }

  static int excitingFunction(int a, int b, int c) {
    if (a <= 0 || b <= 0 | c <= 0) return 1;
    
    if (a > 20 || b > 20 | c > 20) {
      return loadExcitingValue(20, 20, 20);
    }

    if (a < b && b < c) {
      return loadExcitingValue(a, b, c-1)
        + loadExcitingValue(a, b-1, c-1)
        - loadExcitingValue(a, b-1, c);
    }

    return loadExcitingValue(a-1, b, c)
      + loadExcitingValue(a-1, b-1, c)
      + loadExcitingValue(a-1, b, c-1)
      - loadExcitingValue(a-1, b-1, c-1);
  }
  
  static int loadExcitingValue(int a, int b, int c) {
    if (excitingValues[a][b][c] == 0) {
      excitingValues[a][b][c] = excitingFunction(a, b, c);
      return excitingValues[a][b][c];
    }

    return excitingValues[a][b][c];
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
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
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}