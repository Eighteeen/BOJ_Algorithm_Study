import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;

// 최대한 이름 직관적으로 지어서 최대한 로직 이해 쉽도록 신경써봤는데
// 이 부분에 대해서 솔직한 피드백 주시면 감사하겠습니다 (__)

//// 저는 저랑 비슷한 풀이여서 이름보고 이해가 잘 됐어요~ 깔끔해요!
//// 변수명 이해는 잘 되지만 축약 가능한 단어는 축약하는 게 오히려 가독하는 데 더 빠를 것 같습니다.

//// 몇몇 변수명에 index 용어가 들어갔는데,
  //// index라고 하기엔 1부터 시작하기 때문에 nth, sequence 정도의 변수명이 적합해보입니다.

class Main {
  public static void main(String[] args) throws Exception {
    final BigInteger GEN = BigInteger.valueOf(Input.nextInt());
    final BigInteger HASH = BigInteger.valueOf(1000000007);
    
    //// number 변수명은 여러가지로 해석할 수 있는 여지가 있어서 좀 더 구체적인 변수명을 지으면 좋을 것 같습니다.
    BigInteger number = BigInteger.ZERO;

    BigInteger lastNumberOfPreviousGen = BigInteger.ZERO;
    BigInteger lastChildIndexInGen = BigInteger.ONE;
    BigInteger lastGenNodeCount = BigInteger.ONE;

    StringBuilder sb = new StringBuilder();
    //// for문의 i가 BigInteger가 되면서 가독성이 확 내려간 느낌이라서,
      //// GEN과 i는 int로 설정하고 for문 내에서 i를 BigInteger로 설정하여 연산하는 건 어떠신가요?
    for (BigInteger i = BigInteger.ONE; i.compareTo(GEN) <= 0; i = i.add(BigInteger.ONE)) {
      BigInteger childIndex = BigInteger.valueOf(Input.nextInt());

      BigInteger genNodeCount = lastGenNodeCount.multiply(i);
      //// genNodeCount.divide(lastGenNodeCount) = i 인데 필요없는 연산을 더 하고 있는 것 같습니다.
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