import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    nodeMap = new HashMap<>();

    for (int i = 0; i < N - 1; i++) {
      Node node1 = getOrCreateNode(Input.nextInt());
      Node node2 = getOrCreateNode(Input.nextInt());

      node1.connect(node2);
      node2.connect(node1);
    }

    Node root = nodeMap.get(1);
    root.depth = 0;
    
    int leafDepths = getLeafDepths(root);
    System.out.print((leafDepths % 2) == 1 ? "Yes" : "No");
  }

  static int getLeafDepths(Node root) {
    if (root.isLeaf()) return root.depth;

    int leafDepths = 0;
    for (Node child : root.connected) {
      child.disconnect(root);
      child.depth = root.depth + 1;

      leafDepths += getLeafDepths(child);
    }
    
    return leafDepths;
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
  public int depth;

  public Node(int data) {
    this.data = data;
    connected = new ArrayList<>();
  }

  public void connect(Node node) {
    connected.add(node);
  }

  public void disconnect(Node node) {
    connected.remove(node);
  }

  public boolean isLeaf() {
    return connected.size() == 0;
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