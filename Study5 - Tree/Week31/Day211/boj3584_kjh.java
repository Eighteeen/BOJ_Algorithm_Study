import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int T = Input.nextInt();

    for (int i = 0; i < T; i++) {
      int nodeAmount = Input.nextInt();
      int[] parentInfos = new int[nodeAmount + 1];

      for (int j = 0; j < nodeAmount - 1; j++) {
        int parentNum = Input.nextInt();
        int childNum = Input.nextInt();

        parentInfos[childNum] = parentNum;
      }

      int targetNode1 = Input.nextInt();
      int targetNode2 = Input.nextInt();
      sb.append(getMostRecentCommonAncestor(parentInfos, targetNode1, targetNode2))
        .append('\n');
    }

    System.out.print(sb);
  }
  
  static int getMostRecentCommonAncestor(int[] parentInfos, int targetNode1, int targetNode2) {
    List<Integer> commonAncestors = getCommonAncestors(parentInfos, targetNode1, targetNode2);
    final int MOST_RECENT = 0;
    return commonAncestors.get(MOST_RECENT);
  }

  static List<Integer> getCommonAncestors(int[] parentInfos, int targetNode1, int targetNode2) {
    List<Integer> aAncestors = getAncestors(parentInfos, targetNode1);
    List<Integer> bAncestors = getAncestors(parentInfos, targetNode2);

    List<Integer> commonAncestors = new ArrayList<>();
    //// 공통 조상 리스트를 이렇게 구할 수도 있네요! 배워갑니다.
    commonAncestors.addAll(aAncestors);
    commonAncestors.retainAll(bAncestors);

    return commonAncestors;
  }

  static List<Integer> getAncestors(int[] parentInfos, int targetNode) {
    List<Integer> ancestors = new ArrayList<>();
    ancestors.add(targetNode);
    
    int parent = targetNode;
    while (parent != 0) {
      parent = parentInfos[parent];
      ancestors.add(parent);
    }

    return ancestors;
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