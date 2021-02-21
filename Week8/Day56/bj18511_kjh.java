import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;
import static java.util.stream.Collectors.toList;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int K = Input.nextInt();
    String[] boundDigits = Input.nextLine().split(" ");

    int digits = String.valueOf(N).length();
    TreeSet<Integer> combinationsOneLessDigit = makeCombinations(boundDigits, digits - 1);
    TreeSet<Integer> combinations = mixDigit(combinationsOneLessDigit, boundDigits);
    combinations.addAll(combinationsOneLessDigit);

    for (Integer combination : combinations.descendingSet()) {
      if (combination <= N) {
        System.out.print(combination);
        return;
      }
    }
  }

  static TreeSet<Integer> makeCombinations(String[] boundDigits, int digits) {
    if (digits == 1) {
      TreeSet<Integer> combinations = new TreeSet<>();
      for (String boundDigit : boundDigits) {
        combinations.add(Integer.parseInt(boundDigit));
      }
      return combinations;
    }

    TreeSet<Integer> combinationsOneLessDigit = makeCombinations(boundDigits, digits - 1);

    return mixDigit(combinationsOneLessDigit, boundDigits);
  }

  static TreeSet<Integer> mixDigit(TreeSet<Integer> oldCombinations, String[] boundDigits) {
    TreeSet<Integer> combinations = new TreeSet<>();
    for (String boundDigit : boundDigits) {  
      for (Integer oldCombination : oldCombinations) {
        combinations.add(Integer.parseInt(boundDigit + oldCombination));
      }
    }
    return combinations;
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