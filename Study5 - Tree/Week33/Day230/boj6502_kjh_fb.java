import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();
    
    int pizzaNum = 1;
    while (true) {
      int tableRadius = Input.nextInt();
      if (tableRadius == 0) break;
      int pizzaWidth = Input.nextInt();
      int pizzaHeight = Input.nextInt();
      
      boolean isFitOnTheTable = isFitOnTheTable(tableRadius, pizzaWidth, pizzaHeight);
      result.append("Pizza ").append(pizzaNum++);
      result.append(isFitOnTheTable ? " fits on the table." : " does not fit on the table.")
            .append('\n');
    }
    System.out.print(result);
  }

  //// pizzaWidth <= tableDiameter 조건과  pizzaHeight <= tableDiameter 조건 없어도 성립 됩니다. : 22
  //// 아 return 방식 good
  //// => 아 그르네요.. 땡큐!
  static boolean isFitOnTheTable(int tableRadius, int pizzaWidth, int pizzaHeight) {
    int tableDiameter = tableRadius * 2;
    double pizzaDiagonalLine = Math.sqrt(Math.pow(pizzaWidth, 2) + Math.pow(pizzaHeight, 2));
    return pizzaDiagonalLine <= tableDiameter;
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