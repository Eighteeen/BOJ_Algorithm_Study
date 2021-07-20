import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//// 무난 깔끔 : 22
class Main {
  static Map<Integer, Node> nodeMap;
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    nodeMap = new HashMap<>();

    final int NODE_AMOUNT = Input.nextInt();
    final int ROOT_NUMBER = Input.nextInt();
    final int QUERY_AMOUNT = Input.nextInt();

    for (int i = 1; i <= NODE_AMOUNT - 1; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      Node nodeA = getOrCreateNode(numA);
      Node nodeB = getOrCreateNode(numB);

      nodeA.connect(nodeB);
      nodeB.connect(nodeA);
    }

    Node root = getOrCreateNode(ROOT_NUMBER);
    calcSubtreeSizeInEachNode(root);

    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int queryTarget = Input.nextInt();
      Node targetNode = nodeMap.get(queryTarget);

      sb.append(targetNode.subtreeSize).append('\n');
    }

    System.out.print(sb);
  }

  static int calcSubtreeSizeInEachNode(Node root) {
    int subtreeSize = 1;
    for (Node child : root.connected) {
      child.disconnect(root);
      subtreeSize += calcSubtreeSizeInEachNode(child);
    }

    root.subtreeSize = subtreeSize;
    return subtreeSize;
  }

  static Node getOrCreateNode(int data) {
    Node node;
    if (nodeMap.containsKey(data)) {
      node = nodeMap.get(data);
    } else {
      node = new Node(data);
      nodeMap.put(data, node);
    }
    return node;
  }
}

class Node {
  public int number;
  public List<Node> connected;
  public int subtreeSize;

  public Node(int number) {
    this.number = number;
    connected = new ArrayList<>();
  }

  public void connect(Node node) {
    connected.add(node);
  }

  public void disconnect(Node node) {
    connected.remove(node);
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