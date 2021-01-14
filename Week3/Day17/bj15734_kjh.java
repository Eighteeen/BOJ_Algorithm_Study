import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
////깔끔해요 아주
class Main {
  public static void main(String[] args) throws Exception {
    final int LEFT = Input.nextInt();
    final int RIGHT = Input.nextInt();
    final int ALL = Input.nextInt();

    int maxRemainPlayer = 0;
    for (int i = 0; i <= ALL; i++) {
      int leftPlayer = LEFT + i;
      int rightPlayer = RIGHT + (ALL - i);
      
      int difference = Math.abs(leftPlayer - rightPlayer);
      int remainPlayer = (LEFT + RIGHT + ALL) - difference;
      if (remainPlayer > maxRemainPlayer) maxRemainPlayer = remainPlayer;
    }

    System.out.print(maxRemainPlayer);
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
