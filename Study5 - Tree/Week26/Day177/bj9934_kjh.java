import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int DEPTH = Input.nextInt();
    int nodeAmount = (int) Math.pow(2, DEPTH) - 1;
    
    List<Integer> inorderNodes = new ArrayList<>();
    for (int i = 0; i < nodeAmount; i++) {
      inorderNodes.add(Input.nextInt());
    }

    List<List<Integer>> inorderSubtrees = new ArrayList<>();
    inorderSubtrees.add(inorderNodes);
    
    printByLevel(inorderSubtrees);
  }

  static void printByLevel(List<List<Integer>> inorderSubtrees) {
    if (inorderSubtrees.size() == 0) return;

    List<List<Integer>> newSubtrees = new ArrayList<>();

    for (List<Integer> tree : inorderSubtrees) {
      System.out.printf("%d ", getRoot(tree));
      if (tree.size() == 1) continue;

      int size = tree.size();
      int rootIdx = size / 2;
      newSubtrees.add(tree.subList(0, rootIdx));
      newSubtrees.add(tree.subList(rootIdx + 1, size));
    }
    System.out.println();

    printByLevel(newSubtrees);
  }

  static int getRoot(List<Integer> tree) {
    int rootIdx = tree.size() / 2;
    return tree.get(rootIdx);
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