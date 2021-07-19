//// 문제풀이 실패 : 메모리 초과 해결방안을 찾지 못함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Comparable;
import java.util.Comparator;

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

  private Set<String> combinationsOfRemovingBrackets;

  public Expression(String expression) {
    this.expression = expression;

    havingBrackets = new ArrayList<>();
    initializeHavingBrackets();
    Collections.sort(havingBrackets, Comparator.reverseOrder());
    
    combinationsOfRemovingBrackets = new TreeSet<String>();
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

  public void makeCombinations() {
    for (int i = 1; i <= havingBrackets.size(); i++) {
      makeCombinationsOfRemovingBrackets(i, new ArrayList<Bracket>());
    }
  }

  public void printCombinations() {
    StringBuilder sb = new StringBuilder();

    combinationsOfRemovingBrackets.stream()
      .forEach(combination -> sb.append(combination).append('\n'));

    System.out.print(sb);
  }

  private void makeCombinationsOfRemovingBrackets(int toExcept, List<Bracket> bracketsToExcept) {
    if (toExcept == bracketsToExcept.size()) {
      combinationsOfRemovingBrackets.add(exceptBrackets(bracketsToExcept));
      return;
    }

    for (int i = 0; i < havingBrackets.size(); i++) {
      Bracket toExceptBracket = havingBrackets.get(i);
      if (bracketsToExcept.contains(toExceptBracket)) continue;

      bracketsToExcept.add(toExceptBracket);
      makeCombinationsOfRemovingBrackets(toExcept, bracketsToExcept);
      bracketsToExcept.remove(bracketsToExcept.size() - 1);
    }
  } 

  private String exceptBrackets(List<Bracket> brackets) {
    List<Integer> exceptIndicies = new ArrayList<>();

    for (Bracket bracket : brackets) {
      exceptIndicies.add(bracket.getStartIndex());
      exceptIndicies.add(bracket.getEndIndex());
    }

    StringBuilder exceptedExpression = new StringBuilder();
    String expression = this.expression;
    for (int i = 0; i < expression.length(); i++) {
      if (exceptIndicies.contains(i)) continue;
      exceptedExpression.append(expression.charAt(i));
    }

    return exceptedExpression.toString();
  }
}

class Bracket implements Comparable<Bracket> {
  private int openIndex;
  private int closeIndex;

  public Bracket(int openIndex, int closeIndex) {
    this.openIndex = openIndex;
    this.closeIndex = closeIndex;
  }

  public int getStartIndex() {
    return openIndex;
  }

  public int getEndIndex() {
    return closeIndex;
  }

  public int sumIndicies() {
    return openIndex + closeIndex;
  }

  @Override
  public int compareTo(Bracket bracket) {
    return this.sumIndicies() - bracket.sumIndicies();
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