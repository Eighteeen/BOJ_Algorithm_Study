import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

class Main {
  static int maxRadius;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    Tree tree = new Tree();
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int parentNum = Input.nextInt();
      int childNum = Input.nextInt();
      int distance = Input.nextInt();

      tree.createAndLinkNodes(parentNum, childNum, distance);
    }

    Node root = tree.getNode(1);
    if (root != null) {
      getMaxDistance(root);
    }

    System.out.print(maxRadius);
  }

  //// 최소 방문을 sort를 통해서 구현하셨네요. 다른 방법 배워갑니다.
  static int getMaxDistance(Node root) {
    if (root.childrenEdges.size() == 0) {
      return 0;
    }

    List<Integer> childrenMaxDistances = new ArrayList<>();
    for (Edge childEdge : root.childrenEdges) {
      Node child = childEdge.child;
      int distance = childEdge.distance;
      childrenMaxDistances.add(distance + getMaxDistance(child));
    }

    childrenMaxDistances.sort(Comparator.reverseOrder());

    // 자식의 maxDistance 중 가장 큰 두 가지를 합한 것이
    // 현 노드를 중간지점으로 거치는 것 중 최대 지름(maxRadius)
    int maxRadius = childrenMaxDistances.get(0);
    maxRadius += childrenMaxDistances.size() >= 2 ? childrenMaxDistances.get(1) : 0;
    updateMaxRadius(maxRadius);

    return childrenMaxDistances.get(0);
  }

  static void updateMaxRadius(int maxRadius) {
    Main.maxRadius = Math.max(Main.maxRadius, maxRadius);
  }
}

class Tree {
  private Map<Integer, Node> nodeMap;

  public Tree() {
    nodeMap = new HashMap<>();
  }

  public void createAndLinkNodes(int parentNum, int childNum, int distance) {
    Node parent = getOrCreateNode(parentNum);
    Node child = getOrCreateNode(childNum);

    parent.addChild(child, distance);
    child.setParent(parent, distance);
  }

  private Node getOrCreateNode(int number) {
    Node node = getNode(number);
    if (node == null) {
      node = createNode(number);
    }
    return node;
  }

  public Node getNode(int number) {
    return nodeMap.get(number);
  }

  private Node createNode(int number) {
    Node node = new Node(number);
    nodeMap.put(number, node);
    return node;
  }
}

class Node {
  private int number;
  private Edge parentEdge;
  public List<Edge> childrenEdges;

  public Node(int number) {
    this.number = number;
    childrenEdges = new ArrayList<>();
  }

  public void setParent(Node parent, int distance) {
    this.parentEdge = new Edge(parent, this, distance);
  }

  public void addChild(Node child, int distance) {
    childrenEdges.add(new Edge(this, child, distance));
  }
}

class Edge {
  private Node parent;
  public Node child;
  public int distance;

  public Edge(Node parent, Node child, int distance) {
    this.parent = parent;
    this.child = child;
    this.distance = distance;
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