import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    Map<Integer, Node> nodeMap = new HashMap<>();
    boolean isTree = true;
    int caseNum = 1;

    while (true) {
      int parentNumber = Input.nextInt();
      int childNumber = Input.nextInt();
      if (parentNumber == -1 && childNumber == -1) {
        break;
      }

      if (parentNumber == 0 && childNumber == 0) {
        Node root = getRoot(nodeMap);
        if (root == null || countNodesThatRootCanVisit(root) != nodeMap.size() - 1) {
          isTree = false;
        }
        if (nodeMap.size() == 0) {
          isTree = true;
        }

        sb.append("Case ").append(caseNum).append(" is ").append(!isTree ? "not " : "").append("a tree.").append('\n');

        isTree = true;
        caseNum++;
        nodeMap = new HashMap<>();
        continue;
      }

      Node parentNode = getOrCreateNode(nodeMap, parentNumber);
      Node childNode = getOrCreateNode(nodeMap, childNumber);

      parentNode.addChild(childNode);
      if (childNode.parent == null) {
        childNode.parent = parentNode;
      } else {
        isTree = false;
      }
    }

    System.out.print(sb);
  }

  static Node getOrCreateNode(Map<Integer, Node> nodeMap, int data) {
    Node node;
    if (nodeMap.containsKey(data)) {
      node = nodeMap.get(data);
    } else {
      node = new Node(data);
      nodeMap.put(data, node);
    }

    return node;
  }

  static Node getRoot(Map<Integer, Node> nodeMap) {
    for (Integer nodeNum : nodeMap.keySet()) {
      Node node = nodeMap.get(nodeNum);
      if (node.parent == null) {
        return node;
      }
    }
    return null;
  }

  static int countNodesThatRootCanVisit(Node root) {
    int countChildren = 0;
    for (Node child : root.children) {
      countChildren += countNodesThatRootCanVisit(child);
    }
    return root.children.size() + countChildren;
  }
}

class Node {
  public int data;
  public Node parent;
  public List<Node> children;

  public Node(int data) {
    this.data = data;
    parent = null;
    children = new ArrayList<>();
  }

  public void addChild(Node child) {
    children.add(child);
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