import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      int a = Input.nextInt();
      int b = Input.nextInt();

      sb.append(getLastComputer(a, b))
        .append('\n');
    }
    
    System.out.print(sb);
  }

  //// 무난하고 간단하게 짜신 것 같습니다.
  //// 하지만 어느지점 부터는 일의 자리수가 반복된다는 점을 이용하면 시간적 측면에서 훨씬 효율적으로 바꿀 수 있어 이점을 이용해보시면 좋을 것 같습니다!
  static int getLastComputer(int a, int b) {
    int lastComputer = 1;

    for (int i = 0; i < b; i++) {
      lastComputer *= a;
      lastComputer %= 10;
    }

    return (lastComputer == 0) ? (10) : (lastComputer);
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