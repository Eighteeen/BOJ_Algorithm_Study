import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    Deque deque = new Deque(10000);
    for (int i = 0; i < N; i++) {
      String methodName = Input.next();

      if (methodName.equals("push_back")) {
        deque.push_back(Input.nextInt());
        continue;
      } else if (methodName.equals("push_front")) {
        deque.push_front(Input.nextInt());
        continue;
      }

      Method method = Class.forName("Deque").getMethod(methodName);
      int returnValue = (int) method.invoke(deque);
      
      sb.append(returnValue).append('\n');
    }
    
    System.out.print(sb);
  }
}

class Deque {
  public int[] elements;
  private int elementsSize;
  private int front = 0;
  private int rear = 0;

  public Deque(int elementsSize) {
    this.elementsSize = elementsSize + 1;
    elements = new int[elementsSize + 1];
  }

  public void push_front(int number) {
    elements[front] = number;
    front = (front - 1 + elementsSize) % elementsSize;
  }
  
  public int pop_front() {
    if (empty() == 1) return -1;
    front = (front + 1) % elementsSize;
    return elements[front];
  }

  public void push_back(int number) {
    rear = (rear + 1) % elementsSize; 
    elements[rear] = number;
  }
  
  public int pop_back() {
    if (empty() == 1) return -1;
    int returnValue = elements[rear];
    rear = (rear - 1 + elementsSize) % elementsSize;
    return returnValue;
  }

  public int size() {
    if (front <= rear) return rear - front;

    int countFromZeroIdxToRear = rear + 1;
    int countFromFrontToLastIdx = elementsSize - front - 1;
    return countFromZeroIdxToRear + countFromFrontToLastIdx;
  }

  public int empty() {
    return (front == rear) ? (1) : (0);
  }

  public int front() {
    if (empty() == 1) return -1;

    int frontIndex = (front + 1) % elementsSize;
    return elements[frontIndex];
  }
  
  public int back() {
    if (empty() == 1) return -1;
    
    return elements[rear];
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