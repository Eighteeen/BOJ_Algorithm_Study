// 문제풀이 실패 : 모범답안
// 수경씨가 설명해준 로직을 바탕으로 코딩해서 풀었음
// 로직 핵심 아이디어: 전파 시간이 오래 걸리는 사원에게 먼저 전파하는 것이 최적의 방법

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

    Node root = nodes[0];
    System.out.print(getLeastTimeToCall(root));
  }

  static int getLeastTimeToCall(Node root) {
    int childrenSize = root.children.size();
    if (childrenSize == 0) {
      return 0;
    }

    List<Integer> childrenLeastTime = new ArrayList<>();
    for (int i = 0; i < childrenSize; i++) {
      Node child = root.children.get(i);
      childrenLeastTime.add(getLeastTimeToCall(child));
    }

    Collections.sort(childrenLeastTime, Collections.reverseOrder());

    for (int i = 0; i < childrenSize; i++) {
      int timeForStart = i + 1;
      childrenLeastTime.set(i, childrenLeastTime.get(i) + timeForStart);
    }

    return Collections.max(childrenLeastTime);
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