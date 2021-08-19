import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < T; i++) {
      int nodes = Input.nextInt();

      List<Integer> preorder = new ArrayList<>();
      for (int j = 0; j < nodes; j++) {
        preorder.add(Input.nextInt());
      }

      List<Integer> inorder = new ArrayList<>();
      for (int j = 0; j < nodes; j++) {
        inorder.add(Input.nextInt());
      }

      Node root = makeTree(preorder, inorder);
      List<Integer> postorder = traversePostorder(root);

      for (int j = 0; j < nodes; j++) {
        sb.append(postorder.get(j)).append(' ');
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  static Node makeTree(List<Integer> preorder, List<Integer> inorder) {
    if (inorder.size() == 0) return null;

    Node root = new Node();
    root.data = preorder.remove(0);

    int rootIdx = inorder.indexOf(root.data);

    root.left = makeTree(preorder, inorder.subList(0, rootIdx));
    root.right = makeTree(preorder, inorder.subList(rootIdx + 1, inorder.size()));

    return root;
  }

  static List<Integer> traversePostorder(Node root) {
    if (root == null) return new ArrayList<Integer>();

    List<Integer> postorder = new ArrayList<>();
    
    postorder.addAll(traversePostorder(root.left));
    postorder.addAll(traversePostorder(root.right));
    postorder.add(root.data);

    return postorder;
  }
}

class Node {
  public Integer data;
  public Node left;
  public Node right;
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