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
      String reversedS = reversed ? reverseStr(S) : S;
      return reversedS.equals(T);
    }

    if (!T.contains(S) && !T.contains(reverseStr(S))) return false;

    if (reversed) {
      return canMake("A" + S, T, reversed) || canMake("B" + S, T, !reversed);
    }
    return canMake(S + "A", T, reversed) || canMake(S + "B", T, !reversed);
  }
  
  static String reverseStr(String S) {
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