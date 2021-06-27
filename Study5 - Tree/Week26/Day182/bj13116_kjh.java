import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < T; i++) {
      int node1 = Input.nextInt();
      int node2 = Input.nextInt();

      sb.append(10 * getMostRecentCommonAncestor(node1, node2))
        .append('\n');
    }

    System.out.print(sb);
  }

  static int getMostRecentCommonAncestor(int nodeA, int nodeB) {
    return getCommonAncestors(nodeA, nodeB).stream()
      .mapToInt(x -> x)
      .max()
      .getAsInt();
  }

  static Set<Integer> getCommonAncestors(int nodeA, int nodeB) {
    Set<Integer> aAncestors = getAncestors(nodeA);
    Set<Integer> bAncestors = getAncestors(nodeB);

    Set<Integer> commonAncestors = new HashSet<>();
    commonAncestors.addAll(aAncestors);
    commonAncestors.retainAll(bAncestors);

    return commonAncestors;
  }

  static Set<Integer> getAncestors(int node) {
    Set<Integer> ancestors = new HashSet<>();
    ancestors.add(node);
    
    int parent = node;
    while (parent > 1) {
      parent = getParent(parent);
      ancestors.add(parent);
    }

    return ancestors;
  }

  static int getParent(int node) {
    return node / 2;
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