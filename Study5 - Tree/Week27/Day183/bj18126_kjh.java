import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int ROOMS = Input.nextInt();
    if (ROOMS == 1) {
      System.out.print(0);
      return;
    }

    Map<Integer, Node> nodeMap = new HashMap<>();

    for (int i = 0; i < ROOMS - 1; i++) {
      Node nodeA = getOrCreateNode(nodeMap, Input.nextInt());
      Node nodeB = getOrCreateNode(nodeMap, Input.nextInt());
      int distance = Input.nextInt();

      Edge toB = new Edge(nodeB, distance);
      nodeA.addEdge(toB);

      Edge toA = new Edge(nodeA, distance);
      nodeB.addEdge(toA);
    }

    Node root = nodeMap.get(1);
    removeParentInConnected(root);

    System.out.print(getMaxDistance(root)); 
  }

  static void removeParentInConnected(Node root) {
    for (Edge edge : root.edges) {
      Node child = edge.connectedNode;
      child.removeEdge(root.data);
      removeParentInConnected(child);
    }
  }

  static long getMaxDistance(Node root) {
    long maxDistance = 0;

    for (Edge edge : root.edges) {
      long distance = edge.distance + getMaxDistance(edge.connectedNode);
      maxDistance = Math.max(maxDistance, distance);
    }

    return maxDistance;
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
}

class Node {
  public int data;
  public List<Edge> edges;

  public Node(int data) {
    this.data = data;
    edges = new ArrayList<>();
  }

  public void addEdge(Edge edge) {
    edges.add(edge);
  }

  public void removeEdge(int targetData) {
    Edge targetEdge = null;
    for (int i = 0; i < edges.size(); i++) {
      Edge edge = edges.get(i);
      if (edge.connectedNode.data == targetData) {
        targetEdge = edge;
        break;
      }
    }
    if (targetEdge == null) return;
    edges.remove(targetEdge);
  }
}

class Edge {
  public Node connectedNode;
  public int distance;

  public Edge(Node connectedNode, int distance) {
    this.connectedNode = connectedNode;
    this.distance = distance;
  }

  @Override
  public String toString() {
    return connectedNode.data + "(" + distance + ")";
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
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
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}