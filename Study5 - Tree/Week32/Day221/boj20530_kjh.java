import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack

class Main {
  public static void main(String[] args) throws Exception {
    final int EDGE_AMOUNT = Input.nextInt();
    final int QUERY_AMOUNT = Input.nextInt();

    Tree tree = new Tree(EDGE_AMOUNT);
    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();

      tree.addInterconnectedNodes(nodeA, nodeB);
    }

    List<Integer> cycleNodes = tree.getCycleNodes();

    Map<Integer, Integer> rootThatNodeBelongs = new HashMap<>();
    for (int i = 0; i < cycleNodes.size(); i++) {
      int root = cycleNodes.remove(i);
      List<Integer> ancestors = tree.getAncestorsExceptCycle(root, cycleNodes);
      cycleNodes.add(i, root);

      for (int ancestor : ancestors) {
        rootThatNodeBelongs.put(ancestor, root);
      }
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int beginNode = Input.nextInt();
      int endNode = Input.nextInt();

      int rootThatBeginNodeBelongs = rootThatNodeBelongs.get(beginNode);
      int rootThatEndNodeBelongs = rootThatNodeBelongs.get(endNode);

      int pathCount = rootThatBeginNodeBelongs == rootThatEndNodeBelongs ? 1 : 2;
      result.append(pathCount).append('\n');
    }

    System.out.print(result);
  }
}

class Tree {
  private int nodeAmount;
  private List<Integer>[] connectedInfos;
  private int[] parentInfos;

  public Tree(int nodeAmount) {
    this.nodeAmount = nodeAmount;
    connectedInfos = new ArrayList[nodeAmount + 1];
  }

  public void addInterconnectedNodes(int nodeA, int nodeB) {
    connect(nodeA, nodeB);
    connect(nodeB, nodeA);
  }

  public List<Integer> getCycleNodes() {
    return null;
  }

  public List<Integer> getAncestorsExceptCycle(int root, List<Integer> cycleNodes) {
    List<Integer> ancestors = new ArrayList<>();
    ancestors.add(root);

    List<Integer> connectedNodes = connectedInfos[root];
    for (Integer connectedNode : connectedNodes) {
      if (connectedNode == root || cycleNodes.contains(connectedNode)) {
        continue;
      }
      ancestors.addAll(getAncestorsExceptCycle(connectedNode, cycleNodes));
    }

    return ancestors;
  }

  private void connect(int from, int to) {
    if (connectedInfos[from] == null) {
      connectedInfos[from] = new ArrayList<>();
    }
    connectedInfos[from].add(to);
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