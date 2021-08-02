import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//// 깔끔합니다.
class Main {
  static Map<Integer, Node> nodeMap;
  static int[] parentInfos;
  static int[] depthInfos;

  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();
    nodeMap = new HashMap<>();

    final int NODE_AMOUNT = Input.nextInt();
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int nodeNumA = Input.nextInt();
      int nodeNumB = Input.nextInt();

      Node nodeA = getOrCreateNode(nodeNumA);
      Node nodeB = getOrCreateNode(nodeNumB);

      nodeA.connect(nodeB);
      nodeB.connect(nodeA);
    }

    Node root = nodeMap.get(1);

    parentInfos = new int[NODE_AMOUNT + 1];
    depthInfos = new int[NODE_AMOUNT + 1];
    fillDepthParentInfos(root, 0);

    final int TARGET_AMOUNT = Input.nextInt();
    for (int i = 0; i < TARGET_AMOUNT; i++) {
      int targetNodeNumA = Input.nextInt();
      int targetNodeNumB = Input.nextInt();

      result.append(getMostRecentCommonAncestor(targetNodeNumA, targetNodeNumB)).append('\n');
    }

    System.out.print(result);
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

  static void fillDepthParentInfos(Node root, int depth) {
    depthInfos[root.number] = depth;

    for (Node connectedNode : root.connectedNodes) {
      connectedNode.disconnect(root);
      parentInfos[connectedNode.number] = root.number;
      fillDepthParentInfos(connectedNode, depth + 1);
    }
  }

  /// LCA(Lowest Common Ancestor) 라고 널리 사용되는 용어가 있는데 굳이 MostRecent라는 용어를 쓸 필요는
  /// 없어보입니다. (흠칫 3초 혼돈)
  /// => 프로그래밍 쪽에선 거의 그렇게 부르니 그렇긴하네요
  /// => 반영했슴다! 피드백 고마워요
  static int getMostRecentCommonAncestor(int targetNodeA, int targetNodeB) {
    int depthA = depthInfos[targetNodeA];
    int depthB = depthInfos[targetNodeB];

    while (depthA > depthB) {
      depthA--;
      targetNodeA = parentInfos[targetNodeA];
    }
    while (depthB > depthA) {
      depthB--;
      targetNodeB = parentInfos[targetNodeB];
    }

    while (targetNodeA != targetNodeB) {
      targetNodeA = parentInfos[targetNodeA];
      targetNodeB = parentInfos[targetNodeB];
    }

    return targetNodeA;
  }
}

class Node {
  public int number;
  public List<Node> connectedNodes;

  public Node(int number) {
    this.number = number;
    connectedNodes = new ArrayList<>();
  }

  public void connect(Node node) {
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