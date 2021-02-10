import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

//// 무난하고 깔끔합니다 : 22 깔끔합니다~
class Main {
  public static void main(String[] args) throws Exception {
    final String expression = Input.nextLine();
    Stack<Integer> numbers = new Stack<>();

    for (char oper : expression.toCharArray()) {
      if (oper >= '0' && oper <= '9') {
        numbers.push(oper - '0');
        continue;
      }

      int y = numbers.pop();
      int x = numbers.pop();
      numbers.push(calculate(oper, x, y));
    }
    
    System.out.print(numbers.pop());
  }

  public static int calculate(char operation, int x, int y) {
    //// operation 변수가 어디로 가버렸나
    //// => ㅎㅎ! ㅋㅋ!
    switch (operation) {
      case '+':
        return x + y;
      case '-':
        return x - y;
      case '*':
        return x * y;
      case '/':
        return x / y;
    }

    return -1;
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
