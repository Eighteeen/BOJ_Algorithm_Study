import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    ////완전 효율적이네요 적은 값을 받으면서 비교해주고 100이 안넘으면 바로 출력,,,감탄했어요.
class Main {
  public static void main(String[] args) throws Exception {
    final String[] MEMBER = {"Soongsil", "Korea", "Hanyang"};

    int lessPoint = 101;
    int lessPointMemberIndex = 0;
    int sumOfPoints = 0;
    for(int i = 0; i < 3; i++) {
      int point = Input.nextInt();
      
      if (point < lessPoint) {
        lessPoint = point;
        lessPointMemberIndex = i;
      }

      sumOfPoints += point;
    }

    if (sumOfPoints >= 100) {
      System.out.print("OK");
      return;
    }
    System.out.print(MEMBER[lessPointMemberIndex]);
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
