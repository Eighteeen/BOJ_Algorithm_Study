import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

class Main {
  static Map<Integer, Node> nodeMap;

  public static void main(String[] args) throws Exception {
    final int LANDS = Input.nextInt();
    final int DUCKS = Input.nextInt();

    nodeMap = new HashMap<>();
    makeDuckVillage(1, LANDS);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < DUCKS; i++) {
      int landToTake = Input.nextInt();
      sb.append(getResultForTakingLand(landToTake)).append('\n');
    }
    
    System.out.print(sb);
  }

  static int getResultForTakingLand(int landToTake) {
    Node toTake = nodeMap.get(landToTake);
    Node parent = toTake;
    
    int takenNumber = 0;

    while (landToTake > 0) {
      parent = nodeMap.get(landToTake);
      if (parent.taken) takenNumber = parent.data;
      landToTake /= 2;
    }

    if (takenNumber == 0) toTake.taken = true;
    return takenNumber;
  }

  static Node makeDuckVillage(int start, int end) {
    if (start > end) return null;

    Node root = new Node(start);
    root.left = makeDuckVillage(start * 2, end);
    root.right  = makeDuckVillage(start * 2 + 1, end);

    nodeMap.put(start, root);
    return root;
  }
}

class Node {
  public int data;
  public Node left;
  public Node right;
  public boolean taken;

  public Node(int data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return String.valueOf(data) + "(" + String.valueOf(left != null ? left.data : 0) + ", " + String.valueOf(right != null ? right.data : 0) + ")";
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