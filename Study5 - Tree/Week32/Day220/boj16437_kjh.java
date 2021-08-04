import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    Tree tree = new Tree();
    for (int nodeNum = 2; nodeNum <= NODE_AMOUNT; nodeNum++) {
      char species = Input.nextChar();
      int population = Input.nextInt();
      int destinationNodeNum = Input.nextInt();

      Node nodeFrom = tree.getOrAddNode(nodeNum);
      Node nodeTo = tree.getOrAddNode(destinationNodeNum);

      nodeFrom.isWolf = species == 'W';
      nodeFrom.population = population;
      nodeFrom.connect(nodeTo);

      nodeTo.connect(nodeFrom);
    }

    System.out.print(tree.getTheMostSheep());
  }
}

//// 깔끔해요
class Tree {
  private Map<Integer, Node> nodeMap;

  public Tree() {
    nodeMap = new HashMap<>();
  }
  
  public long getTheMostSheep() {
    Node root = getNode(1);
    return getTheMostSheep(root);
  }

  private long getTheMostSheep(Node root) {
    long leftSheep = root.population;
    //// 음수 곱하기! 생각지 못 한 방법이네요. 다른 방법도 알아갑니다.
    leftSheep *= root.isWolf ? -1 : 1;

    for (Node connectedNode : root.connectedNodes) {
      connectedNode.disconnect(root);
      leftSheep += Math.max(0, getTheMostSheep(connectedNode));
    }

    return Math.max(0, leftSheep);
  }

  public Node getOrAddNode(int number) {
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

class Node {
  public int number;
  public List<Node> connectedNodes;
  public boolean isWolf;
  public int population;

  public Node(int number) {
    this.number = number;
    this.connectedNodes = new ArrayList<>();
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