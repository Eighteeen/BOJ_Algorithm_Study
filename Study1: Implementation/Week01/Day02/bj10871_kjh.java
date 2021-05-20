import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔해요.
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int X = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      int number = Input.nextInt();
      //// 숫자의 활용을 하는 문제라서 Input.nextInt()을 뜯어봤는데 활용성 있게 메소드를 작성하신 것 같으면서도 너무 세부적으로 나눠놓아서 한번에 파악하기에 어려웠습니다.
      //// 굳이 나눌 필요가 없는 부분은 하나의 메소드로 합치는 게 좋을 것 같아요.
      //// => next, nextLine, nextInt 셋 모두 Scanner에서 따온 이름이에요!
      //// => 더 효율적이면서 (BufferedReader 사용)
      //// => 더 간편한 (static 메소드라서 인스턴스화 하지 않아도 사용할 수 있음)
      //// => 클래스로 제가 따로 만들어서 항상 참조하는 클래스로 사용하고 있습니다
      if (number < X) {
        sb.append(number).append(' ');
      }
    }
    System.out.println(sb);
  }
}
    ////효율적인 코드를 만들기 위한 노력이 대단합니다
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
