import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

////깔끔해요 : 22
class Main {
  static Map<Integer, Node> nodeMap;
  static int order;

  public static void main(String[] args) throws Exception {
    nodeMap = new HashMap<>();
    StringBuilder sb = new StringBuilder();

    final int NODE_AMOUNT = Input.nextInt();

    for (int i = 0; i < NODE_AMOUNT; i++) {
      int nodeNumber = Input.nextInt();
      Node node = getOrCreateNode(nodeNumber);

      int connectedNodeNumber;
      while ((connectedNodeNumber = Input.nextInt()) != -1) {
        Node connectedNode = getOrCreateNode(connectedNodeNumber);
        node.connect(connectedNode);
      }
    }

    final int ROOT_NUMBER = Input.nextInt();
    Node root = getOrCreateNode(ROOT_NUMBER);

    order = 0;
    fillLeftRight(root);

    for (int i = 1; i <= NODE_AMOUNT; i++) {
      Node node = getOrCreateNode(i);
      sb.append(i).append(' ').append(node.left).append(' ').append(node.right).append('\n');
    }

    System.out.print(sb);
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

  static void fillLeftRight(Node root) {
    root.left = ++order;

    for (Node connectedNode : root.connectedNodes) {
      connectedNode.disconnect(root);
      fillLeftRight(connectedNode);
    }

    root.right = ++order;
  }
}

class Node {
  public int number;
  public List<Node> connectedNodes;
  public int left;
  public int right;

  public Node(int number) {
    this.number = number;
    connectedNodes = new ArrayList<>();
  }

  public void connect(Node node) {
    int number = node.number;

    if (connectedNodes.size() == 0 || number < connectedNodes.get(0).number) {
      connectedNodes.add(0, node);
      return;
    }

    for (int i = 0; i < connectedNodes.size() - 1; i++) {
      if (connectedNodes.get(i).number < number && number < connectedNodes.get(i + 1).number) {
        connectedNodes.add(i + 1, node);
        return;
      }
    }

    connectedNodes.add(node);
  }

  public void disconnect(Node node) {
    connectedNodes.remove(node);
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