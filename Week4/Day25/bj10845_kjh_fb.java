import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
  ////깔끔합니다!!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    CircularQueue queue = new CircularQueue(10000);
    for (int i = 0; i < N; i++) {
      String methodName = Input.next();

      if (methodName.equals("push")) {
        queue.push(Input.nextInt());
        continue;
      }
      Method method = Class.forName("CircularQueue").getMethod(methodName);
      int returnValue = (int) method.invoke(queue);
      
      sb.append(returnValue).append('\n');
    }
    
    System.out.print(sb);
  }
}

class CircularQueue {
  //// 밑에 메소드 코드를 보고 도대체 나머지를 왜 구할까 봤는데 명령이 10000개가 넘어도 순환되게 하신 거군요 활용성이 좋네요.
  //// -> 넹넹 이 문제에 꼭 필요한건 아니였지만 '원형 큐'를 한번 구현해보고 싶었어요

  //// 근데 front와 rear 초기화를 -1로 하면 생성자에서 elementsSize에 + 1을 안 해도 되지 않을까요?
  //// -> 그렇게 하면 꽉 찬 경우와 텅 빈 경우를 구분할 수 없습니다.

  //// -> front, rear = -1에 int[10000]으로 구현했을때
    //// push와 pop을 각각 한번씩하면 front=0, rear=0이 됩니다.
    //// 거기에 push를 10000번 해도 똑같이 front=0, rear=0입니다.
    //// 전자는 0개의 원소를 가졌고, 후자는 10000개의 원소를 가졌지만 구분할 수 없습니다
  
  //// -> 위에서 말했듯이 이 문제에서는 꼭 필요한 구현은 아니긴 합니다.
  //// -> 최대 10000개의 push만 주어질 수 있으므로 순환할 일이 없어 원형 큐도 필요없었고
  //// -> 10001개의 push가 될 일도 없으니 포화상태를 확인할 필요도 없었습니다
  //// -> 그냥 원형 큐 구현해보고 싶어서 한겁니다 ㅎㅎ;

  public int[] elements;
  private int elementsSize;
  private int front = 0;
  private int rear = 0;

  public CircularQueue(int elementsSize) {
    this.elementsSize = elementsSize + 1;
    elements = new int[elementsSize + 1];
  }

  public void push(int number) {
    rear = (rear + 1) % elementsSize;
    elements[rear] = number;
  }
  
  public int pop() {
    if (empty() == 1) return -1;
    front = (front + 1) % elementsSize;
    return elements[front];
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
