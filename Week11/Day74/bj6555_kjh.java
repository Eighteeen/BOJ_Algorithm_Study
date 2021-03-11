import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

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