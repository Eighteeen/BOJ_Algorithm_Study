import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    int nthCase = 1;
    while (true) {
      String word = Input.nextLine();
      String mixedWord = Input.nextLine();
      if (word.equals("END") && mixedWord.equals("END")) break;
      
      if (isSameCombinationOfAlphabets(word, mixedWord)) {
        sb.append("Case ").append(nthCase++).append(": same").append('\n');
        continue;
      }
      sb.append("Case ").append(nthCase++).append(": different").append('\n');
    }

    System.out.print(sb);
  }

  static boolean isSameCombinationOfAlphabets(String word1, String word2) {
    int[] frequency1 = getFrequencyOfAlphabets(word1);
    int[] frequency2 = getFrequencyOfAlphabets(word2);

    if (Arrays.equals(frequency1, frequency2)) {
      return true;
    }
    return false;
  }

  static int[] getFrequencyOfAlphabets(String word) {
    int[] frequency = new int[26];

    for (char ch : word.toCharArray()) {
      frequency[getPlaceOfAlphabet(ch)]++;
    }

    return frequency;
  }

  private static int getPlaceOfAlphabet(char alphabet) {
    return alphabet - 'a';
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