import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.util.List;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    int caseNum = 1;
    while (true) {
      int nodeAmount = Input.nextInt();
      int edgeAmount = Input.nextInt();
      if (nodeAmount == 0 && edgeAmount == 0) {
        break;
      }

      Forest forest = new Forest(nodeAmount);
      for (int i = 0; i < edgeAmount; i++) {
        int nodeA = Input.nextInt();
        int nodeB = Input.nextInt();
        forest.addEdge(nodeA, nodeB);
      }

      result.append("Case ").append(caseNum++).append(": ").append(forest.toString()).append('\n');
    }

    System.out.print(result);
  }
}

class Forest {
  private List<Integer>[] connectedInfos;
  private int nodeAmount;
  boolean[] isVisited;

  public Forest(int nodeAmount) {
    this.connectedInfos = Stream.generate(ArrayList<Integer>::new).limit(nodeAmount + 1).toArray(ArrayList[]::new);
    this.nodeAmount = nodeAmount;
    this.isVisited = new boolean[nodeAmount + 1];
  }

  public void addEdge(int nodeA, int nodeB) {
    connectedInfos[nodeA].add(nodeB);
    connectedInfos[nodeB].add(nodeA);
  }

  private boolean hasCycle(int current, int parent) {
    if (isVisited[current]) {
      return true;
    }
    isVisited[current] = true;

    boolean isCycle = false;
    int count = 0;
    for (int connectedNode : connectedInfos[current]) {
      if (connectedNode == parent) {
        count++;
        boolean isConnectedToEachOther = count == 2;
        if (isConnectedToEachOther) {
          return true;
        }
        continue;
      }
      isCycle |= hasCycle(connectedNode, current);
    }

    return isCycle;
  }

  @Override
  public String toString() {
    int treeAmount = getTreeAmount();

    if (treeAmount == 0) {
      return "No trees.";
    } else if (treeAmount == 1) {
      return "There is one tree.";
    }

    return "A forest of " + treeAmount + " trees.";
  }

  private int getTreeAmount() {
    int treeAmount = 0;

    for (int i = 1; i <= nodeAmount; i++) {
      if (isVisited[i]) {
        continue;
      }
      treeAmount += hasCycle(i, 0) ? 0 : 1;
    }

    return treeAmount;
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