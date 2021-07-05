import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

// 최대한 이름 직관적으로 지어서 최대한 로직 이해 쉽게 신경써봤는데
// 이 부분에 대해서 솔직한 피드백 주시면 감사하겠습니다
class Main {
  public static void main(String[] args) throws Exception {
    final BigInteger GEN = BigInteger.valueOf(Input.nextInt());
    final BigInteger HASH = BigInteger.valueOf(1000000007);
    
    BigInteger number = BigInteger.ZERO;

    BigInteger lastNumberOfPreviousGen = BigInteger.ZERO;
    BigInteger lastChildIndexInGen = BigInteger.ONE;
    BigInteger lastGenNodeCount = BigInteger.ONE;

    StringBuilder sb = new StringBuilder();
    for (BigInteger i = BigInteger.ONE; i.compareTo(GEN) <= 0; i = i.add(BigInteger.ONE)) {
      BigInteger childIndex = BigInteger.valueOf(Input.nextInt());

      BigInteger genNodeCount = lastGenNodeCount.multiply(i);
      BigInteger childIndexInGen = genNodeCount
        .divide(lastGenNodeCount)
        .multiply(
          lastChildIndexInGen.
          subtract(BigInteger.ONE))
        .add(childIndex);

      number = lastNumberOfPreviousGen.add(childIndexInGen);

      lastNumberOfPreviousGen = lastNumberOfPreviousGen.add(genNodeCount);
      lastChildIndexInGen = childIndexInGen;
      lastGenNodeCount = genNodeCount;

      sb.append(number.remainder(HASH)).append('\n');
    }

    System.out.print(sb);
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