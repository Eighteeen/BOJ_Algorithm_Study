import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 효율 깔끔
class Main {
  public static void main(String[] args) {
    final int N = Input.nextInt();
    System.out.print(nthSixSixSix(N));
  }

  static int nthSixSixSix(int nth) {
    int cnt = 0;
    int number = 665;

    do {
      number++;
      if (isSixSixSix(number)) {
        cnt++;
      }
    } while (cnt != nth);

    return number;
  }
  
  //// 오호 이렇게하면 효율적으로 하나씩 비교할 수 있군요 배워갑니다!!
  static boolean isSixSixSix(int number) {
    int strike = 0;
    while (number > 0) {
      if (number % 10 == 6) { 
        strike++;
      } else {
        strike = 0;
      }

      if (strike == 3) return true;
      number = number / 10;
    }
    return false;
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