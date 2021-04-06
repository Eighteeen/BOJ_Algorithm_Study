import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Main {
  public static void main(String[] args) throws Exception {
    final String EXPRESSION = Input.nextLine();
    Expression expression = new Expression(EXPRESSION);
    
    expression.makeCombinations();
    expression.printCombinations();
  }
}

class Expression {
  private String expression;
  private List<Bracket> havingBrackets;

  private List<List<Bracket>> combinationsOfExcepting;

  public Expression(String expression) {
    this.expression = expression;

    havingBrackets = new ArrayList<>();
    initializeHavingBrackets();
    sortBrackets();
    
    combinationsOfExcepting = new ArrayList<>();
  }

  private void initializeHavingBrackets() {
    Stack<Integer> openBrackets = new Stack<>();

    for (int i = 0; i < expression.length(); i++) {
      char letter = expression.charAt(i);

      if (letter == '(') {
        openBrackets.add(i);
      } else if (letter == ')') {
        havingBrackets.add(new Bracket(openBrackets.pop(), i));
      }
    }
  }

  private void sortBrackets() {
    List<Bracket> sortedBrackets = new ArrayList<>();

    for (int i = 0; i < expression.length(); i++) {
      char letter = expression.charAt(i);
      if (letter != '(' && letter != ')') continue; 

      for (Bracket bracket : havingBrackets) {
        if (bracket.includes(i) && !sortedBrackets.contains(bracket)) {
          sortedBrackets.add(0, bracket);
          break;
        }
      }
    }

    havingBrackets = sortedBrackets;
  }

  public void makeCombinations() {
    for (int i = 1; i <= havingBrackets.size(); i++) {
      makeCombinations(i, new ArrayList<Bracket>(), 0);
    }
  }

  public void makeCombinations(int toExcept, List<Bracket> excepted, int exceptFrom) {
    if (toExcept == excepted.size()) {
      combinationsOfExcepting.add(excepted);
      return;
    }

    int leftExcepting = toExcept - excepted.size();
    for (int i = exceptFrom; i <= havingBrackets.size() - leftExcepting; i++) {
      Bracket toExceptBracket = havingBrackets.get(i);

      excepted.add(toExceptBracket);
      makeCombinations(toExcept - 1, excepted, i + 1);
      excepted.remove(toExceptBracket);
    }
  }

  public void printCombinations() {
    StringBuilder sb = new StringBuilder();

    combinationsOfExcepting.stream()
      .forEach(combination -> sb.append(combination).append('\n'));

    System.out.print(sb);
  }

}

class Bracket {
  private int openIndex;
  private int closeIndex;

  public Bracket(int openIndex, int closeIndex) {
    this.openIndex = openIndex;
    this.closeIndex = closeIndex;
  }

  public int getOpenIndex() {
    return openIndex;
  }

  public int getCloseIndex() {
    return closeIndex;
  }

  public boolean includes(int index) {
    return openIndex == index || closeIndex == index;
  }

  @Override
  public boolean equals(Object o) {
    return ((Bracket) o).getOpenIndex() == openIndex && ((Bracket) o).getCloseIndex() == closeIndex;
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