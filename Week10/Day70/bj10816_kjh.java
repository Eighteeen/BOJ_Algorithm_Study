import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 전체적으로 무난 깔끔합니다
class Main {
  public static void main(String[] args) {
    //// 해당 상수를 선언만 하고 사용하지 않은 이유가 뭘까요..? 아마 선언만 하고 까먹으신 거겠죠..?
    final int CARD_NUMBER_RANGE =  20000001;
    final int MAX_CARD_NUMBER = 10000000;

    int[] havingCardsCount = new int[20000001];
    Arrays.fill(havingCardsCount, 0);

    final int N = Input.nextInt();
    for (int i = 0; i < N; i++) {
      int cardNumber = Input.nextInt();
      havingCardsCount[cardNumber + MAX_CARD_NUMBER]++;
    }

    StringBuilder sb = new StringBuilder();

    final int M = Input.nextInt();
    for (int i = 0; i < M; i++) {
      int targetCard = Input.nextInt();
      sb.append(havingCardsCount[targetCard + MAX_CARD_NUMBER])
        .append(' ');
    }

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