import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    final int NODES = Input.nextInt();

    nodeMap = new HashMap<>();
    for (int i = 0; i < NODES - 1; i++) {
      int data1 = Input.nextInt();
      int data2 = Input.nextInt();

      Node node1 = getOrCreateNode(data1);
      Node node2 = getOrCreateNode(data2);

      node1.connect(node2);
      node2.connect(node1);
    }

    Node root = nodeMap.get(1);
    defineParents(root);

    StringBuilder sb = new StringBuilder();
    for (int i = 2; i <= NODES; i++) {
      Node node = nodeMap.get(i);
      sb.append(node.parent.data).append('\n');
    }

    System.out.print(sb);
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

  static void defineParents(Node root) {
    for (Node child : root.connected) {
      child.parent = root;
      child.disconnect(root);
      defineParents(child);
    }
  }
}

class Node {
  public int data;
  public List<Node> connected;
  public Node parent;

  public Node(int data) {
    this.data = data;
    connected = new ArrayList<>();
    parent = null;
  }

  public void connect(Node node) {
    if (connected.contains(node)) return;
    connected.add(node);
  }

  public void disconnect(Node parent) {
    connected.remove(parent);
  }

  @Override
  public String toString() {
    return String.valueOf(data);
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