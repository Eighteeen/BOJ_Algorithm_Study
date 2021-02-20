import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 전체적으로 깔끔하지만 2차원 배열 사용으로 루프를 많이 도는 게 아쉽습니다
//// 지금과 비슷한 구현방식으로는 String 일차원 배열로도 구현이 가능할 것 같습니다
  //// 예를 들어 copyArray()에서 to[0][toStartY - 1] == '*'이면 blank를 채운다는 식으로 변형이 가능할 것 같습니다!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();
    char[][] stars = makeStars(N);

    int square = (int) Math.pow(2, N);
    for (int i = 0; i < square; i++) {
      for (int j = 0; j < (square - i); j++) {
        //// 오호 char형에서는 null 대신 '\u0000' 이라는 값을 사용하군요 배워갑니다
        sb.append(stars[i][j] == '\u0000' ? ' ' : stars[i][j]);
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  static char[][] makeStars(int size) {
    if (size == 0) {
      char[][] stars = {{'*'}};
      return stars;
    }

    int square = (int) Math.pow(2, size);
    char[][] stars = new char[square][square];

    char[][] previousStars = makeStars(size - 1);
    int previousSquare = (int) Math.pow(2, size - 1);
    //// 쌓인 걸 사용하는 방법이 있을 것 같은데 모두 하나하나씩 카피하는 게 아쉽습니다
    copyArray(previousStars, stars, 0, 0);
    copyArray(previousStars, stars, previousSquare, 0);
    copyArray(previousStars, stars, 0, previousSquare);

    return stars;
  }
  
  static void copyArray(char[][] from, char[][] to, int toStartX, int toStartY) {
    int toX = toStartX, toY = toStartY;
    for (int i = 0; i < from.length; i++) {
      for (int j = 0; j < from[0].length; j++) {
        to[toX][toY++] = from[i][j];
      }
      toX++;
      //// 물론 쓰신 문장이 의미가 잘 통하고 꼭 피드백처럼 할 필요는 없지만
      //// toY -= from[0].length 처리를 한다면 굳이 변수를 복제하지 않아도 될 것 같습니다
      toY = toStartY;
    }
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