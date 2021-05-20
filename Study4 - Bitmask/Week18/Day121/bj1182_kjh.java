import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔합니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_OF_INTEGER = Input.nextInt();
    final int TARGET_SUM = Input.nextInt();
    
    int[] integers = new int[NUMBER_OF_INTEGER];
    for (int i = 0; i < NUMBER_OF_INTEGER; i++) {
      integers[i] = Input.nextInt();
    }

    int cases = 0;

    //// 범위가 충분히 남는데 long을 사용하신 이유가 뭔가요?
    long set = (1 << NUMBER_OF_INTEGER) - 1;
    for (long subset = set; subset > 0; subset--) {
      int sum = 0;
      for (int i = 0; i < NUMBER_OF_INTEGER; i++) {
        if ((subset & (1 << i)) == 0) continue;
        sum += integers[i];
      }

      if (sum == TARGET_SUM) cases++;
    }

    System.out.print(cases);
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