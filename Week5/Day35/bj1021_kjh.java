import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.IntStream;

class Main {
  public static void main(String[] args) throws Exception {
    Deque<Integer> deque = new ArrayDeque<>();
    
    final int N = Input.nextInt();
    IntStream.range(1, N + 1)
      .boxed()
      .forEach(item -> deque.offerLast(item));

    final int M = Input.nextInt();
    int[] targetNumber = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int countsToTarget = 0;
    for (int i = 0; i < M; i++) {
      int countToTarget = 0;
      int poped;
      while ((poped = deque.pollFirst()) != targetNumber[i]) {
        deque.offerLast(poped);
        countToTarget++; 
      }

      if (countToTarget > (deque.size() + 1) / 2) {
        countsToTarget += (deque.size() + 1) - countToTarget;
        continue;
      }
      countsToTarget += countToTarget;
    }

    System.out.println(countsToTarget);
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
    return new StringTokenizer(nextLine(), " ");
  }
}