import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//// ㄲㄲ
class Main {
  static int pillarLength;
  static Node gigaNode;
  
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();
    final int ROOT_NUMBER = Input.nextInt();
    
    Node[] nodes = Stream.generate(Node::new).limit(NODE_AMOUNT + 1).toArray(Node[]::new);
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();
      int distance = Input.nextInt();

      nodes[numA].number = numA;
      nodes[numB].number = numB;
      
      nodes[numA].addEdge(new Edge(nodes[numB], distance));
      nodes[numB].addEdge(new Edge(nodes[numA], distance));
    }

    Node root = nodes[ROOT_NUMBER];
    Node gigaNode = getGigaNodeAndUpdatePillarLength(root);

    int longestBranchLength = getMaxDistance(gigaNode);

    System.out.printf("%d %d", pillarLength, longestBranchLength);
  }
  
  static int getMaxDistance(Node startNode) {
    if (startNode == null) return 0;

    int maxDistance = 0;
    for (Edge edge : startNode.edges) {
      Node connectedNode = edge.connectedNode;
      connectedNode.removeEdge(startNode.number);
      maxDistance = Math.max(maxDistance, edge.distance + getMaxDistance(connectedNode));
    }
    
    return maxDistance;
  }

  static Node getGigaNodeAndUpdatePillarLength(Node root) {
    if (root.edges.size() == 0) {
      return null;
    }
    if (root.edges.size() > 1) {
      return root;
    }

    Edge edge = root.edges.get(0);
    pillarLength += edge.distance;
    edge.connectedNode.removeEdge(root.number);

    return getGigaNodeAndUpdatePillarLength(edge.connectedNode);
  }
}

class Node {
  public int number;
  public List<Edge> edges;

  public Node() {
    edges = new ArrayList<>();
  }

  public void addEdge(Edge edge) {
    edges.add(edge);
  }

  public void removeEdge(int targetData) {
    Edge targetEdge = null;
    for (int i = 0; i < edges.size(); i++) {
      Edge edge = edges.get(i);
      if (edge.connectedNode.number == targetData) {
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