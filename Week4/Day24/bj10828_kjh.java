import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    Stack stack = new Stack(10000);
    for (int i = 0; i < N; i++) {
      String methodName = Input.next();

      if (methodName.equals("push")) {
        stack.push(Input.nextInt());
        continue;
      }
      //// 와우 이런 기능이 있을 줄은 몰랐어요!! 다음에 써먹어봐야겠어요 배워갑니다!!
      // Reflection을 활용해 메소드 이름으로 메소드 호출
      Method method = Class.forName("Stack").getMethod(methodName); // 메소드 이름으로 메소드 특정
      int returnValue = (int) method.invoke(stack); // stack 객체에게 메소드를 실행하게 함
      
      sb.append(returnValue).append('\n');
    }
    
    System.out.print(sb);
  }
}

//// 아주 깔끔하게 잘 구현하신 거 같습니다
class Stack {
  public int[] elements;
  private int index = -1;

  public Stack(int size) {
    elements = new int[size];
  }

  public void push(int number) {
    elements[++index] = number;
  }
  
  public int pop() {
    if (empty() == 1) return -1;
    return elements[index--];
  }

  public int size() {
    return index + 1;
  }

  public int empty() {
    return (index == -1) ? (1) : (0);
  }

  public int top() {
    if (empty() == 1) return -1;
    return elements[index];
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