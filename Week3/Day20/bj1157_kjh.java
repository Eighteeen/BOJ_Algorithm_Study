import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
  ////군더더기 없는코드네요 잘 짜신거 같아요. : 22
class Main {
  public static void main(String[] args) throws Exception {
    final String WORD = Input.nextLine();
    String upperCaseWord = WORD.toUpperCase();
    
    final int ALPHABET_COUNTS = 'Z' - 'A' + 1;
    int[] usedCounts = new int[ALPHABET_COUNTS];

    for (char alphabet : upperCaseWord.toCharArray()) {
      usedCounts[getPlaceOfAlphabet(alphabet)]++;
    }

    int highestUsedCount = Arrays.stream(usedCounts).max().getAsInt();
    long highestUsedAlphabetsCount = Arrays.stream(usedCounts)
      .filter(usedCount -> (usedCount == highestUsedCount))
      .count();

    if (highestUsedAlphabetsCount > 1) {
      System.out.print("?");
      return;
    }
    
    for (int i = 0; i < ALPHABET_COUNTS; i++) {
      if (usedCounts[i] == highestUsedCount) {
        System.out.print(getNthPlaceAlphabet(i));
        return;
      }
    }
  }

  private static int getPlaceOfAlphabet(char alphabet) {
    return alphabet - 'A';
  }

  private static char getNthPlaceAlphabet(int n) {
    return (char) ('A' + n);
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

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
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
