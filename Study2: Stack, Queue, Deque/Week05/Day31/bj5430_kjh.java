import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Collector;
//// 클래스 활용 좋습니다 깔끔해요! : 22!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    
    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      char[] functions = Input.nextLine().toCharArray();
      int arraySize = Input.nextInt();
      int[] array = parseArray(Input.nextLine());

      AC ac = new AC(array);
      ac.runFunctions(functions);

      sb.append(ac.toString())
        .append('\n');
    }

    System.out.print(sb);
  }

  public static int[] parseArray(String unparsedArray) {
    if (unparsedArray.equals("[]")) return null;

    String[] parsedArray = unparsedArray.replace("[", "")
      .replace("]", "")
      .split(",");

    return Arrays.stream(parsedArray)
      .mapToInt(Integer::parseInt)
      .toArray();
  }
}

//// 함수활용이 정말 좋네요 선영이가 이런식으로 만들었을까? 하는 느낌으로 본 것 같아요 (물론 자바로 구현한 거긴 하지만요) 굿굿
class AC {
  private Deque<Integer> deque;
  private boolean isError = false;
  private boolean isReversed = false;

  public AC(int[] array) {
    deque = new ArrayDeque<Integer>();
    
    if (array == null) return;
    Arrays.stream(array)
      .forEach(item -> deque.offerLast(item));
  }
  
  public void runFunctions(char[] functions) {
    for (char function : functions) {
      runFunction(function);
    }
  }

  private void runFunction(char function) {
    if (function == 'R') {
      reverseOrder();
    }
    else if (function == 'D') {
      deleteFirst();
    }
  }
  
  private void reverseOrder() {
    isReversed = !isReversed;
  }

  private void deleteFirst() {
    if (checkEmpty()) return;

    if (isReversed) {
      deque.pollLast();
    } else {
      deque.pollFirst();
    }
  }

  private boolean checkEmpty() {
    if (deque.size() == 0) {
      isError = true;
    }
    return isError;
  }
//// 오버라이딩까지 활용하시다니 대단해요,,,! : 22 멋있슴돠!
  @Override
  public String toString() {
    if (isError) return "error";

    if (isReversed) {
      deque = deque.stream().collect(
        Collector.of(
          ArrayDeque::new, // supplier: 결과가 담길 컨테이너
          (reversed, item) -> reversed.offerFirst(item), // accumulator: reversed에 요소들을 누적
          (part, combined) -> { combined.addAll(part); return combined; } // combiner: 병렬 처리 시 동시에 처리된 것들을 combined에 모음 (현 코드에서는 병렬 스트림을 사용하지 않아서 동작하지 않음)
        )
      );
    }

    //// Collectors.joining 사용으로 어떻게 출력되는지 한번에 보여서 정말 좋네요 많이 배워갑니다!
    return deque.stream()
      .map(String::valueOf)
      .collect(Collectors.joining(",", "[", "]"));
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
