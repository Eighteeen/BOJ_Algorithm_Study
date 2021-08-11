import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    int caseNum = 1;
    while (true) {
      int nodeAmount = Input.nextInt();
      int edgeAmount = Input.nextInt();
      if (nodeAmount == 0 && edgeAmount == 0) {
        break;
      }

      Forest forest = new Forest(nodeAmount);
      for (int i = 0; i < edgeAmount; i++) {
        int nodeA = Input.nextInt();
        int nodeB = Input.nextInt();
        forest.addEdge(nodeA, nodeB);
      }

      result.append("Case ").append(caseNum++).append(": ").append(forest.toString()).append('\n');
    }

    System.out.print(result);
  }
}

class Forest {
  private int nodeAmount;
  private List<Tree> trees;
  private int[] treeIndexNodeBelongs;

  public Forest(int nodeAmount) {
    this.nodeAmount = nodeAmount;

    trees = new ArrayList<>();

    treeIndexNodeBelongs = new int[nodeAmount + 1];
    Arrays.fill(treeIndexNodeBelongs, -1);
  }

  public void addEdge(int nodeA, int nodeB) {
    int treeIndex = Math.max(treeIndexNodeBelongs[nodeA], treeIndexNodeBelongs[nodeB]);

    if (treeIndex == -1) {
      treeIndex = trees.size();
      trees.add(new Tree());
    }

    treeIndexNodeBelongs[nodeA] = treeIndex;
    treeIndexNodeBelongs[nodeB] = treeIndex;
    trees.get(treeIndex).addEdge(nodeA, nodeB);
  }

  @Override
  public String toString() {
    int treeAmount = getTreeAmount();

    if (treeAmount == 0) {
      return "No trees.";
    } else if (treeAmount == 1) {
      return "There is one tree.";
    }

    return "A forest of " + treeAmount + " trees.";
  }

  private int getTreeAmount() {
    int treeAmount = 0;
    for (Tree tree : trees) {
      treeAmount += tree.isAvailable() ? 1 : 0;
    }

    for (int i = 1; i <= nodeAmount; i++) {
      boolean isSingleNode = treeIndexNodeBelongs[i] == -1;
      treeAmount += isSingleNode ? 1 : 0;
    }

    return treeAmount;
  }
}

class Tree {
  private Set<Integer> nodeSet;
  private int edgeAmount;

  public Tree() {
    nodeSet = new HashSet<>();
  }

  public void addEdge(int nodeA, int nodeB) {
    nodeSet.add(nodeA);
    nodeSet.add(nodeB);

    edgeAmount++;
  }

  public boolean isAvailable() {
    return edgeAmount < nodeSet.size();
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