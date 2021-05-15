import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < T; i++) {
      sb.append(get01NumberCanDividedBy(Input.nextInt()))
        .append('\n');
    }

    System.out.print(sb);
  }
  
  static long get01NumberCanDividedBy(int N) {
    int bitmask = 1;

    long _01Number;
    while (true) {
      _01Number = binaryTo01Number(bitmask);
      //// while 문을 이용하셨으니 if break 문을 while의 조건으로 두면 더 깔끔할 것 같아요.
      if (_01Number % N == 0) {
        break;
      }
      bitmask++;
    }

    return _01Number;
  }

  static long binaryTo01Number(long binary) {
    long _01Number = 0;
    long digit = 1;

    while (binary > 0) {
      _01Number += digit * (binary % 2);
      binary >>= 1;

      digit *= 10;
    }
    return _01Number;
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