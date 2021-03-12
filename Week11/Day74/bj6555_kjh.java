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