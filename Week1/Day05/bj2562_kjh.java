import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    ////무난하게 잘 짜신거 같아요.:22 : 33
class Main {
  public static void main(String[] args) throws Exception {
    int highScore = 0;
    int highLocation = 0;
    //// 여기 사이에는 개행이 있으면 한번에 쭉 읽기 더 좋을 것 같아요
    for(int i = 0; i < 9; i++) {
      int number = Input.nextInt();
      if (number > highScore) {
        highScore = number;
        highLocation = i + 1;
      }
    }
    System.out.print(highScore + "\n" + highLocation);
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
