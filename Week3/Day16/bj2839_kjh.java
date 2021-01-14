import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 페어프로그래밍 할 때 완전 쥐어짜 가면서 한 거 같은데 짧고 간결한 코드를 보니 현타가 옵니다... 깔끔깔끔:22 혖ㄴ타 매우 옵니다,,,대체 어떻게 저렇게 생각해내죠?
//// => 규칙 찾을 엄두가 안나서 무식하게 푸는 방법을 고민했습니다. 컴퓨터는 빠르니까요
//// => 규칙 찾아내신 근성이 저는 정말 대단하게 느껴집니다..
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    int fiveQuota = N / 5;
    int minNeedBags = -1;
    for(int i = fiveQuota; i >= 0; i--) {
      int left = N - (i * 5);
      
      if (left % 3 == 0) {
        minNeedBags = i + (left / 3);
        break;
      }
    }
    System.out.print(minNeedBags);
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
