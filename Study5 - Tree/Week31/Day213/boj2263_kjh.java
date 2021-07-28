import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Main {
  static List<Integer> inorder;
  static List<Integer> postorder;

  static Map<Integer, Integer> inorderIdxMap;
  static StringBuilder sb;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    inorderIdxMap = new HashMap<>();
    inorder = new ArrayList<>();
    postorder = new ArrayList<>();

    for (int i = 0; i < NODE_AMOUNT; i++) {
      Integer node = Input.nextInt();
      inorder.add(node);
      inorderIdxMap.put(node, i);
    }
    for (int i = 0; i < NODE_AMOUNT; i++) {
      postorder.add(Input.nextInt());
    }

    sb = new StringBuilder();
    traversePreorder(0, NODE_AMOUNT - 1, 0, NODE_AMOUNT - 1);

    System.out.print(sb);
  }

  static void traversePreorder(int inStartIdx, int inEndIdx, int postStartIdx, int postEndIdx) {
    if (inStartIdx > inEndIdx) {
      return;
    }

    int root = postorder.get(postEndIdx);
    sb.append(root + " ");

    int inRootIdx = inorderIdxMap.get(root);
    int leftTreeSize = inRootIdx - inStartIdx;
    traversePreorder(inStartIdx, inRootIdx - 1, postStartIdx, postStartIdx + leftTreeSize - 1);
    traversePreorder(inRootIdx + 1, inEndIdx, postStartIdx + leftTreeSize, postEndIdx - 1);
  }
}

class Node {
  public Integer data;
  public Node left;
  public Node right;

  public Node(int data) {
    this.data = data;
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