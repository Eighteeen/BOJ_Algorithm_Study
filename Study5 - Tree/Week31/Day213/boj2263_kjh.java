import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//// 깔끔해요 : 22
class Main {
  static List<Integer> inorder;
  static List<Integer> postorder;

  static Map<Integer, Integer> inorderIdxMap;
  //// sb는 너무 공통적인 변수명으로 느껴져서 프리오더를 저장한다는 걸 알 수 있도록 지정하면 좋을 것 같습니다.
  //// => 타성에 젖어서 썼네요. 피드백 고마워요!
  static StringBuilder resultInPreorder;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    inorderIdxMap = new HashMap<>();
    inorder = new ArrayList<>();
    postorder = new ArrayList<>();

    for (int i = 0; i < NODE_AMOUNT; i++) {
      Integer node = Input.nextInt();
      inorder.add(node);
      inorderIdxMap.put(node, i);
    }
    for (int i = 0; i < NODE_AMOUNT; i++) {
      postorder.add(Input.nextInt());
    }

    resultInPreorder = new StringBuilder();
    getResultOfTraversingInPreorder(0, NODE_AMOUNT - 1, 0, NODE_AMOUNT - 1);

    System.out.print(resultInPreorder);
  }

  //// 정말 프리오더 방식으로 노드를 방문한다기 보단 다른 오더 방식으로부터 프리오더를 추출하는 메서드라서
  //// traverse 이라는 용어가 조금 어색하게 느껴졌습니다. set, add, infer 등등 대체할 수 있는 단어는 많을 것 같습니다.
  //// => 반영했습니다!
  static void getResultOfTraversingInPreorder(int inStartIdx, int inEndIdx, int postStartIdx, int postEndIdx) {
    if (inStartIdx > inEndIdx) {
      return;
    }

    int root = postorder.get(postEndIdx);
    //// StringBuilder를 사용하는데 굳이 +를 사용할 필요는 없을 것 같습니다.
    //// => 삽질의 흔적을 다 못 지운 ㅋㅋ
    resultInPreorder.append(root).append(' ');

    int inRootIdx = inorderIdxMap.get(root);
    int leftTreeSize = inRootIdx - inStartIdx;
    getResultOfTraversingInPreorder(inStartIdx, inRootIdx - 1, postStartIdx, postStartIdx + leftTreeSize - 1);
    getResultOfTraversingInPreorder(inRootIdx + 1, inEndIdx, postStartIdx + leftTreeSize, postEndIdx - 1);
  }
}

class Node {
  public Integer data;
  public Node left;
  public Node right;

  public Node(int data) {
    this.data = data;
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