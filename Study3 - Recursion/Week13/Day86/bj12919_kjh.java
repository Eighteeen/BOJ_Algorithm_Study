import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    final String S = Input.nextLine();
    final String T = Input.nextLine();

    System.out.print(canMake(S, T, false) ? 1 : 0);
  }

  static boolean canMake(String S, String T, boolean reversed) {
    if (S.length() == T.length()) {
      //// reverse를 안 할 수도 있어서 reversed는 조금 어색하게 느껴집니다. process, check 등은 어떤가요? 
      String reversedS = reversed ? reverseStr(S) : S;
      return reversedS.equals(T);
    }

    //// 처음에는 S와 T의 길이가 같을 수 없으니 아래 조건문이 상단에 위치하는 게 좀 더 자연스러울 것 같습니다!
    if (!T.contains(S) && !T.contains(reverseStr(S))) return false;

    //// reversed를 이용해서 S와 T의 length가 같을 때만 reverse를 해주는 건가? 했는데,
    //// 이미 위에서 false를 반환하는 조건문에서 reverse를 하시니, (안 할 수도 있지만요)
    //// reversed 변수를 넘기기 보다는 reverse한 S를 이용하시는 게 좀 더 깔끔하게 보일 것 같습니다.
    if (reversed) {
      return canMake("A" + S, T, reversed) || canMake("B" + S, T, !reversed);
    }
    return canMake(S + "A", T, reversed) || canMake(S + "B", T, !reversed);
  }
  
  static String reverseStr(String S) {
    //// 아래 방식처럼 reverse를 해도 성능면에서는 별 차이가 없지만, StringBuilder를 이용하시니 reverse()를 사용하는 것이 가독성면에서 좋을 것 같습니다.
    StringBuilder sb = new StringBuilder();
    for (int i = S.length() - 1; i >= 0; i--) {
      sb.append(S.charAt(i));
    }
    return sb.toString();
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