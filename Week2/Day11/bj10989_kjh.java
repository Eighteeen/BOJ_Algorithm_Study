import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    Map<Integer, Integer> usedNumberCounts = new HashMap<>();

    final int N = Input.nextInt();
    for (int i = 0; i < N; i++) {
      int number = Input.nextInt();

      if (!usedNumberCounts.containsKey(number)) {
        usedNumberCounts.put(number, 1);
        continue;
      }
      int usedCount = usedNumberCounts.get(number);
      usedNumberCounts.put(number, usedCount + 1);
    }

    for (Entry<Integer, Integer> numberCount : usedNumberCounts.entrySet()) {
      for(int i = 0; i < numberCount.getValue(); i++) {
        sb.append(numberAndCount.getKey()).append("\n");
      }
    }

    System.out.print(sb);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}