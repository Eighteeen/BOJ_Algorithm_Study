import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

class Main {
  public static void main(String[] args) throws Exception {
    BigInteger startSum = BigInteger.valueOf(Input.nextInt());
    BigInteger endSum = BigInteger.valueOf(Input.nextInt());

    final int GREATER_THAN = 1;
    if (startSum.compareTo(endSum) == GREATER_THAN) {
      BigInteger tmp = startSum;
      startSum = endSum;
      endSum = tmp;
    }
    ////java BigInteger 메서드 배워가네요~
    BigInteger amountOfNumbers = endSum.subtract(startSum).add(BigInteger.ONE);

    System.out.print(amountOfNumbers.multiply(startSum.add(endSum)).divide(BigInteger.TWO));
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