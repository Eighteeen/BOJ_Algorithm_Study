import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();

    Queue<Integer> cards = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      cards.offer(i);
    }

    int card = 0;
    //// 이미 피드백 남기셨는데 while의 조건문을 size가 1이 아닐때로 체크하시면 더욱 간결하고 조건문 체크가 줄어들어 조건문을 바꿔보시면 좋을 것 같습니다!
    while (!cards.isEmpty()) {
      card = cards.poll();
      if (cards.isEmpty()) break;
      cards.offer(cards.poll());
    }

    System.out.print(card);
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