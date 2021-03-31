import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 무난 깔끔합니다.
class Main {
  static boolean[][] pixels;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    pixels = new boolean[N][N];
    
    for (int i = 0; i < N; i++) {
      String aLineOfPixels = Input.nextLine();
      for (int j = 0; j < N; j++) {
        pixels[i][j] = (aLineOfPixels.charAt(j) == '1') ? (true) : (false);
      }
    }

    System.out.println(compress(N, 0, 0));
  }

  static String compress(int size, int row, int col) {
    if (areAllEqual(size, row, col)) {
      return pixels[row][col] ? "1" : "0";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('(');

    int prevSize = size / 2;

    for (int i = 0; i <= prevSize; i += prevSize) {
      for (int j = 0; j <= prevSize; j += prevSize) {
        sb.append(compress(prevSize, row + i, col + j));
      }
    }

    sb.append(')');
    return sb.toString();
  }

  static boolean areAllEqual(int size, int row, int col) {
    boolean head = pixels[row][col];
    for (int i = row; i < row + size; i++) {
      for (int j = col; j < col + size; j++) {
        if (pixels[i][j] != head) return false;
      }
    }
    return true;
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

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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