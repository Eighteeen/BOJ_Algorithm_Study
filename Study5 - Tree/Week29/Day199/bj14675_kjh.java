import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// 단절점: https://ip99202.github.io/posts/%EB%8B%A8%EC%A0%88%EC%A0%90-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/
// 단절선: https://ip99202.github.io/posts/%EB%8B%A8%EC%A0%88%EC%84%A0-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/
// 위 링크의 코드를 아주 많이 참고했음
class Main {
  static Map<Integer, Node> nodeMap;
  static int discover;
  static Edges edges;

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    nodeMap = new HashMap<>();
    edges = new Edges();
    discover = 1;

    final int NODE_AMOUNT = Input.nextInt();
    for (int i = 1; i <= NODE_AMOUNT - 1; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      Node nodeA = getOrCreateNode(numA);
      Node nodeB = getOrCreateNode(numB);

      Edge edgeAB = edges.add(i, numA, numB);

      nodeA.connect(edgeAB);
      nodeB.connect(edgeAB);
    }

    Node root = nodeMap.get(1);
    markArticulationPoints(root, true);
    discover = 1;
    markBridges(root, null);

    final int QUERY_AMOUNT = Input.nextInt();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int queryType = Input.nextInt();
      int queryTarget = Input.nextInt();

      if (queryType == 1) {
        sb.append(nodeMap.get(queryTarget).isArticulationPoint ? "yes" : "no").append('\n');
      } else {
        sb.append(edges.getByNumber(queryTarget).isBridge ? "yes" : "no").append('\n');
      }
    }

    System.out.print(sb);
  }

  static int markBridges(Node root, Node parent) {
    root.discoverByLine = discover++;

    int minDiscoverNum = root.discoverByLine;
    for (Edge vertex : root.vertexes) {
      int connectedNum = vertex.getConnectedNum(root.number);
      Node connectedNode = nodeMap.get(connectedNum);

      if (connectedNode == parent) {
        continue;
      }

      if (connectedNode.discoverByLine > 0) {
        minDiscoverNum = Math.min(minDiscoverNum, connectedNode.discoverByLine);
        continue;
      }

      int connectedDiscoverNum = markBridges(connectedNode, root);
      minDiscoverNum = Math.min(minDiscoverNum, connectedDiscoverNum);

      if (root.discoverByLine < connectedDiscoverNum) {
        int nodeA = Math.min(root.discoverByLine, connectedNode.discoverByLine);
        int nodeB = Math.max(root.discoverByLine, connectedNode.discoverByLine);

        Edge edge = edges.getByConnectedNodes(nodeA, nodeB);
        if (edge != null) {
          edge.isBridge = true;
        }
      }
    }

    return minDiscoverNum;
  }

  static int markArticulationPoints(Node root, boolean isRoot) {
    root.discoverByPoint = discover++;

    int minDiscoverNum = root.discoverByPoint;
    for (Edge vertex : root.vertexes) {
      int connectedNum = vertex.getConnectedNum(root.number);
      Node connectedNode = nodeMap.get(connectedNum);

      if (connectedNode.discoverByPoint > 0) {
        minDiscoverNum = Math.min(minDiscoverNum, connectedNode.discoverByPoint);
        continue;
      }

      int connectedDiscoverNum = markArticulationPoints(connectedNode, false);
      minDiscoverNum = Math.min(minDiscoverNum, connectedNum);

      if (root.discoverByPoint <= connectedDiscoverNum && !isRoot) {
        root.isArticulationPoint = true;
      }
    }

    if (isRoot && root.vertexes.size() > 1) {
      root.isArticulationPoint = true;
    }
    return minDiscoverNum;
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
  public List<Edge> vertexes;
  public boolean isArticulationPoint;
  public int discoverByPoint;
  public int discoverByLine;

  public Node(int number) {
    this.number = number;
    vertexes = new ArrayList<>();
    isArticulationPoint = false;
  }

  public void connect(Edge vertex) {
    vertexes.add(vertex);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node == false) {
      return false;
    }
    return ((Node) obj).number == this.number;
  }
}

class Edges {
  static Map<Integer, Edge> numberToEdge;
  static Map<String, Edge> dataToEdge;

  public Edges() {
    numberToEdge = new HashMap<>();
    dataToEdge = new HashMap<>();
  }

  public Edge add(int number, int numA, int numB) {
    Edge edge = new Edge(numA, numB);

    numberToEdge.put(number, edge);
    dataToEdge.put(numA + "/" + numB, edge);

    return edge;
  }

  public Edge getByNumber(int number) {
    return numberToEdge.get(number);
  }

  public Edge getByConnectedNodes(int numA, int numB) {
    return dataToEdge.get(numA + "/" + numB);
  }
}

class Edge {
  public int numA;
  public int numB;
  public boolean isBridge;

  public Edge(int numA, int numB) {
    this.numA = numA;
    this.numB = numB;
    isBridge = false;
  }

  public int getConnectedNum(int num) {
    if (num == numA) {
      return numB;
    }
    return numA;
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