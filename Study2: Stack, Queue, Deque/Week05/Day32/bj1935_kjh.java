import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
  //// 깔꼼합니다!!!! : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    String expression = Input.nextLine();

    double[] numbers = new double[N];
    for(int i = 0; i < N; i++) {
      numbers[i] = Input.nextDouble();
    }

    Stack<Double> stack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      char currentChar = expression.charAt(i);
      
      if (currentChar >= 'A' && currentChar <= 'Z') {
        stack.push(numbers[currentChar - 'A']);
        continue;
      }
      
      double y = stack.pop();
      double x = stack.pop();
      stack.push(calculate(currentChar, x, y));
    }

    System.out.printf("%.2f", stack.pop());
  }

  public static double calculate(char operation, double x, double y) {
    //// !!!! 저는 case마다 바로 return할 생각을 못 했네요. 코드도 짧게 바로바로 읽히고 좋네요. 피드백 반영시에 이것도 반영해보려고요! 굿굿
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
