import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 완전 깔끔해요
class Main {
  public static void main(String[] args) throws Exception {
    int leftScore = 100;
    int rightScore = 100;

    final int N = Input.nextInt();
    for(int i = 0; i < N; i++) {
      int leftDice = Input.nextInt();
      int rightDice = Input.nextInt();

      //// 오호 삼항연산자를 이렇게도 사용할 수 있군요! 배워갑니다:22 : 33 옭 삼항연산자 사용 좋네요!!
      leftScore -= (leftDice < rightDice) ? (rightDice) : (0);
      rightScore -= (rightDice < leftDice) ? (leftDice) : (0);
    }

    System.out.print(leftScore+"\n"+rightScore);
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