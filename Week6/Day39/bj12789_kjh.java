import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

//// 전체적으로 무난하게 잘 짜신 것 같습니다!
class Main {
  public static void main(String[] args) throws Exception {
    Stack<Integer> waitingStack = new Stack<>();
    
    final int N = Input.nextInt();
    int currentOrder = 1;

    for (int i = 0; i < N; i++) {
      int frontStudent = Input.nextInt();
      if (frontStudent == currentOrder) {
        currentOrder++;
        continue;
      }

      //// !stack.empty() 혹은 !stack.isEmpty() 가 좀 더 코드도 짧고 직관적이라고 생각하는데 stack.size() > 0 로 하신 이유가 있을까요?
      while (waitingStack.size() > 0 && waitingStack.peek() == currentOrder) {
        waitingStack.pop();
        currentOrder++;
      }
      waitingStack.push(frontStudent);
    }
    
    while (waitingStack.size() > 0) {
      if (waitingStack.pop() != currentOrder) {
        System.out.print("Sad");
        return;
      }
      currentOrder++;
    }
    
    System.out.print("Nice");
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