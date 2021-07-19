import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  ////깔끔하게 잘 짠거 같습니다.:22 : 333
class Main {
  public static void main(String[] args) throws Exception {
    int sum = 0;
    int min = 101;
    //// 개행을 넣어주세요
    //// => 네엥 앞으로 반영할게요
    for(int i = 0; i < 7; i++) {
      int number = Input.nextInt();
      if (number % 2 == 1) {
        min = (number < min) ? (number) : (min);
        sum += number;
      }
    }
    if (sum == 0) {
      System.out.print(-1);
      return;
    }
    System.out.print(sum + "\n" + min);
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