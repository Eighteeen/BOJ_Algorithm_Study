import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

// 로직을 도무지 생각해낼 수 없어서 문제풀이 실패 때릴 위기에 있었는데,
// 실패보단 힌트받고 푸는게 더 낫지 않나 싶어 켱씨의 힌트를 받고 풀어냈음. 감사의 뜻을 전하옵니다
//// 사소한 힌트만 줬고 혼자 생각해서 풀었다고 생각해요! 저보다 함수명이 이해가 더 나은 것 같네요
//// true false 를 사용해서 푸는 방법을 생각하지 못 했어요. 이번 문제 kjh, hsk 코드와 비교해서 저의 풀이 정-말 복잡해보이네요. ㅠㅠ
class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    Tree tree = new Tree(NODE_AMOUNT);
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();
      tree.addInterconnectedNodes(nodeA, nodeB);
    }

    System.out.print(tree.getMaxWinCase());
  }
}

class Tree {
  private int nodeAmount;
  private List<Integer>[] connectedInfos;
  private boolean[] isLeftInfos;

  public Tree(int nodeAmount) {
    this.nodeAmount = nodeAmount;
    connectedInfos = new ArrayList[nodeAmount + 1];
    isLeftInfos = new boolean[nodeAmount + 1];
  }

  //// Tree 클래스의 getMaxWinCase 라는 함수명은 언발란스합니다. 트리인데 이기는 케이스..?
  //// 전날 피드백 남겨주신 것처럼 작명상의 추상화 단계가 달라서 생긴 문제인 것 같습니다.
  public int getMaxWinCase() {
    if (nodeAmount == 1) {
      return 1;
    }
    if (nodeAmount == 2) {
      return 0;
    }

    //// fillIsLeftInfos에서는 current 이지만 이곳에서는 current라는 변수명은 다시 반복하나? 정도의 오해의 소지가 있어보입니다.
    int current = getAnyNodeThatIsNotLeaf();
    int parent = 0;
    boolean isLeft = true;
    fillIsLeftInfos(current, parent, isLeft);

    List<Integer> leaves = getLeaves();
    if (leaves.size() == nodeAmount - 1) {
      return nodeAmount - 2;
    }

    int leftLeaves = 0;
    int rightLeaves = 0;
    for (Integer leaf : leaves) {
      leftLeaves += isLeftInfos[leaf] ? 1 : 0;
      rightLeaves += !isLeftInfos[leaf] ? 1 : 0;
    }

    return Math.max(leftLeaves, rightLeaves);
  }

  private int getAnyNodeThatIsNotLeaf() {
    for (int i = 1; i < connectedInfos.length; i++) {
      if (connectedInfos[i].size() > 1) {
        return i;
      }
    }
    return 0;
  }

  //// fillIsLeftInfos() 에서 leftLeaves, rightLeaves 를 충분히 구할 수 있을 것 같습니다.
  private void fillIsLeftInfos(int current, int parent, boolean isLeft) {
    isLeftInfos[current] = isLeft;

    for (int connectedNode : connectedInfos[current]) {
      if (connectedNode == parent) {
        continue;
      }
      fillIsLeftInfos(connectedNode, current, !isLeft);
    }
  }

  private List<Integer> getLeaves() {
    List<Integer> leaves = new ArrayList<>();
    for (int i = 1; i < connectedInfos.length; i++) {
      List<Integer> connectedInfo = connectedInfos[i];
      if (connectedInfo.size() == 1) {
        leaves.add(i);
      }
    }
    return leaves;
  }

  public void addInterconnectedNodes(int nodeA, int nodeB) {
    if (connectedInfos[nodeA] == null) {
      connectedInfos[nodeA] = new ArrayList<>();
    }
    if (connectedInfos[nodeB] == null) {
      connectedInfos[nodeB] = new ArrayList<>();
    }

    connectedInfos[nodeA].add(nodeB);
    connectedInfos[nodeB].add(nodeA);
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