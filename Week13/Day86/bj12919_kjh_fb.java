import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    final String S = Input.nextLine();
    final String T = Input.nextLine();

    System.out.print(canMake(S, T) ? 1 : 0);
  }

  //// reverse를 안 할 수도 있어서 reversed는 조금 어색하게 느껴집니다. process, check 등은 어떤가요?
  //// => 맞워요 피드백 반영중에 사라졌지만 피드백 고마워요!

  //// 처음에는 S와 T의 길이가 같을 수 없으니 아래 조건문이 상단에 위치하는 게 좀 더 자연스러울 것 같습니다!
  //// => contains 연산을 덜 하게 하려고 의도적으로 길이부터 거르게 한 부분입니다!


  //// reversed를 이용해서 S와 T의 length가 같을 때만 reverse를 해주는 건가? 했는데,
  //// 이미 위에서 false를 반환하는 조건문에서 reverse를 하시니, (안 할 수도 있지만요)
  //// reversed 변수를 넘기기 보다는 reverse한 S를 이용하시는 게 좀 더 깔끔하게 보일 것 같습니다.
    //// => 여러번 reverse하기보다 최종 딱 한번만 reverse하겠다고 그렇게 짠건데
    //// => 뒤집을수밖에 없다는걸 뒤늦게 알았네요 ㅋㅋㅋ 날카로운 지적 고마워요!

  static boolean canMake(String S, String T) {
    if (S.length() == T.length()) {
      return S.equals(T);
    }

    if (!T.contains(S) && !T.contains(reverseStr(S))) return false;
    return canMake(S + "A", T) || canMake(reverseStr(S + "B"), T);
  }
  

  //// 아래 방식처럼 reverse를 해도 성능면에서는 별 차이가 없지만, StringBuilder를 이용하시니 reverse()를 사용하는 것이 가독성면에서 좋을 것 같습니다.
  //// 그르네요 으챂 sb쓰는거 내부함수 쓰는걸로 바꿨습니다
  static String reverseStr(String S) {
    return new StringBuilder(S).reverse().toString();
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

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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