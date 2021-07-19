import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();
    nodeMap = new HashMap<>();

    Node root = null;
    for (int nodeNum = 1; nodeNum <= NODE_AMOUNT; nodeNum++) {
      Node node = getOrCreateNode(nodeNum);

      int parentNum = Input.nextInt();
      if (parentNum == -1) {
        root = node;
        continue;
      }

      Node parent = getOrCreateNode(parentNum);
      parent.addChild(node);
    }

    fillHeightsInEachNode(root, 0);

    StringBuilder sb = new StringBuilder();
    for (int nodeNum = 1; nodeNum <= NODE_AMOUNT; nodeNum++) {
      Node node = getOrCreateNode(nodeNum);
      sb.append(node.height).append('\n');
    }

    System.out.print(sb);
  }

  //// 정말 한눈에 이해가 됐어요 : 22 깔끔!!
  static void fillHeightsInEachNode(Node root, int height) {
    if (root == null)
      return;

    root.height = height;
    fillHeightsInEachNode(root.leftChild, height + 1);
    fillHeightsInEachNode(root.rightChild, height + 1);
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
  public Node leftChild;
  public Node rightChild;
  public int height;

  public Node(int number) {
    this.number = number;
  }

  public void addChild(Node child) {
    if (leftChild == null) {
      leftChild = child;
    } else {
      rightChild = child;
    }
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