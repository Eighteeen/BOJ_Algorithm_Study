// 문제 실패
// 투자기간과 횟수가 같으면 순서와 무관하게 항상 같은 이자가 나온다는 가정하에 풀었는데
// 그게 아니었나 봄
// 선후 관게를 뒤집는 것까지 재귀로 구현할 방법이 떠오르지 않았음,,

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int H = Input.nextInt();
    final int Y = Input.nextInt();

    System.out.print(investByBestStrategy(H, Y));
  }
  
  static int investByBestStrategy(int balance, int howManyYear) {
    if (howManyYear == 0) return balance;

    if (howManyYear == 5 || howManyYear == 8 || howManyYear == 10) {
      return investByBestStrategy((int) (balance * 1.35), howManyYear - 5);
    }
    
    if (howManyYear >= 3) {
      return investByBestStrategy((int) (balance * 1.2), howManyYear - 3);
    }
    
    return investByBestStrategy((int) (balance * 1.05), howManyYear - 1);
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