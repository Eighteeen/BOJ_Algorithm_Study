import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Collections;

//// 전체적으로 무난하게 짤 짜신 것 같습니다! :22 깔끔합니당!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int TEST_CASES = Input.nextInt();

    for (int i = 0; i < TEST_CASES; i++) {
      int numberOfDocs = Input.nextInt();
      int targetDoc = Input.nextInt();
      String priority = Input.nextLine();
      PrinterQueue printerQueue = new PrinterQueue(numberOfDocs, priority);

      for (int nth = 1; nth <= numberOfDocs; nth++) {
        int printedDoc = printerQueue.print();
        if (printedDoc == targetDoc) {
          sb.append(nth)
            .append('\n');
          break;
        }
      }
    }
    
    System.out.print(sb);
  }
}
//// LinkedList 활용 신기해요!
class PrinterQueue {
  private Queue<Integer> queue;
  private Queue<Integer> priorities;

  public PrinterQueue(int numberOfDocs, String strPriorities) {
    //// queue에 index를 사용해서 직관적으로 알 수 있는 게 좋네요!
    queue = new LinkedList<>();
    IntStream.range(0, numberOfDocs)
      .boxed()
      .forEach(docNumber -> queue.add(docNumber));

    priorities = new LinkedList<>();
    Arrays.stream(strPriorities.split(" "))
      .mapToInt(Integer::parseInt)
      .forEach(priority -> priorities.add(priority));
  }

  public int print() {
    int highestPriority = getHighestPriority();
    while (queue.size() > 0) {
      //// 돌릴 때 마다가 아니라 priority == highestPriority 일 때마다 getHighestPriority()를 해주면 연산횟수가 줄어들 것 같아요
      //// => 오~ 전혀 생각 못했던 부분이에요 날카로운 피드백 고마워요!
      int dequeued = queue.remove();
      int priority = priorities.remove();

      if (priority == highestPriority) {
        highestPriority = getHighestPriority();
        return dequeued;
      }
      
      queue.add(dequeued);
      priorities.add(priority);
    }

    return -1;
  }

  public int getHighestPriority() {
    return priorities.stream().max(Integer::compareTo).orElse(-1);
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
