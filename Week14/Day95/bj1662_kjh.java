import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    final String COMPRESSED = Input.nextLine();

    Decompressor decompressor = new Decompressor(COMPRESSED);
    System.out.print(decompressor.calcLength());
  }
}

class Decompressor {
  private Stack<Character> stack;
  private String compressed;
  private int idx;

  public Decompressor(String compressed) {
    this.compressed = compressed;
    stack = new Stack<>();
  }

  public int calcLength() {
    int length = 0;
    while (idx < compressed.length()) {
      length += calcLengthOfABunch();
    }
    return length;
  }

  private int calcLengthOfABunch() {
    if (idx >= compressed.length()) return 0;
    
    char current = compressed.charAt(idx);
    if (isNumeric(current)) {
      idx++;
      if (idx == compressed.length()) return 1;
      char next = compressed.charAt(idx);
      if (isNumeric(next) || isClosing(next)) return 1;
      if (isOpening(next)) return 0;
    }

    if (isOpening(current)) {
      char previous = compressed.charAt(idx - 1);
      idx++;
      stack.push('(');

      int currentOpened = stack.size();
      int lengthOfLowerBunches = 0;
      while (stack.size() >= currentOpened) {
        lengthOfLowerBunches += calcLengthOfABunch();
      }
      return toInt(previous) * lengthOfLowerBunches;
    }

    idx++;
    stack.pop();
    return 0;
  }

  private boolean isNumeric(char ch) {
    return ch >= '0' && ch <= '9';
  }

  private boolean isOpening(char ch) {
    return ch == '(';
  }

  private boolean isClosing(char ch) {
    return ch == ')';
  }

  private int toInt(char ch) {
    return ch - '0';
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