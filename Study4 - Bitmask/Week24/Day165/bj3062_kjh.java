import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      String strNumber = Input.nextLine();
      String reversed = reverse(strNumber);
      
      int number = Integer.parseInt(strNumber);
      int reversedNumber = Integer.parseInt(reversed);

      int sum = number + reversedNumber;
      if (isPalindrome(sum)) {
        sb.append("YES").append('\n');
        continue;
      }
      sb.append("NO").append('\n');
    }

    System.out.print(sb);
  }

  static String reverse(String str) {
    StringBuffer bfStr = new StringBuffer(str);
    return bfStr.reverse().toString();
  }

  static boolean isPalindrome(int number) {
    String strNumber = String.valueOf(number);
    int len = strNumber.length();

    for (int i = 0; i < len / 2; i++) {
      if (strNumber.charAt(i) != strNumber.charAt(len - i - 1)) {
        return false;
      }
    }
    return true;
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