import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    String[] treeInfo = Input.nextLine().split(" ");

    int height = Integer.parseInt(treeInfo[0]);
    String path = treeInfo.length == 2 ? treeInfo[1] : "";

    int[] tree = makeTree(height);
    int nodeAtEndOfPath = getNodeAtEndOfPath(tree, path);

    System.out.print(nodeAtEndOfPath);
  }

  static int[] makeTree(int height) {
    int nodes = (int) Math.pow(2, height + 1) - 1;
    int[] tree = new int[nodes];

    for (int i = 0; i < nodes; i++) {
      tree[i] = nodes - i;
    }

    return tree;
  }

  static int getNodeAtEndOfPath(int[] tree, String path) {
    int nodeLocation = 1;
    
    for (char direction : path.toCharArray()) {
      if (direction == 'L') {
        nodeLocation = nodeLocation * 2;
      } else if (direction == 'R') {
        nodeLocation = nodeLocation * 2 + 1;
      }
    }

    return tree[nodeLocation - 1];
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
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
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}