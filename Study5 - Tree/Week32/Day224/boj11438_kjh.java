import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    Tree tree = new Tree(NODE_AMOUNT);
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();
      tree.addInterconnectedNodes(nodeA, nodeB);
    }

    StringBuilder result = new StringBuilder();
    final int QUERY_AMOUNT = Input.nextInt();
    
    tree.getReadyForLCA();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();
      result.append(tree.getLCA(nodeA, nodeB)).append('\n');
    }

    System.out.print(result);
  }
}

class Tree {
  private int nodeAmount;
  private List<Integer>[] adjacentNodes;
  private List<Integer>[] ancestors;
  private int[] depths;

  public Tree(int nodeAmount) {
    this.nodeAmount = nodeAmount;
    adjacentNodes = new ArrayList[nodeAmount + 1];
    ancestors = new ArrayList[nodeAmount + 1];
    depths = new int[nodeAmount + 1];
  }

  public void getReadyForLCA() {
    getReadyForLCA(1, new ArrayList<>(), 0);
  }

  private void getReadyForLCA(Integer node, List<Integer> myAncestors, int depth) {
    depths[node] = depth;
    ancestors[node] = new ArrayList<>();
    for (int ancestor : myAncestors) {
      ancestors[node].add(ancestor);
    }

    myAncestors.add(node);
    
    List<Integer> connectedNodes = adjacentNodes[node];
    for (int connectedNode : connectedNodes) {
      adjacentNodes[connectedNode].remove(node);
      getReadyForLCA(connectedNode, myAncestors, depth + 1);
    }

    myAncestors.remove(node);
  }

  public int getLCA(int nodeA, int nodeB) {
    if (depths[nodeA] > depths[nodeB]) {
      nodeA = getAncestorByDepth(nodeA, depths[nodeB]);
    } else if (depths[nodeB] > depths[nodeA]) {
      nodeB = getAncestorByDepth(nodeB, depths[nodeA]);
    }
    
    while (nodeA != nodeB) {
      nodeA = ancestors[nodeA].get(ancestors[nodeA].size() - 1);
      nodeB = ancestors[nodeB].get(ancestors[nodeB].size() - 1);
    }
    return nodeA;
  }

  private int getAncestorByDepth(int node, int depth) {
    List<Integer> myAncestors = ancestors[node];
    for (int ancestor : myAncestors) {
      if (depths[ancestor] == depth) {
        return ancestor;
      }
    }
    return 0;
  }

  public void addInterconnectedNodes(int nodeA, int nodeB) {
    connect(nodeA, nodeB);
    connect(nodeB, nodeA);
  }

  private void connect(int from, int to) {
    if (adjacentNodes[from] == null) {
      adjacentNodes[from] = new ArrayList<>();
    }
    adjacentNodes[from].add(to);
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