import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.stream.IntStream;
//// 코드가 아주 깔꼼해요 클래스효과인가봐요 굿굿! : 22 진짜 깔-끔하고 효율도 좋네용
//// -> 써보쉴?
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int NUMBER_OF_PEOPLE = Input.nextInt();
    final int REMOVE_NTH_PERSON = Input.nextInt();
    final int REVERSE_EVERY_NTH = Input.nextInt();
    
    Josephus josephus = new Josephus(NUMBER_OF_PEOPLE);

    for (int i = 1; i <= NUMBER_OF_PEOPLE; i++) {
      sb.append(josephus.popNth(REMOVE_NTH_PERSON))
        .append('\n');
      
      boolean needReverse = (i % REVERSE_EVERY_NTH) == 0;
      if (needReverse) {
        josephus.reverse();
      }
    }

    System.out.print(sb);
  }
}

class Josephus {
  private Deque<Integer> sequence = new ArrayDeque<>();
  
  private boolean isNaturalOrder = true;
  private int numberOfPeople;

  public Josephus(int numberOfPeople) {
    IntStream.range(1, numberOfPeople + 1)
      .boxed()
      .forEach(item -> sequence.add(item));
    
    this.numberOfPeople = numberOfPeople;
  }

  public int popNth(int n) {
    for (int i = 0; i < (n - 1); i++) {
      popAndPush();
    }

    return pop();
  }
  
  public void reverse() {
    isNaturalOrder = !isNaturalOrder;
  }

  private void popAndPush() {
    if (isNaturalOrder) {
      sequence.offerLast(sequence.pollFirst());
      return;
    }

    sequence.offerFirst(sequence.pollLast()); 
  }

  private int pop() {
    if (isNaturalOrder) {
      return sequence.pollFirst();
    }
    
    return sequence.pollLast();
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
