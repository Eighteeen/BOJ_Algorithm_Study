//// 문제풀이 실패 : 메모리와 시간 두마리 토끼를 잡는 로직을 구상하지 못함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();
    BinaryTree tree = new BinaryTree(NODE_AMOUNT);

    for (int i = 2; i <= NODE_AMOUNT; i++) {
      int depth = Input.nextInt();
      tree.addNodeInSpecificDepth(depth);
      
      if (tree.isErrorCaused) {
        System.out.print(-1);
        return;
      }
    }

    System.out.print(tree.getANNodes());
  }
}

class BinaryTree {
  private Map<Integer, Integer> nodes;
  private int[] nodeAmountInDepth;
  private int nodeAmount;

  public boolean isErrorCaused;

  private final int READY_FOR_NUMBERING = 0;

  public BinaryTree(int nodeAmount) {
    this.nodeAmount = nodeAmount;

    nodes = new LinkedHashMap<>();
    nodes.put(0, READY_FOR_NUMBERING);
    
    nodeAmountInDepth = new int[nodeAmount];
    nodeAmountInDepth[0] = 1;
  }

  public void addNodeInSpecificDepth(int currentDepth) {
    int nodeAmountUpperDepth = nodeAmountInDepth[currentDepth - 1];
    int nodeAmountCurrentDepth = nodeAmountInDepth[currentDepth];

    if (nodeAmountCurrentDepth + 1 > nodeAmountUpperDepth * 2) {
      isErrorCaused = true;
      return;
    }

    int depthStartIndex = (int) Math.pow(2, currentDepth) - 1;
    int depthEndIndex = depthStartIndex * 2;
    for (int i = depthStartIndex; i <= depthEndIndex; i++) {
      if (nodes.get(i) == null) {
        System.out.printf("enable(%d)\n", i);
        nodes.put(i, READY_FOR_NUMBERING);
        break;
      }
    }

    nodeAmountInDepth[currentDepth]++;
  }

  private int numberNodesAndReturnTreeSize(int rootIdx, int startNumber) {
    if (nodes.get(rootIdx) == null) return 0;

    int leftTreeIndex = rootIdx * 2 + 1;
    int rightTreeIndex = leftTreeIndex + 1;

    int leftTreeSize = numberNodesAndReturnTreeSize(leftTreeIndex, startNumber);
    nodes.put(rootIdx, startNumber + leftTreeSize);
    int rightTreeSize = numberNodesAndReturnTreeSize(rightTreeIndex, nodes.get(rootIdx) + 1);

    return leftTreeSize + 1 + rightTreeSize;
  }

  public String getANNodes() {
    numberNodesAndReturnTreeSize(0, 1);

    StringBuilder result = new StringBuilder();
    for (Integer n : nodes.keySet()) {
      result.append(nodes.get(n)).append('\n');
    }    

    return result.toString();
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