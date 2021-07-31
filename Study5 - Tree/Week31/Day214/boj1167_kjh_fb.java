import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

//// 깔끔해요 : 22
class Main {
  static Map<Integer, Node> nodeMap;
  static int maxDiameter;

  public static void main(String[] args) throws Exception {
    nodeMap = new HashMap<>();

    final int NODE_AMOUNT = Input.nextInt();
    for (int i = 0; i < NODE_AMOUNT; i++) {
      int nodeNum = Input.nextInt();
      Node node = getOrCreateNode(nodeNum);

      while (true) {
        int connectedNodeNum = Input.nextInt();
        if (connectedNodeNum == -1) {
          break;
        }
        Node connectedNode = getOrCreateNode(connectedNodeNum);
        int distance = Input.nextInt();

        node.addEdge(connectedNode, distance);
      }
    }

    Node root = getOrCreateNode(1);
    //// get or create를 하는데 왜 null 체크를 하죠..?
    //// => 어... 그러게요? 수정했슴다!
    getMaxDistance(root);

    System.out.print(maxDiameter);
  }

  static int getMaxDistance(Node root) {
    List<Integer> childrenMaxDistances = new ArrayList<>();
    for (Edge edge : root.edges) {
      Node connectedNode = edge.connectedNode;
      int distance = edge.distance;
      if (connectedNode.isVisited(root.number)) {
        continue;
      }

      root.markVisited(connectedNode.number);
      childrenMaxDistances.add(distance + getMaxDistance(connectedNode));
    }

    if (childrenMaxDistances.size() == 0) {
      return 0;
    }
    childrenMaxDistances.sort(Comparator.reverseOrder());

    int maxDiameter = childrenMaxDistances.get(0);
    maxDiameter += childrenMaxDistances.size() >= 2 ? childrenMaxDistances.get(1) : 0;
    updateMaxDiameter(maxDiameter);

    return childrenMaxDistances.get(0);
  }

  static void updateMaxDiameter(int maxDiameter) {
    Main.maxDiameter = Math.max(Main.maxDiameter, maxDiameter);
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
  public List<Edge> edges;
  public List<Integer> visitedNodes;

  public Node(int number) {
    edges = new ArrayList<>();
    visitedNodes = new ArrayList<>();
  }

  public void addEdge(Node connectedNode, int distance) {
    edges.add(new Edge(this, connectedNode, distance));
  }

  public void markVisited(Integer visitedNodeNum) {
    visitedNodes.add(visitedNodeNum);
  }

  public boolean isVisited(Integer visitedNodeNum) {
    return visitedNodes.contains(visitedNodeNum);
  }
}

class Edge {
  public Node node;
  public Node connectedNode;
  public int distance;

  public Edge(Node node, Node connectedNode, int distance) {
    this.node = node;
    this.connectedNode = connectedNode;
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