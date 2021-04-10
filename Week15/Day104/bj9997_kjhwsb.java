import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    String[] words = new String[N];
    for (int i = 0; i < N; i++) {
      String word = Input.nextLine();
      words[i] = word;
    }
    
    System.out.print(countCompleteSentence(words, new boolean[26]));
  }

  static int countCompleteSentence(String[] words, boolean[] usedAlphabets) {
    int len = words.length;
    if (len == 0 || !canMakeCompleteSentence(words, usedAlphabets)) { 
      return 0;
    }

    int count = 0;
    for (int i = 0; i < len; i++) {
      String word = words[i];

      boolean[] currentUsed = usedAlphabets.clone();
      for (char letter : word.toCharArray()) {
        currentUsed[letter - 'a'] = true;
      }

      if (areAllTrue(currentUsed)) {
        count += (int) Math.pow(2, len - i - 1);
        continue;
      }

      String[] nextCombination = Arrays.copyOfRange(words, i + 1, len);
      count += countCompleteSentence(nextCombination, currentUsed);
    }

    return count;
  }

  static boolean canMakeCompleteSentence(String[] words, boolean[] usedAlphabets) {
    boolean[] used = usedAlphabets.clone();

    for (String word : words) {
      for (char letter : word.toCharArray()) {
        used[letter - 'a'] = true;
      }
    }

    return areAllTrue(used);
  }

  static boolean areAllTrue(boolean[] array) {
    for (boolean item : array) {
      if (!item) return false;
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