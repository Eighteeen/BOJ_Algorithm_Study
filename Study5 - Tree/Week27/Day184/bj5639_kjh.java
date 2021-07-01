import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//// 깔끔해요
class Main {
  public static void main(String[] args) throws Exception {
    List<Integer> nodesPreorder = new ArrayList<>();

    String node;
    while ( (node = Input.nextLine()) != null ) {
      nodesPreorder.add(Integer.parseInt(node));
    }

    Node root = makeTree(nodesPreorder, 0, 1000000);
    System.out.print(traversePostorder(root));
  }

  //// 저는 root보다 큰 부분을 찾아서 그 기준으로 자식을 나눴는데 이렇게 순차적으로 체크할 수도 있군요. 다양한 방법 알아갑니다.
  static Node makeTree(List<Integer> preorder, int biggerThan, int smallerThan) {
    if (preorder.size() == 0) return null;
    
    Node root = new Node(preorder.get(0));
    if (root.data < biggerThan || root.data > smallerThan) {
      return null;
    }
    
    preorder.remove(0);
    root.left = makeTree(preorder, biggerThan, root.data);
    root.right = makeTree(preorder, root.data, smallerThan);

    return root;
  }

  static StringBuilder traversePostorder(Node root) {
    StringBuilder sb = new StringBuilder();

    if (root.left != null) {
      sb.append(traversePostorder(root.left));
    }
    if (root.right != null) {
      sb.append(traversePostorder(root.right));
    }
    sb.append(root.data).append('\n');

    return sb;
  }
}

class Node {
  public int data;
  public Node parent;
  public Node left;
  public Node right;

  public Node(int data) {
    this.data = data;
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