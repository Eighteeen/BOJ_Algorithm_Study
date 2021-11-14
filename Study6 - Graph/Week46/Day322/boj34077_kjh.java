//// 문제풀이 실패 : 시간초과를 해결할 방법을 찾지 못함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;


class Main {
  static Map<String, Boolean> isElement = new HashMap<>();

  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    initElements();

    for (int i = 0; i < T; i++) {
      String word = Input.nextLine();
      sb.append(isElementic(word) ? "YES" : "NO").append('\n');
    }

    System.out.print(sb);
  }

  static boolean isElementic(String word) {
    return isElementic(word, 0);
  }

  static boolean isElementic(String word, int startIndex) {
    if (startIndex >= word.length()) {
      return true;
    }

    boolean result = false;

    String oneLetter = word.substring(startIndex, startIndex + 1);
    if (isElement.containsKey(oneLetter)) {
      result |= isElementic(word, startIndex + 1);
    }

    if (startIndex + 1 == word.length()) {
      return result;
    }

    String twoLetters = word.substring(startIndex, startIndex + 2);
    if (isElement.containsKey(twoLetters)) {
      result |= isElementic(word, startIndex + 2);
    }

    return result;
  }

  static void initElements() {
    String[] elements = {
        "h", "b", "c", "n", "o", "f", "p", "s", "k", "v", "y", "i", "w", "u",
        "ba", "ca" , "ga", "la", "na", "pa", "ra", "ta", "db", "nb", "pb", "rb", "sb", "tb", "yb", "ac",
        "sc", "tc", "cd", "gd", "md", "nd", "pd", "be", "ce", "fe", "ge", "he", "ne", "re", "se", "te",
        "xe", "cf", "hf", "rf", "ag", "hg", "mg", "rg", "sg", "bh", "rh", "th", "bi", "li", "ni", "si",
        "ti", "bk", "al", "cl", "fl", "tl", "am", "cm", "fm", "pm", "sm", "tm", "cn", "in", "mn", "rn",
        "sn", "zn", "co", "ho", "mo", "no", "po", "np", "ar", "br", "cr", "er", "fr", "ir", "kr", "lr",
        "pr", "sr", "zr", "as", "cs", "ds", "es", "hs", "os", "at", "mt", "pt", "au", "cu", "eu", "lu",
        "pu", "ru", "lv", "dy"
    };

    for (String element : elements) {
      isElement.put(element, true);
    }
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}