import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;
//// 우왁 깔끔해요!!
class Main {
  public static void main(String[] args) throws Exception {
    final int PUT_FIRST = 1, PUT_SECOND = 2, PUT_LAST = 3;
    Stack<Integer> skills = new Stack<>();
    Deque<Integer> originalCards = new ArrayDeque<>();
    
    final int N = Input.nextInt();
    for (int i = 0; i < N; i++) {
      skills.push(Input.nextInt());
    }

    for (int card = 1; card <= N; card++) {
      int skillInReversedOrder = skills.pop();
      switch (skillInReversedOrder) {
        case PUT_FIRST:
          originalCards.offerFirst(card);
          break;
        case PUT_SECOND:
          int currentFirstCard = originalCards.pollFirst();
          originalCards.offerFirst(card);
          originalCards.offerFirst(currentFirstCard);
          break;
        case PUT_LAST:
          originalCards.offerLast(card);
          break;
      }
    }

    StringBuilder sb = new StringBuilder();
    originalCards.stream()
      .forEach(card -> sb.append(card).append(' '));
    
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
