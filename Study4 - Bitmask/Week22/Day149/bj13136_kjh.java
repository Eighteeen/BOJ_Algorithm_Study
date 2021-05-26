import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔!
//// 22~
class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COL_SIZE = Input.nextInt();
    final int CCTV_SIZE = Input.nextInt();

    int rowRequired = calcRequiredCCTV(ROW_SIZE, CCTV_SIZE);
    int colRequired = calcRequiredCCTV(COL_SIZE, CCTV_SIZE);
    
    long totalRequired = (long) rowRequired * colRequired;

    System.out.print(totalRequired);
  }

  static int calcRequiredCCTV(int areaSize, int cctvSize) {
    int requiredAmount = areaSize / cctvSize;
    requiredAmount += (areaSize % cctvSize > 0) ? 1 : 0;

    return requiredAmount;
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