import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

//// 이런 방법도 있군요!
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    int[] xi = new int[N];
    int totalSum = 0;

    for (int i = 0; i < N; i++) {
      xi[i] = Input.nextInt();
      totalSum += xi[i];
    }

    //// long으로도 가능하니 long으로 하시면 좋을 것 같아요!
    BigInteger sigma = BigInteger.ZERO;
    int sumOfBackOperands = totalSum;

    for (int i = 0; i < N - 1; i++) {
      int frontOperand = xi[i];
      sumOfBackOperands -= frontOperand;
      sigma = sigma.add(BigInteger.valueOf(frontOperand * sumOfBackOperands));
    }

    System.out.print(sigma);
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