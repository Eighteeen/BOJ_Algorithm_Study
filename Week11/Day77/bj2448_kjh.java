import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] triangle;

  public static void main(String[] args) throws Exception {
    int height = Input.nextInt();
    int width = height * 2 - 1;
    
    triangle = new boolean[height][width];

    makeTriangle(height, 0, 0);
    printTriangle(triangle);
  }

  static void makeTriangle(int height, int row, int col) {
    if (height == 3) {
      makeSmallestTriangle(row, col);
      return;
    }

    makeTriangle(height / 2, row, col + (height / 2));
    makeTriangle(height / 2, (row + height / 2), col);
    makeTriangle(height / 2, (row + height / 2), col + height);
  }

  static void makeSmallestTriangle(int row, int col) {
    triangle[row][col+2] = true;
    triangle[row+1][col+1] = true;
    triangle[row+1][col+3] = true;
    for (int i = 0; i < 5; i++) {
      triangle[row+2][col+i] = true;
    }
  }

  static void printTriangle(boolean[][] triangle) {
    StringBuilder sb = new StringBuilder();

    int height = triangle.length;
    int width = triangle[0].length;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sb.append(triangle[i][j] ? '*' : ' ');
      }
      sb.append('\n');
    }

    System.out.print(sb);
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