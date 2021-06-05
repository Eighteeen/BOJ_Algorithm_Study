import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

////깔끔
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int TEST_CASES = Input.nextInt();

    for (int i = 0; i < TEST_CASES; i++) {
      int yonsei = 0;
      int korea = 0;
      for (int j = 0; j < 9; j++) {
        yonsei += Input.nextInt();
        korea += Input.nextInt();
      }

      sb.append(judge(yonsei, korea))
        .append('\n');
    }

    System.out.print(sb);
  }

  static String judge(int yonsei, int korea) {
    if (yonsei > korea) {
      return "Yonsei";
    } else if (korea > yonsei) {
      return "Korea";
    } else {
      return "Draw";
    }
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