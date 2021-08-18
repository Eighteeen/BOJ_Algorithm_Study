import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final long NODE_AMOUNT = Input.nextLong();
    final int MAX_CHILDREN_AMOUNT = Input.nextInt();
    final int QUERY_AMOUNT = Input.nextInt();

    KTree tree = new KTree(NODE_AMOUNT, MAX_CHILDREN_AMOUNT);

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      long targetNodeA = Input.nextLong();
      long targetNodeB = Input.nextLong();

      result.append(tree.getDistanceBetween(targetNodeA, targetNodeB)).append('\n');
    }

    System.out.print(result);
  }
}

class KTree {
  private int maxChildrenAmount;

  public KTree(long nodeAmount, int maxChildrenAmount) {
    this.maxChildrenAmount = maxChildrenAmount;
  }

  public long getDistanceBetween(long nodeA, long nodeB) {
    if (maxChildrenAmount == 1) {
      return Math.abs(nodeA - nodeB);
    }

    long nodeIdxA = nodeA - 1;
    long nodeIdxB = nodeB - 1;

    long lca = getLCA(nodeIdxA, nodeIdxB);
    long distanceAToLCA = getDepthDifference(nodeIdxA, lca);
    long distanceBToLca = getDepthDifference(nodeIdxB, lca);

    return distanceAToLCA + distanceBToLca;
  }

  public long getLCA(long nodeIdxA, long nodeIdxB) {
    long depthDifference = getDepthDifference(nodeIdxA, nodeIdxB);
    while (depthDifference-- > 0) {
      if (nodeIdxA > nodeIdxB) {
        nodeIdxA = getParent(nodeIdxA);
      } else {
        nodeIdxB = getParent(nodeIdxB);
      }
    }

    while (nodeIdxA != nodeIdxB) {
      nodeIdxA = getParent(nodeIdxA);
      nodeIdxB = getParent(nodeIdxB);
    }

    return nodeIdxA;
  }

  public long getDepthDifference(long nodeIdxA, long nodeIdxB) {
    long depthA = getDepth(nodeIdxA);
    long depthB = getDepth(nodeIdxB);
    return Math.abs(depthA - depthB);
  }

  public long getDepth(long nodeIdx) {
    long depth = 0;
    while (nodeIdx > 0) {
      nodeIdx = getParent(nodeIdx);
      depth++;
    }
    return depth;
  }

  public long getParent(long nodeIdx) {
    return (nodeIdx - 1) / maxChildrenAmount;
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