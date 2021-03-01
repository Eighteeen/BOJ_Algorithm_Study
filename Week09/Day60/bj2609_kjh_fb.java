import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 전체적으로 깔끔합니다!
class Main {
  public static void main(String[] args) throws Exception {
    final int num1 = Input.nextInt();
    final int num2 = Input.nextInt();
    
    //// 쉬어가는 문제였지만, 최대공약수를 재귀함수로 구하는 방법도 유명하더라구요! '유클리드 호제법' 보시고 그 방법으로 적용해봐도 좋을 것 같아요!
    //// => 풀 당시에 생각도 못했네요 ㅌㅋㅋㅋ 고마워요!
    int gcd = calcGCD(num1, num2);
    System.out.println(gcd);

    int lcm = num1 * num2 / gcd;
    System.out.print(lcm);
  }

  static int calcGCD(int num1, int num2) {
    if (num2 == 0) return num1;
    return calcGCD(num2, num1 % num2);
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