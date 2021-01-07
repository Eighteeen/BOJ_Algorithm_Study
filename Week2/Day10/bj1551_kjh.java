import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
//// 무난하고 깔끔하게 짜신 것 같아요

// Java8의 Stream을 적극적으로 사용하였습니다.
// 관심있는 분은 https://futurecreator.github.io/2018/08/26/java-8-streams/ 참조해주세요
// C#에서는 LINQ와 비슷한 것 같아요!
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int K = Input.nextInt();

    String[] sequencesArray = Input.nextLine().split(",");
    List<Integer> sequences;
    sequences = Arrays.stream(sequencesArray) // Stream을 사용할 준비
      .map(Integer::parseInt) // 각 요소에 대해 int로 형 변환
      .collect(Collectors.toList()); // 요소들을 모아 List로 만듬

    for (int i = 0; i < K; i++) {
      int lastIndex = sequences.size() - 1;
      for (int j = 0; j < lastIndex; j++) {
        Integer convertedValue = sequences.get(j + 1) - sequences.get(j);
        sequences.set(j, convertedValue);
      }
      sequences.remove(lastIndex);
    }

    //// 허걱 한번에 출력하는 방법 엄청 찾았는데..! 완전 배워갑니다!!
    String result = sequences.stream() // Stream을 사용할 준비
      .map(String::valueOf) // 각 요소에 대해 String으로 형 변환
      .collect(Collectors.joining(",")); // 요소 사이에 ","를 붙여 하나의 문자열로 합침
    System.out.print(result);
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