import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Integer, Node> nodeMap;
  
  static Node optimalNode;
  static int maxDepth = 0;
  static int minHours = 50000000;

  public static void main(String[] args) throws Exception {
    nodeMap = new HashMap<>();
    
    final int NODE_AMOUNT = Input.nextInt();
    final int MAX_HOUR_A_DAY = Input.nextInt();

    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int nodeNumA = Input.nextInt();
      int nodeNumB = Input.nextInt();
      int hours = Input.nextInt();
      
      Node nodeA = getOrCreateNode(nodeNumA);
      Node nodeB = getOrCreateNode(nodeNumB);
      nodeA.connect(nodeB, hours);
      nodeB.connect(nodeA, hours);
    }

    Node root = getOrCreateNode(1);
    updateOptimalNodeInfos(root, 0, 0);
    updateOptimalNodeInfos(optimalNode, 0, 0);

    int minDays = minHours / MAX_HOUR_A_DAY;
    minDays += (minHours % MAX_HOUR_A_DAY > 0) ? 1 : 0;

    System.out.print(minDays);
  }
  

  static void updateOptimalNodeInfos(Node standard, int curDepth, int curHours) {
    if (standard.isVisited()) return;

    boolean isFoundOptimalNode = (curDepth > maxDepth || (curDepth == maxDepth && curHours < minHours));
    if (isFoundOptimalNode) {
      optimalNode = standard;
      maxDepth = curDepth;
      minHours = curHours;
    }

    for (Edge connectedEdge : standard.connectedEdges) {
      Node connectedNode = connectedEdge.to;
      int hours = connectedEdge.hours;

      standard.toggleVisit();
      updateOptimalNodeInfos(connectedNode, curDepth + 1, curHours + hours);
      standard.toggleVisit();
    }
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

class Edge {
  public Node from;
  public Node to;
  public int hours;

  public Edge(Node from, Node to, int hours) {
    this.from = from;
    this.to = to;
    this.hours = hours;
  }
}

class Node {
  public int number;
  public List<Edge> connectedEdges;
  private boolean visited;

  public Node(int number) {
    this.number = number;
    connectedEdges = new ArrayList<>();
    visited = false;
  }

  public void connect(Node node, int hours) {
    connectedEdges.add(new Edge(this, node, hours));
  }

  public boolean isVisited() {
    return visited;
  }

  public void toggleVisit() {
    visited = !visited;
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