import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      int base = Input.nextInt();
      int exponent = Input.nextInt();

      sb.append(getLastComputer(base, exponent))
        .append('\n');
    }
    
    System.out.print(sb);
  }

  //// 무난하고 간단하게 짜신 것 같습니다.
  //// 하지만 어느지점 부터는 일의 자리수가 반복된다는 점을 이용하면 시간적 측면에서 훨씬 효율적으로 바꿀 수 있어 이점을 이용해보시면 좋을 것 같습니다!
  //// => 1008ms -> 156ms의 극적인 성능 향상을 이뤄냈습니다. 피드백 감사합니다! 
  static int getLastComputer(int base, int exponent) {
    int[] powerCycle = getPowerCycle(base % 10);

    int cycleSize = powerCycle.length;
    int cycleIndex = (exponent - 1) % cycleSize;

    int lastComputer = powerCycle[cycleIndex];
    lastComputer = lastComputer == 0 ? 10 : lastComputer;

    return lastComputer;
  }

  static int[] getPowerCycle(int base) {
    List<Integer> powerCycle = new ArrayList<>();
    powerCycle.add(base);

    int powered = (base * base) % 10;
    while (powered != base) {
      powerCycle.add(powered);

      powered *= base;
      powered %= 10;
    }

    return powerCycle.stream()
      .mapToInt(integer -> integer.intValue())
      .toArray();
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