import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    //// 코드를 재사용하기 쉽도록 만들었네요!
class Main {
  public static void main(String[] args) throws Exception {
    final int PLAYER_AMOUNT = 5;
    final int SCORE_AMOUNT = 4;
    //// 변수활용을 직관적으로 잘 하신 거 같아요

    int score = 0;
    int topPlayer = 0;
    int maxScore = -1;
    for (int i = 0; i < PLAYER_AMOUNT; i++) {
      for (int j = 0; j < SCORE_AMOUNT; j++) {
        score += Input.nextInt();
      }
      
      if (score > maxScore) {
        topPlayer = i + 1;
        maxScore = score;
      }
      score = 0;
    }
    System.out.println(topPlayer + " " + maxScore);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}
