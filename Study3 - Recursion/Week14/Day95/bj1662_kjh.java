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

  //// isNumeric 조건문에서 next를 구하고 그 후에 다시 메소드에 들어오면 또 그 문자를 확인하고,
  //// isOpening 조건문에서 이미 확인했던 문자를 다시 확인하는 부분에서 알고리즘이 깔끔하게 느껴지지 않아 아쉬웠습니다.
  private int calcLengthOfABunch() {
    if (idx >= compressed.length()) return 0;
    
    //// 어떤 문자이든 idx++ 연산을 해주니 current 부분에서 ++ 연산을 하고 previous 부분에서 - 2를 해주는 게 깔끔할 것 같습니다.
    char current = compressed.charAt(idx);
    if (isNumeric(current)) {
      idx++;
      if (idx == compressed.length()) return 1;
      char next = compressed.charAt(idx);
      //// 최대 세가지 조건문을 거칠 필요 없이 isOpening에 대해 return 0을 하고 밑에 return 1을 해주면 더 깔끔할 것 같아요.
      if (isNumeric(next) || isClosing(next)) return 1;
      if (isOpening(next)) return 0;
    }

    if (isOpening(current)) {
      //// 변수 설정 부분에서 int형 변수로 toInt를 사용하는 게 더 깔끔할 것 같아요.
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