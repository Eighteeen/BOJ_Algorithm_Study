import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

////깔끔해요
class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    nodeMap = new HashMap<>();

    final int visits = Input.nextInt();

    Node root = getOrCreateNode(Input.nextInt());
    for (int i = 1; i < visits; i++) {
      int visit = Input.nextInt();

      Node parent = root.parent;
      if (parent == null || parent.number != visit) {
        Node child = getOrCreateNode(visit);
        child.parent = root;
        root = child;
        continue;
      }
      root = root.parent;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(nodeMap.size()).append('\n');

    //// root의 parent를 -1로 설정하면 조건문을 실행할 필요 없어 그 방법도 고려해보면 좋을 것 같습니다.
    for (int i = 0; i < nodeMap.size(); i++) {
      Node node = nodeMap.get(i);
      Node parent = node.parent;

      sb.append(parent == null ? -1 : parent.number).append(' ');
    }

    System.out.print(sb);
  }

  static Node getOrCreateNode(int number) {
    Node node;
    if (nodeMap.containsKey(number)) {
      node = nodeMap.get(number);
    } else {
      node = new Node(number);
      nodeMap.put(number, node);
    }
    return node;
  }
}

//// 활용 good
class Node {
  public int number;
  public Node parent;

  public Node(int number) {
    this.number = number;
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