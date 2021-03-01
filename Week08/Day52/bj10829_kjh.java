import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 완전 깔끔해요
//// => 피드백 주시진 않았찌만 StringBuilder를 반환하게 하는 방식 맘에 들어서 반영했슴다 ㅎ
class Main {
  public static void main(String[] args) throws Exception {
    final long N = Input.nextLong();
    System.out.print(decimalToBinary(N));
  }

  static StringBuilder decimalToBinary(long num) {
    StringBuilder sb = new StringBuilder();
    if (num <= 1) return sb.append(num);
    //// 오호 이런식으로도 구현이 가능하군요! 다른 방법도 알아가요
    return sb.append(decimalToBinary(num / 2))
      .append(num % 2); 
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