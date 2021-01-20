import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    // Reflection 사용을 위한 준비
    Class cls = Class.forName("Stack"); // Class 특정
    Constructor constructor = cls.getConstructor(int.class); // 생성자 특정
    Stack stack = (Stack) constructor.newInstance(10000); // 생성자로 객체 생성

    for (int i = 0; i < N; i++) {
      String methodName = Input.next();

      if (methodName.equals("push")) {
        stack.push(Input.nextInt());
        continue;
      }
      // Reflection을 활용해 메소드 이름으로 메소드 호출
      Method method = cls.getMethod(methodName); // 메소드 이름으로 메소드 특정
      int returnValue = (int) method.invoke(stack); // stack 객체에게 메소드를 실행하게 함
      
      sb.append(returnValue).append('\n');
    }
    
    System.out.print(sb);
  }
}

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