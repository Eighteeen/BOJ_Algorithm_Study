import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
    //// list에 저장해두고 하나씩 지우는 것도 좋은 방법인거 같네요!!
class Main {
  public static void main(String[] args) throws Exception {
    int[] oneToThirty = IntStream.range(1, 31).toArray();
    List<Integer> studentNumbers = Arrays.stream(oneToThirty).boxed().collect(Collectors.toList());

    //// 28명만 과제를 제출한 경우에만 미제출자를 구할 수 있는 코드라서 아쉬워요
    //// => 문제에서 입력 개수가 정해져있고, 활용 가능하게 하려면 while문을 써야하는 점이 그렇게 매력적인 것 같지 않아서 유지하겠습니다!
    for(int i = 0; i < 28; i++) {
      Integer studentNumber = Input.nextInt();
      studentNumbers.remove(studentNumber);
    }

    //// 2명만 미제출자일 시에만 정답을 구할 수 있는 코드라서 아쉬워요
    //// => 위와 마찬가지로 유지하겠습니다!
    StringBuilder sb = new StringBuilder();
    sb.append(studentNumbers.get(0).toString())
      .append('\n')
      .append(studentNumbers.get(1).toString());
    
    System.out.print(sb);
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
