import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

//// triangle 배열을 리스트형태로 만들거나(쉽지만 메모리가 많이 쓰이는 방법),
//// 배열의 max(input이 10일 때)값에서 각 input의 시작과 끝지점을 계산하여 활용하는 등
//// 전에 만들었던 삼각형을 활용할 수 있을 것 같은데 이미 만들었던 것도 다시 만들기 시작하는 점이 아쉽습니다.
  //// makeTriangle 함수에서는 이미 글자가 있나 확인하는 식으로 로직을 추가하시면 이미 만든 삼각형을 재사용할 수 있을 것 같습니다.
//// => 이전 depth의 위치를 찾아내 복사하는 것
  //// => 이전 depth를 따로 저장하지 않기
    //// 이전 depth의 것이 다음에는 다른 위치에 존재해서
    //// 하나의 배열로만 하는건 아예 불가능합니다. 원본이 중간에 덮어씌워질 수 밖에 없거든요
  //// => 이전 depth 배열만 저장하기
    //// 이건 효율성에 큰 차이가 없습니다
  //// => 모든 depth에 대해 저장하기
    //// 이래야 효율성에 유의미한 차이가 생기는데
    //// 이렇게하면 이전 depth에 있는 것을 세번 복사하는 형태입니다
    //// https://github.com/deepredk/BCU_Algorithm_Study/blob/main/Week08/Day54/bj16505_kjh.java
    //// 요것처럼 하되 이전 결과들을 모두 저장만 한다는건데
    //// void copyArray(int toDepth, int fromStartX, int fromStartY, int toStartX, int toStartY)
    //// 그럼 이런 기괴한 형태의 copyArray가 필요하게 됩니다
    //// 비록 비효율적이지만, 훨씬 가독성 좋은 현재 형태가 좋다고 생각해서 이대로 두겠습니다!
class Main {
  private static char[][] triangle;

  public static void main(String[] args) throws Exception {
    List<StringBuilder> triangles = new ArrayList<>();

    String input;
    while (!( input = Input.nextLine() ).equals("0")) {
      int fractalDepth = Integer.parseInt(input);

      int rowSize = (int) Math.pow(2, fractalDepth);
      int colSize = rowSize * 2;
      triangle = new char[rowSize][colSize];

      makeTriangle(fractalDepth, 0, 0);
      triangles.add(drawTriangle(triangle));
    }

    System.out.print(
      triangles.stream()
        .map(StringBuilder::toString)
        .collect(joining("\n"))
    );
  }

  static void makeTriangle(int depth, int row, int col) {
    if (depth == 1) {
      triangle[row][col+1] = '/';
      triangle[row][col+2] = '\\';
      triangle[row+1][col] = '/';
      triangle[row+1][col+1] = '_';
      triangle[row+1][col+2] = '_';
      triangle[row+1][col+3] = '\\';
      return;
    }

    int lowDepthSize = (int) Math.pow(2, depth - 1);

    //// 재귀함수를 멋지게 활용하셨네요
    makeTriangle(depth - 1, row, col + lowDepthSize);
    makeTriangle(depth - 1, row + lowDepthSize, col);
    makeTriangle(depth - 1, row + lowDepthSize, col + lowDepthSize * 2);
  }

  static StringBuilder drawTriangle(char[][] triangle) {
    StringBuilder sb = new StringBuilder();

    int rowSize = triangle.length;
    int colSize = triangle[0].length;

    for (int i = 0; i < rowSize; i++) {
      int untilLastChar = colSize - rowSize + i + 1;
      for (int j = 0; j < untilLastChar; j++) {
        sb.append(triangle[i][j] == '\u0000' ? ' ' : triangle[i][j]);
      }
      sb.append('\n');
    }

    return sb;
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