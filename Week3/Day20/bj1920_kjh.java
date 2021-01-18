import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
//// 깔끔한거 같슴다 굿굿~
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final Set<Long> set = new HashSet<Long>();

    for (int i = 0; i < N; i++) {
      set.add(Input.nextLong());
    }

    final int M = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      Long target = Input.nextLong();
      //// Set의 특성을 이용해 깔끔하게 짜신 것 같아요 굿
      int result = set.contains(target) ? (1) : (0);
      sb.append(result).append('\n');
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
