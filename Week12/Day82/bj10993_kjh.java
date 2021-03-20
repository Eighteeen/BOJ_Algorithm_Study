import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] stars;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();

    int rowSize = calcRowSize(N);
    int colSize = calcColSize(N);
    stars = new boolean[rowSize][colSize];

    makeStars(N, 0, 0);
    printStars(N);
  }

  //// 사이즈까지 재귀함수라니! 활용을 많이 하셨네요.
  public static int calcRowSize(int size) {
    if (size == 1) return 1;
    return calcRowSize(size - 1) + (int) Math.pow(2, size - 1);
  }

  public static int calcColSize(int size) {
    if (size == 1) return 1;
    return calcColSize(size - 1) + (int) Math.pow(2, size);
  }

  //// 복잡해보이기는 하지만 많은 고민을 하신 게 느껴집니다!
  public static void makeStars(int size, int row, int col) {
    if (size == 1) {
      stars[row][col] = true;
      return;
    }

    int rowSize = calcRowSize(size);
    int colSize = calcColSize(size);

    int colPrev = (int) Math.pow(2, size - 1);
    int rowPrev = 1;

    if ((size % 2) == 0) {
      makeStars(size - 1, row + rowPrev, col + colPrev);

      for (int i = 0; i < colSize; i++) {
        stars[row][col+i] = true; // 윗 부분
      }
      // 1 2
      for (int i = 1; i < rowSize; i++) {
        stars[row+i][col+i] = true; // 왼쪽 대각선 테두리
        stars[row+i][col+colSize-1-i] = true; // 오른쪽 대각선 테두리
      }
      return;
    }

    rowPrev = colPrev - 1;
    makeStars(size - 1, row + rowPrev, col + colPrev);

    for (int i = 0; i < colSize; i++) {
      stars[row+rowSize-1][col+i] = true; // 아랫 부분
    }
    
    for (int i = 0; i < rowSize - 1; i++) {
      stars[row+i][col+rowSize-1-i] = true; // 왼쪽 대각선 테두리
      stars[row+i][col+colSize-rowSize+i] = true; // 오른쪽 대각선 테두리
    }
  }

  public static void printStars(int size) {
    if (size == 1) {
      System.out.print("*");
      return;
    }

    int rowSize = calcRowSize(size);
    int colSize = calcColSize(size);

    StringBuilder sb = new StringBuilder();
    if ((size % 2) == 0) {
      for (int i = 0; i < rowSize; i++) {
        for (int j = 0; j < colSize - i; j++) {
          sb.append(stars[i][j] ? '*' : ' ');
        }
        sb.append('\n');
      }
      System.out.print(sb);
      return;
    }

    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < colSize - rowSize + i + 1; j++) {
        sb.append(stars[i][j] ? '*' : ' ');
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