import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

//// 무난하게 짜신 것 같습니다.
class Main {
  public static void main(String[] args) throws Exception {
    final String BRACKETS = Input.nextLine();

    Brackets brackets = new Brackets(BRACKETS);
    System.out.print(brackets.calcScore());
  }
}

class Brackets {
  private String brackets;
  private Stack<Character> stack;
  private int idx;
  private boolean isError;

  public Brackets(String brackets) {
    this.brackets = brackets;
    stack = new Stack<>();
    idx = 0;
  }
  
  public int calcScore() {
    int totalScore = 0;
    while (idx < brackets.length()) {
      totalScore += calcBundleOfBrackets();
    }
    
    if (!stack.isEmpty() || isError) return 0;
    return totalScore;
  }

  private int calcBundleOfBrackets() {
    if (idx >= brackets.length() || isError) return 0;

    char current = brackets.charAt(idx);
    if (isClosingBracket(current)) {
      if (stack.isEmpty() || !isPairOfBracket(stack.peek(), current)) {
        isError = true;
        idx = brackets.length();
        return 0;
      }
      idx++;
      stack.pop();
      return 0;
    }

    int singleScore = calcBracket(current);
    if (idx + 1 < brackets.length() && isPairOfBracket(current, brackets.charAt(idx + 1))) {
      idx += 2;
      return singleScore + calcBundleOfBrackets();
    }

    stack.push(current);

    int currentSize = stack.size();
    int score = 0;
    idx++;
    while (stack.size() >= currentSize && idx < brackets.length()) {
      int calc = calcBundleOfBrackets();
      score += singleScore * calc;
    }

    return score;
  }

  private boolean isClosingBracket(char bracket) {
    return (bracket == ')' || bracket == ']');
  }

  private boolean isPairOfBracket(char opening, char closing) {
    return (opening == '(' && closing == ')') || (opening == '[' && closing == ']');
  }

  private int calcBracket(char opening) {
    return (opening == '(') ? 2 : 3;
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