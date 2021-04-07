import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int totalDistanceMoved;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int NUMBER_OF_COWS = (int) Math.pow(2, N);

    int[] cows = new int[NUMBER_OF_COWS];

    for (int i = 0; i < NUMBER_OF_COWS; i++) {
      cows[i] = Input.nextInt();
    }

    skewedSort(cows);

    StringBuilder sb = new StringBuilder();
    sb.append(totalDistanceMoved)
      .append('\n');

    for (int i = 0; i < NUMBER_OF_COWS; i++) {
      sb.append(cows[i])
        .append('\n');
    }

    System.out.println(sb);
  }

  static void skewedSort(int[] items) {
    skewedSort(items, 0, items.length - 1);
  }

  static void skewedSort(int[] items, int beginIdx, int endIdx) {
    if (beginIdx >= endIdx) return;

    int middleIdx = (beginIdx + endIdx) / 2;

    skewedSort(items, beginIdx, middleIdx);
    skewedSort(items, middleIdx + 1, endIdx);
    skew(items, beginIdx, middleIdx, endIdx);
  }

  static void skew(int[] items, int beginIdx, int middleIdx, int endIdx) {
    int eachPartitionSize = endIdx - middleIdx;

    if (items[beginIdx] < items[middleIdx + 1]) return;

    for (int i = 0; i < eachPartitionSize; i++) {
      int leftPartitionIdx = beginIdx + i;
      int rightPartitionIdx = middleIdx + 1 + i;

      int tmp = items[leftPartitionIdx];
      items[leftPartitionIdx] = items[rightPartitionIdx];
      items[rightPartitionIdx] = tmp;

      totalDistanceMoved += 2 * eachPartitionSize;
    }
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