import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    nodeMap = new HashMap<>();

    final int NODE_AMOUNT = Input.nextInt();
    final int START_NODE_NUMBER = Input.nextInt();
    final int DISTANCE_CAN_THROW = Input.nextInt();

    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int nodeNumA = Input.nextInt();
      int nodeNumB = Input.nextInt();

      Node nodeA = getOrCreateNode(nodeNumA);
      Node nodeB = getOrCreateNode(nodeNumB);

      nodeA.connect(nodeB);
      nodeB.connect(nodeA);
    }

    Node root = getOrCreateNode(START_NODE_NUMBER);

    List<Node> leaves = getLeaves(root);
    markNeedToVisit(leaves, DISTANCE_CAN_THROW);

    int minDistance = getMarkedPathDistance(root);

    System.out.print(minDistance);
  }

  static Node getOrCreateNode(int number) {
    Node node;
    if (nodeMap.containsKey(number)) {
      node = nodeMap.get(number);
    } else {
      node = new Node(number);
      nodeMap.put(number, node);
    }

    return node;
  }

  static List<Node> getLeaves(Node root) {
    if (root.isLeaf()) {
      return new ArrayList<>(List.of(root));
    }

    List<Node> leaves = new ArrayList<>();
    for (Node connectedNode : root.connectedNodes) {
      connectedNode.setParent(root);
      leaves.addAll(getLeaves(connectedNode));
    }

    return leaves;
  }

  static void markNeedToVisit(List<Node> leaves, int distanceCanThrow) {
    for (Node leaf : leaves) {
      markNeedToVisit(leaf, distanceCanThrow);
    }
  }

  static void markNeedToVisit(Node leaf, int distanceCanThrow) {
    Node markFrom = leaf;

    while (distanceCanThrow-- > 0) {
      markFrom = markFrom.parent;
      if (markFrom == null) {
        return;
      }
    }

    while (markFrom != null) {
      if (markFrom.needToVisit) {
        return;
      }
      markFrom.needToVisit = true;
      markFrom = markFrom.parent;
    }
  }

  static int getMarkedPathDistance(Node root) {
    if (root.needToVisit == false) {
      return 0;
    }

    int distance = root.parent != null ? 2 : 0;
    for (Node connectedNode : root.connectedNodes) {
      distance += getMarkedPathDistance(connectedNode);
    }

    return distance;
  }
}

class Node {
  public int number;
  public Node parent;
  public List<Node> connectedNodes;
  public boolean needToVisit;

  public Node(int number) {
    this.number = number;
    connectedNodes = new ArrayList<>();
  }

  public void connect(Node node) {
    connectedNodes.add(node);
  }

  public void setParent(Node node) {
    parent = node;
    connectedNodes.remove(node);
  }

  public boolean isLeaf() {
    return connectedNodes.size() == 0;
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