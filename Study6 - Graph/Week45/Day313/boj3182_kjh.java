import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔해요
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    int[] eachChild = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      int child = Input.nextInt();
      eachChild[i] = child;
    }

    int maxChildren = 0;
    int maxChildrenIndex = 1;
    for (int i = N; i >= 1; i--) {
      int childrenAmount = getChildrenAmount(eachChild, i);
      if (childrenAmount >= maxChildren) {
        maxChildren = childrenAmount;
        maxChildrenIndex = i;
      }
    }

    System.out.print(maxChildrenIndex);
  }

  static int getChildrenAmount(int[] eachChild, int index) {
    boolean[] visited = new boolean[eachChild.length];

    int childrenAmount = 0;
    int child = index;

    while (!visited[child]) {
      visited[child] = true;
      child = eachChild[child];
      childrenAmount++;
    }

    return childrenAmount;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}