import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();
    BinaryTree = new BinaryTree(NODE_AMOUNT);

    for (int i = 2; i <= NODE_AMOUNT; i++) {
      int depth = Input.nextInt();
      tree.addNodeInSpecificDepth(depth);
      
      if (tree.isErrorCaused) {
        Sytsem.out.print(-1);
        return;
      }
    }

    System.out.print(tree.getANNodes());
  }
}

class BinaryTree {
  private int[] tree;
  public boolean isErrorCaused;

  public BinaryTree(int nodeAmount) {
    tree = new int[nodeAmount];
    Arrays.fill(tree, -1);
  }

  public void addNodeInSpecificDepth(int depth) {
    // 특정 깊이의 시작 인덱스 번호, 끝 인덱스 번호
  }

  private void numberNodes(int rootIdx) {
    // 왼쪽 자식 번호, 오른쪽 자식 번호
  }

  public String getANNodes() {

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