//// 문제풀이 실패 : 로직조차 떠올리지 못함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static Node[] nodes;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();
  
    nodes = new Node[NODE_AMOUNT];
    for (int i = 0; i < NODE_AMOUNT; i++) {
      int parentIdx = Input.nextInt();
      nodes[i] = new Node(i);

      if (parentIdx == -1) {
        continue;
      }
      nodes[parentIdx].addChild(nodes[i]);
    }

    System.out.print(getLeastTimeToCall(nodes[0]));
  }

  static int getLeastTimeToCall(Node root) {
    System.out.println(root.children);
    int childrenSize = root.children.size();
    if (childrenSize == 0) {
      return 1;
    }

    int childLeastTimeToCall = 500;
    for (Node child : root.children) {
      childLeastTimeToCall = Math.min(childLeastTimeToCall, getLeastTimeToCall(child));
    }

    return childLeastTimeToCall + (childrenSize - 1);
  }
}

class Node {
  public List<Node> children;
  public int number;

  public Node(int number) {
    this.number = number;
    children = new ArrayList<>();
  }

  public void addChild(Node child) {
    children.add(child);
  }

  @Override
  public String toString() {
    return "(" + number + ")";
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