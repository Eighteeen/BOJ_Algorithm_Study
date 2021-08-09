import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Stream;

//// ㄲㄲ
class Main {
  static int[] leftmostColumn;
  static int[] rightmostColumn;
  static Node[] nodes;

  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    leftmostColumn = new int[NODE_AMOUNT + 1];
    rightmostColumn = new int[NODE_AMOUNT + 1];
    //// generate 사용법 알아갑니다!
    nodes = Stream.generate(Node::new).limit(NODE_AMOUNT + 1).toArray(Node[]::new);

    for (int i = 0; i < NODE_AMOUNT; i++) {
      int parent = Input.nextInt();
      int left = Input.nextInt();
      int right = Input.nextInt();

      if (left > 0) {
        nodes[left].parent = nodes[parent];
        nodes[parent].left = nodes[left];
      }
      if (right > 0) {
        nodes[right].parent = nodes[parent];
        nodes[parent].right = nodes[right];
      }
    }

    Node root = getRoot();
    Arrays.fill(leftmostColumn, NODE_AMOUNT);

    updateLeftRightmostInRow(root, 1, 1);

    int widestRow = 0;
    int widestWidth = 0;
    for (int i = 1; i <= NODE_AMOUNT; i++) {
      if (rightmostColumn[i] == 0) {
        break;
      }

      int width = rightmostColumn[i] - leftmostColumn[i] + 1;
      if (width > widestWidth) {
        widestRow = i;
        widestWidth = width;
      }
    }

    System.out.printf("%d %d", widestRow, widestWidth);
  }

  static Node getRoot() {
    Node node = nodes[1];
    while (node.parent != null) {
      node = node.parent;
    }
    return node;
  }

  static int updateLeftRightmostInRow(Node root, int depth, int startNumber) {
    if (root == null) {
      return 0;
    }

    int leftSize = updateLeftRightmostInRow(root.left, depth + 1, startNumber);
    startNumber += leftSize;

    int columnNumber = startNumber;
    leftmostColumn[depth] = Math.min(columnNumber, leftmostColumn[depth]);
    //// rightmostColumn[depth] = columnNumber 로 연산을 줄이는 것은 어떨까요? (어짜피 맨 오른쪽은 맨 마지막에
    //// 나와서 max 비교가 필요없음)
    //// => 올 그러네요 고마워요!
    rightmostColumn[depth] = columnNumber;

    startNumber++;
    int rightSize = updateLeftRightmostInRow(root.right, depth + 1, startNumber);

    return leftSize + 1 + rightSize;
  }
}

class Node {
  public Node parent;
  public Node left;
  public Node right;
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