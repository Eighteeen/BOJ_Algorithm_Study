import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

////깔끔해요 : 22 무난 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int VACATION_DAYS = Input.nextInt();
    final int KOREAN_PAGES = Input.nextInt();
    final int MATH_PAGES = Input.nextInt();
    final int KOREAN_PAGES_PER_DAY = Input.nextInt();
    final int MATH_PAGES_PER_DAY = Input.nextInt();

    int koreanRequired = KOREAN_PAGES / KOREAN_PAGES_PER_DAY;
    koreanRequired += (KOREAN_PAGES % KOREAN_PAGES_PER_DAY > 0) ? 1 : 0;

    int mathRequired = MATH_PAGES / MATH_PAGES_PER_DAY;
    mathRequired += (MATH_PAGES % MATH_PAGES_PER_DAY > 0) ? 1 : 0;
    
    int requiredDays = Math.max(koreanRequired, mathRequired);
    
    int freeDays = VACATION_DAYS - requiredDays;
    
    System.out.print(freeDays);
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