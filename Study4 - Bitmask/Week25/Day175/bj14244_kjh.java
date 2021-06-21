import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODES = Input.nextInt();
    final int LEAVES = Input.nextInt();

    int nodeNum = 0;
    //// Node 객체를 사용한게 인상깊네요
    Node root = new Node(nodeNum++);
    Node leaf = root;
    for (int i = 0; i < NODES - LEAVES; i++) {
      Node node = new Node(nodeNum++);
      leaf.addChild(node);

      leaf = node;
    }

    for (int i = 0; i < LEAVES - 1; i++) {
      leaf.addChild(new Node(nodeNum++));
    }

    System.out.print(getTreeInfo(root));
  }

  static StringBuilder getTreeInfo(Node root) {
    StringBuilder sb = new StringBuilder();

    for (Node child : root.children) {
      sb.append(root.data).append(' ').append(child.data)
        .append('\n');
      sb.append(getTreeInfo(child));
    }

    return sb;
  }
}

class Node {
  public int data;
  public List<Node> children;

  public Node() {
    children = new ArrayList<>();
  }

  public Node(int data) {
    this.data = data;
    children = new ArrayList<>();
  }

  public void addChild(Node child) {
    children.add(child);
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