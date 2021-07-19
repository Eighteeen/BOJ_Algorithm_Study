import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

//// 깔끔해요
class Main {
  static Set<Integer> taken;

  public static void main(String[] args) throws Exception {
    final int LANDS = Input.nextInt();
    final int DUCKS = Input.nextInt();

    taken = new HashSet<>();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < DUCKS; i++) {
      int landToTake = Input.nextInt();
      sb.append(getResultForTakingLand(landToTake)).append('\n');
    }
    
    System.out.print(sb);
  }

  static int getResultForTakingLand(int landToTake) {
    int parent = landToTake;
    
    int takenNumber = 0;
    while (parent > 0) {
      if (taken.contains(parent)) takenNumber = parent;
      parent = parent / 2;
    }

    if (takenNumber == 0) taken.add(landToTake);
    return takenNumber;
  }

  //// 로직상 연결되어 있는 노드들을 관리하는 코드는 없어서 문제 자체에서 Node를 쓸 필요가 있나 싶어요. (활용성이 적어보임)
  //// 위 내용과 연결되는 내용으로 아래 함수는 실질적인 문제를 푸는데에 꼭 필요한 흐름은 아닌 것 같아요.
  //// : 22 
  //// => 노드에 너무 집중해서 필요없는 연산을 하고 있다는 자각이 없었네요 ㅠㅠㅋ 
  //// => 개선 사항 피드백 주셔서 감사합니다! ㅎㅎ 시간 30%나 줄었어요
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