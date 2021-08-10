import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//// 매우 깔끔해요
class Main {
  static StringBuilder antDent;

  public static void main(String[] args) throws Exception {
    final int ANT_AMOUNT = Input.nextInt();

    Node root = new Node("");

    for (int i = 0; i < ANT_AMOUNT; i++) {
      int preyInfoAmount = Input.nextInt();

      Node current = root;
      for (int j = 0; j < preyInfoAmount; j++) {
        String preyData = Input.next();
        current = current.getOrAddChild(preyData);
      }
    }

    antDent = new StringBuilder();
    visualizeAntDent(root, -1);

    System.out.print(antDent);
  }

  static void visualizeAntDent(Node root, int depth) {
    if (depth >= 0) {
      antDent.append("--".repeat(depth));
      antDent.append(root.data).append('\n');
    }

    for (Node child : root.children) {
      visualizeAntDent(child, depth + 1);
    }
  }
}

class Node {
  public String data;
  public List<Node> children;

  public Node(String data) {
    this.data = data;
    children = new ArrayList<>();
  }

  public Node getOrAddChild(String data) {
    int childIndex = getChildIndex(data);

    if (childIndex == -1) {
      Node child = new Node(data);
      addChild(child);
      return child;
    }

    return children.get(childIndex);
  }

  private int getChildIndex(String targetData) {
    for (int i = 0; i < children.size(); i++) {
      Node child = children.get(i);
      if (child.data.equals(targetData)) {
        return i;
      }
    }
    return -1;
  }

  private void addChild(Node childToAdd) {
    for (int i = 0; i < children.size(); i++) {
      Node child = children.get(i);
      if (child.data.compareTo(childToAdd.data) > 0) {
        children.add(i, childToAdd);
        return;
      }
    }

    children.add(childToAdd);
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