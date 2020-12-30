import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final String N = Input.next();
    int length = N.length();

    String leftDigits = N.substring(0, length / 2);
    String rightDigits = N.substring(length / 2);

    int sumLeftDigits = calcSumOfDigits(leftDigits);
    int sumRightDigits = calcSumOfDigits(rightDigits);

    if (sumLeftDigits == sumRightDigits) {
      System.out.println("LUCKY");
      return;
    }
    System.out.println("READY");
  }

  private static int calcSumOfDigits(String digits) {
    int sum = 0;
    for(int i = 0; i < digits.length(); i++) {
      int digit = charToInt(digits.charAt(i));
      sum += digit;
    }
    return sum;
  }

  private static int charToInt(char ch) {
    return ch - '0';
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