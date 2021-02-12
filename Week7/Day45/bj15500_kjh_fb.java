import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

//// 피드백 전체적으로 다 반영했슴다 ㅎㅎ 피드백 고마워요
class Main {
  static Stack<Integer> rods[];

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    rods = new Stack[3];
    rods[1] = new Stack<>(); rods[2] = new Stack<>();

    final int K = Input.nextInt();
    for (int i = 0; i < K; i++) {
      rods[1].push(Input.nextInt());
    }

    int from = 1, to = 2;
    int currentOrder = K;
    int moveCount = 0;
    while (currentOrder > 0) {
      while (!rods[from].isEmpty()) {
        int top = rods[from].pop();
        if (top == currentOrder) {
          currentOrder--;
          moveCount++;
          sb.append(from).append(' ').append(3).append('\n');
          continue;
        }
        rods[to].push(top);
        moveCount++;
        sb.append(from).append(' ').append(to).append('\n');
      }

      int tmp = from;
      from = to;
      to = tmp;
    }
    
    System.out.println(moveCount);
    System.out.print(sb);
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