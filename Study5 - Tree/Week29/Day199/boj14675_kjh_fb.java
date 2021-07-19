
// 문제 실패 - 직접 다시 품
// '사이클이 없는' 트리의 단절점 단절선을 구하는 문제인데
// 괜히 그래프에 대한 단절점 단절선 이론을 참고해서 삽질했음
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//// 깔끔!
class Main {
  static Map<Integer, Node> nodeMap;
  static int discover;

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    nodeMap = new HashMap<>();

    final int NODE_AMOUNT = Input.nextInt();
    for (int i = 1; i <= NODE_AMOUNT - 1; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();

      Node nodeA = getOrCreateNode(numA);
      Node nodeB = getOrCreateNode(numB);

      nodeA.connect(nodeB);
      nodeB.connect(nodeA);
    }

    final int QUERY_AMOUNT = Input.nextInt();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int queryType = Input.nextInt();
      int queryTarget = Input.nextInt();

      if (queryType == 1) {
        sb.append(nodeMap.get(queryTarget).connected.size() > 1 ? "yes" : "no").append('\n');
      } else {
        sb.append("yes").append('\n');
      }
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
}

class Node {
  public int number;
  public List<Node> connected;

  public Node(int number) {
    this.number = number;
    connected = new ArrayList<>();
  }

  public void connect(Node node) {
    connected.add(node);
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