import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 전체적으로 깔끔하지만 2차원 배열 사용으로 루프를 많이 도는 게 아쉽습니다
//// 지금과 비슷한 구현방식으로는 String 일차원 배열로도 구현이 가능할 것 같습니다
  //// 예를 들어 copyArray()에서 to[0][toStartY - 1] == '*'이면 blank를 채운다는 식으로 변형이 가능할 것 같습니다!
//// => 이 피드백은 재귀함수를 좀 더 재귀함수답게 바꾸느라 적용할 수 없게 되었습니다 ㅠㅠ
//// => 예시까지 들어주시는 상세한 피드백 감사합니다!
class Main {
  static boolean[][] stars;

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    int twoPoweredByN = (int) Math.pow(2, N);
    stars = new boolean[twoPoweredByN][twoPoweredByN];

    makeStarPattern(twoPoweredByN, 0, 0);
    
    for (int i = 0; i < twoPoweredByN; i++) {
      for (int j = 0; j < (twoPoweredByN - i); j++) {
        //// 오호 char형에서는 null 대신 '\u0000' 이라는 값을 사용하군요 배워갑니다
        sb.append(stars[i][j] ? '*' : ' ');
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  //// 쌓인 걸 사용하는 방법이 있을 것 같은데 모두 하나하나씩 카피하는 게 아쉽습니다
    //// => 피드백 감사합니다! 카피하는 부분은 저도 아쉬웠었어요. 다른 분의 코드를 참고하고 이해해서 새로 짜봤습니다 ㅎㅎ
    //// => 그럼에도 2위네요.. bj16505_wsb.java 처럼 짜는게 확실히 효율면에서 훨씬 좋은 것 같습니다.
  static void makeStarPattern(int size, int rowToBeMade, int colToBeMade) {
    if (size == 1) {
      stars[rowToBeMade][colToBeMade] = true;
      return;
    }

    int half = size / 2;
    makeStarPattern(half, rowToBeMade, colToBeMade);
    makeStarPattern(half, rowToBeMade, colToBeMade + half);
    makeStarPattern(half, rowToBeMade + half, colToBeMade);
  }
  
  //// 물론 쓰신 문장이 의미가 잘 통하고 꼭 피드백처럼 할 필요는 없지만
  //// toY -= from[0].length 처리를 한다면 굳이 변수를 복제하지 않아도 될 것 같습니다
    //// => (변수를 읽는 연산 + 대입하는 연산) vs (배열의 길이를 알아내는 연산 + 빼면서 대입하는 연산)
    //// => 저 둘 사이에 효율성면으로 그렇게 큰 차이는 없지 않을까요??
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

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
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