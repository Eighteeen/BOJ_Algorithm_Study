import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final String N = Input.nextLine();
    int[] needNumbers = new int[9];
    Arrays.fill(needNumbers, 0);

    final int SIX_OR_NINE = 6;
    for(int i = 0; i < N.length(); i++) {
      int num = charToInt(N.charAt(i));
      int needNumberIndex = (num == 9) ? (SIX_OR_NINE) : (num);
      needNumbers[needNumberIndex] += 1;
    }

    needNumbers[SIX_OR_NINE] = (int) Math.ceil(needNumbers[SIX_OR_NINE] / 2.0);

    int needSet = calcMax(needNumbers);
    System.out.print(needSet);
  }

  private static int charToInt(char ch) {
    return ch - '0';
  }

  private static int calcMax(int[] intArr) {
    int max = intArr[0];
    for(int i = 1; i < intArr.length; i++) {
      if (intArr[i] > max) {
        max = intArr[i];
      }
    }
    return max;
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