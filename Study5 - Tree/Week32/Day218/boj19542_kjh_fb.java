//// 구글링->모범 로직을 참고하여 직접 짰음

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
    fillAndReturnDescendantsDepth(root);

    int nodesNeedToVisit = 0;
    for (int i = 1; i <= NODE_AMOUNT; i++) {
      Node node = nodeMap.get(i);
      if (node.descendantsDepth >= DISTANCE_CAN_THROW) {
        nodesNeedToVisit++;
      }
    }

    if (nodesNeedToVisit <= 1) {
      System.out.print(0);
      return;
    }

    final int EXCEPT_ROOT = -2;

    System.out.print(nodesNeedToVisit * 2 + EXCEPT_ROOT);
  }

  static int fillAndReturnDescendantsDepth(Node root) {
    if (root.isLeaf()) {
      return 0;
    }

    int descendantsDepth = 0;
    for (Node connectedNode : root.connectedNodes) {
      connectedNode.disconnect(root);
      descendantsDepth = Math.max(descendantsDepth, 1 + fillAndReturnDescendantsDepth(connectedNode));
    }

    root.descendantsDepth = descendantsDepth;
    return descendantsDepth;
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

}

class Node {
  public int number;
  public List<Node> connectedNodes;
  public int descendantsDepth;

  public Node(int number) {
    this.number = number;
    connectedNodes = new ArrayList<>();
    descendantsDepth = 0;
  }

  public void connect(Node node) {
    connectedNodes.add(node);
  }

  public void disconnect(Node node) {
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