import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    final int EDGE_AMOUNT = Input.nextInt();
    final int QUERY_AMOUNT = Input.nextInt();

    Tree tree = new Tree(EDGE_AMOUNT);
    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();

      tree.addInterconnectedNodes(nodeA, nodeB);
    }

    List<Integer> cycleNodes = tree.getCycleNodes();

    Map<Integer, Integer> rootThatNodeBelongs = new HashMap<>();
    for (int i = 0; i < cycleNodes.size(); i++) {
      int root = cycleNodes.remove(i);
      List<Integer> ancestors = tree.getAncestorsExceptCycle(root, cycleNodes);
      cycleNodes.add(i, root);

      for (int ancestor : ancestors) {
        rootThatNodeBelongs.put(ancestor, root);
      }
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < QUERY_AMOUNT; i++) {
      int beginNode = Input.nextInt();
      int endNode = Input.nextInt();

      int rootThatBeginNodeBelongs = rootThatNodeBelongs.get(beginNode);
      int rootThatEndNodeBelongs = rootThatNodeBelongs.get(endNode);

      int pathCount = rootThatBeginNodeBelongs == rootThatEndNodeBelongs ? 1 : 2;
      result.append(pathCount).append('\n');
    }

    System.out.print(result);
  }
}

class Tree {
  private int nodeAmount;
  private List<Integer>[] connectedInfos;

  public Tree(int nodeAmount) {
    this.nodeAmount = nodeAmount;
    connectedInfos = new ArrayList[nodeAmount + 1];
  }

  public void addInterconnectedNodes(int nodeA, int nodeB) {
    connect(nodeA, nodeB);
    connect(nodeB, nodeA);
  }

  public List<Integer> getCycleNodes() {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);

    boolean[] isVisited = new boolean[nodeAmount + 1];
    int[] parentInfos = new int[nodeAmount + 1];

    Integer node;
    while (true) {
      node = stack.pop();
      if (isVisited[node]) {
        break;
      }
      isVisited[node] = true;
      
      for (Integer connectedNode : connectedInfos[node]) {
        if (parentInfos[node] == connectedNode) {
          continue;
        }
        stack.push(connectedNode);
        parentInfos[connectedNode] = node;
      }
    }

    List<Integer> cycleNodes = new ArrayList<>();

    Integer cycleBegin = node;
    do {
      cycleNodes.add(node);
      node = parentInfos[node];
    } while (node != cycleBegin);

    return cycleNodes;
  }

  public List<Integer> getAncestorsExceptCycle(int root, List<Integer> cycleNodes) {
    List<Integer> ancestors = new ArrayList<>();
    
    Stack<Integer> stack = new Stack<>();
    boolean[] isVisited = new boolean[nodeAmount + 1];
    stack.push(root);

    while (stack.size() > 0) {
      Integer node = stack.pop();
      isVisited[node] = true;

      ancestors.add(node);
      for (Integer connectedNode : connectedInfos[node]) {
        if (cycleNodes.contains(connectedNode) || isVisited[connectedNode]) {
          continue;
        }
        stack.push(connectedNode);
      }
    }

    return ancestors;
  }

  private void connect(int from, int to) {
    if (connectedInfos[from] == null) {
      connectedInfos[from] = new ArrayList<>();
    }
    connectedInfos[from].add(to);
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