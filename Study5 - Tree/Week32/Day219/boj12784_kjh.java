import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

//// 깔쌈
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      Input.nextInt();
      int edgeAmount = Input.nextInt();

      Tree tree = new Tree();
      for (int j = 0; j < edgeAmount; j++) {
        int nodeNumA = Input.nextInt();
        int nodeNumB = Input.nextInt();
        int requiredDynamites = Input.nextInt();

        tree.addInterconnectedNodes(nodeNumA, nodeNumB, requiredDynamites);
      }

      int leastDynamites = tree.getLeastDynamites();
      result.append(leastDynamites).append('\n');
    }

    System.out.print(result);
  }
}

class Tree {
  private Map<Integer, Node> nodeMap;

  public Tree() {
    nodeMap = new HashMap<>();
  }

  public void addInterconnectedNodes(int nodeNumA, int nodeNumB, int requiredDynamites) {
    Node nodeA = getOrAddNode(nodeNumA);
    Node nodeB = getOrAddNode(nodeNumB);

    nodeA.addEdge(new Edge(nodeB, requiredDynamites));
    nodeB.addEdge(new Edge(nodeA, requiredDynamites));
  }

  public int getLeastDynamites() {
    if (nodeMap.size() == 0) {
      return 0;
    }

    Node root = getNode(1);
    return getLeastDynamites(root);
  }

  private int getLeastDynamites(Node root) {
    List<Edge> havingEdges = root.getHavingEdges();
    int size = havingEdges.size();
    //// 특정하게 123456789 이라는 숫자를 지정해서 다른 뜻이 있나 생각하게 됐어요.
    //// min 계산을 위해서 반환하는 값이니 (특정한 의미가 없는 값) 어떤 특정한 값을 하드코딩으로 넣는 것 보단
    //// hsk 코드처럼 max 값을 지정해주는 게 혼돈이 없을 것 같습니다. (Integer.MAX_VALUE)
    if (size == 0) {
      return 123456789;
    }

    //// 굳이 array를 쓸 필요가 없어 보입니다.
    //// IntStream으로 sum을 지정하고 싶다면 array를 사용한다지만, 저장하는 loop 후에 다시 탐색하는 loop를 도는 것은 어색해보입니다.
    int[] childrenLeastDynamites = new int[size];
    for (int i = 0; i < size; i++) {
      childrenLeastDynamites[i] = havingEdges.get(i).getRequiredDynamites();
    }

    for (int i = 0; i < size; i++) {
      Edge havingEdge = havingEdges.get(i);
      Node connectedNode = havingEdge.getDestination();
      connectedNode.removeEdge(root);

      childrenLeastDynamites[i] = Math.min(childrenLeastDynamites[i], getLeastDynamites(connectedNode));
    }

    //// stream 패키지 좋네요,,
    return IntStream.of(childrenLeastDynamites).sum();
  }

  private Node getOrAddNode(int number) {
    if (isExists(number)) {
      return getNode(number);
    }
    return addNode(number);
  }

  private boolean isExists(int number) {
    return nodeMap.containsKey(number);
  }

  public Node getNode(int number) {
    return nodeMap.get(number);
  }

  private Node addNode(int number) {
    Node node = new Node(number);
    nodeMap.put(number, node);
    return node;
  }
}

class Edge {
  private Node destination;
  private int requiredDynamites;

  public Edge(Node destination, int requiredDynamites) {
    this.destination = destination;
    this.requiredDynamites = requiredDynamites;
  }

  public Node getDestination() {
    return destination;
  }

  public int getRequiredDynamites() {
    return requiredDynamites;
  }
}

class Node {
  private int number;
  private List<Edge> havingEdges;

  public Node(int number) {
    this.number = number;
    havingEdges = new ArrayList<>();
  }

  public void addEdge(Edge edge) {
    havingEdges.add(edge);
  }

  public void removeEdge(Node targetDestination) {
    Edge removeTarget = null;
    for (Edge havingEdge : havingEdges) {
      if (havingEdge.getDestination() == targetDestination) {
        removeTarget = havingEdge;
      }
    }

    if (removeTarget == null) {
      return;
    }
    havingEdges.remove(removeTarget);
  }

  public int getNumber() {
    return number;
  }

  public List<Edge> getHavingEdges() {
    return havingEdges;
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