import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 무난합니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int X = Input.nextInt();

    if (X == 1) {
      System.out.print("1/1");
      return;
    }

    printNthFraction(X);
  }
  
  static void printNthFraction(int X) {
    int numerator = 1;
    int denominator = 2;
    boolean isTopRightToBottomLeft = true;

    int nth = 2;
    while (nth < X) {
      if (isTopRightToBottomLeft) {
        numerator++;
        denominator--;
      } else {
        numerator--;
        denominator++;
      }

      nth++;
      if (nth == X) break;

      if (numerator == 1 || denominator == 1) {
        numerator = (isTopRightToBottomLeft) ? (numerator + 1) : (numerator);
        denominator = (!isTopRightToBottomLeft) ? (denominator + 1) : (denominator);
        isTopRightToBottomLeft = !isTopRightToBottomLeft;

        nth++;
      }
    }
    
    System.out.printf("%d/%d\n", numerator, denominator);
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