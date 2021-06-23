import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Character, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    nodeMap = new HashMap<>();
    for (int i = 0; i < NODE_AMOUNT; i++) {
      Character data = Input.nextChar();
      Character leftChild = Input.nextChar();
      Character rightChild = Input.nextChar();

      nodeMap.put(data, new Node(data, leftChild, rightChild));
    }

    Node root = nodeMap.get('A');

    StringBuilder sb = new StringBuilder();
    sb.append(getPreorder(root)).append('\n');
    sb.append(getInorder(root)).append('\n');
    sb.append(getPostorder(root)).append('\n');
    
    System.out.print(sb);
  }
  
  static StringBuilder getPreorder(Node root) {
    StringBuilder sb = new StringBuilder();

    sb.append(root.data);
    if (root.leftChild != '.') {
      sb.append(getPreorder(nodeMap.get(root.leftChild)));
    }
    if (root.rightChild != '.') {
      sb.append(getPreorder(nodeMap.get(root.rightChild)));
    }

    return sb;
  }
  
  static StringBuilder getInorder(Node root) {
    StringBuilder sb = new StringBuilder();

    if (root.leftChild != '.') {
      sb.append(getInorder(nodeMap.get(root.leftChild)));
    }
    sb.append(root.data);
    if (root.rightChild != '.') {
      sb.append(getInorder(nodeMap.get(root.rightChild)));
    }
    
    return sb;
  }
  
  static StringBuilder getPostorder(Node root) {
    StringBuilder sb = new StringBuilder();

    if (root.leftChild != '.') {
      sb.append(getPostorder(nodeMap.get(root.leftChild)));
    }
    if (root.rightChild != '.') {
      sb.append(getPostorder(nodeMap.get(root.rightChild)));
    }
    sb.append(root.data);
    
    return sb;
  }
  
}

class Node {
  Character data;
  Character leftChild;
  Character rightChild;

  public Node(Character data, Character leftChild, Character rightChild) {
    this.data = data;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
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