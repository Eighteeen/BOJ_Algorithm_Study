import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.util.List;

//// 깔끔해요 : 22
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

    for (int connectedNode : connectedInfos[current]) {
      if (connectedNode == parent) {
        //// count 관련 문장 없어도 solve 되고, 애초에 isConnectedToEachOther가 true인 상황은 나올 수 없네요.
        //// => 별 짓을 다 해보다가.. 이렇게 됐네요
        continue;
      }
      //// hasCycle() 가 true 일 때 바로 return 하면 나머지 탐색 하지 않아도 되니 바로 return하는 게 좋을 것
      //// 같습니다.
      //// => 그걸 전혀 생각못했네요. 위쪽도 그렇고 꼼꼼한 피드백들 고마워요! 시간도 줄었어요
      if (hasCycle(connectedNode, current)) {
        return true;
      }
    }

    return false;
  }

  //// toString 활용 좋네요 : 22
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