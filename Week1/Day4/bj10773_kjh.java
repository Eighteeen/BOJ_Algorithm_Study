import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

//// 스택 활용 깔끔하네요
class Main {
  public static void main(String[] args) throws Exception {
    final int K = Input.nextInt();
    Stack<Integer> numbers = new Stack<>();
    ////Continue활용 좋네요
    for(int i = 0; i < K; i++) {
      int number = Input.nextInt();
      if (number == 0) {
        numbers.pop();
        continue;
      }
      numbers.add(number);
    }
      ////int도 이렇게 더해서 사용할수 있군요 배워갑니다.
    int sum = 0;
    while( !numbers.empty() ) {
      sum += numbers.peek();
      numbers.pop();
    }

    System.out.print(sum);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}
