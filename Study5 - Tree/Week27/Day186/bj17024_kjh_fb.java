import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//// 무난해요 : 22
class Main {
  static Map<Integer, Node> nodeMap;
  //// grass는 어디에 쓰이나!
  //// => 안 쓰인다!
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    nodeMap = new HashMap<>();

    for (int i = 0; i < N - 1; i++) {
      Node node1 = getOrCreateNode(Input.nextInt());
      Node node2 = getOrCreateNode(Input.nextInt());

      node1.connect(node2);
      node2.connect(node1);
    }

    System.out.print(getMaxConnection(nodeMap.get(1)) + 1);
  }

  static int getMaxConnection(Node root) {
    int maxConnection = root.connected.size();
    maxConnection += root.parent == null ? 0 : 1;

    for (Node child : root.connected) {
      child.setParent(root);
      maxConnection = Math.max(maxConnection, getMaxConnection(child));
    }

    return maxConnection;
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
  public int data;
  public List<Node> connected;
  public Node parent;

  public Node(int data) {
    this.data = data;
    connected = new ArrayList<>();
  }

  public void connect(Node node) {
    connected.add(node);
  }

  public void setParent(Node node) {
    connected.remove(node);
    parent = node;
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