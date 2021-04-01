import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 전체적으로 깔끔합니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int TEST_CASE = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < TEST_CASE; i++) {
      int level = Input.nextInt();

      int targetCol = Input.nextInt();
      int targetRow = Input.nextInt();
      sb.append(isCrystal(level, targetRow, targetCol) ? "crystal" : "empty")
        .append('\n');
    }

    System.out.print(sb);
  }

  static boolean isCrystal(int level, int row, int col) {
    if (row < 0 || col < 0) return false;

    int prevSize = (int) Math.pow(5, level - 1);

    //// row >= prevSize 조건은 체크할 필요가 없네요.
    boolean isInLargerSquare = (col >= prevSize && col < prevSize * 4 && row < prevSize)
      || (col >= prevSize * 2 && col < prevSize * 3 && row >= prevSize && row < prevSize * 2);
    
    if (isInLargerSquare) return true;
    if (level == 1) return false;

    //// 사소하지만 첫번째 호출문에서는 level - 1 로 작성하시고 두번째, 세번째 호출문에서는 level -1 이렇게 작성하셨네요. (불-편 ㅋㅋ)
    boolean isInPrevRepeat = isCrystal(level - 1, row - prevSize, col - prevSize) ||
      isCrystal(level -1, row - prevSize * 2, col - prevSize * 2) ||
      isCrystal(level -1, row - prevSize, col - prevSize * 3);

    return isInPrevRepeat;
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