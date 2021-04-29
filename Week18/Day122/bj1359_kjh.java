import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔합니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_RANGE = Input.nextInt();
    final int AMOUNT_PICKING = Input.nextInt();
    final int LUCKY_AMOUNT_STANDARD = Input.nextInt();

    int allNumberPicked = (1 << NUMBER_RANGE) - 1;

    int allCases = 0;
    int luckyCases = 0;

    for (int i = 1; i <= allNumberPicked; i++) {
      if (Integer.bitCount(i) != AMOUNT_PICKING) {
        continue;
      }
      allCases++;

      int luckyAmount = 0;
      for (int j = 0; j < AMOUNT_PICKING; j++) {
        if ((i & (1 << j)) > 0) {
          luckyAmount++;
        }
      }
      
      if (luckyAmount >= LUCKY_AMOUNT_STANDARD) {
        luckyCases++;
      }
    }

    System.out.printf("%.9f", (double) luckyCases / allCases);
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