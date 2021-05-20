import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] papers;
  static int blues, whites;

  public static void main(String[] args) throws Exception {
    final int SIZE = Input.nextInt();
    papers = new boolean[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        papers[i][j] = isBlue(Input.nextInt());
      }
    }

    slicePapers(SIZE, 0, 0);

    System.out.print(whites + "\n" + blues);
  }

  static void slicePapers(int size, int row, int col) {
    if (isAllPapersEqual(size, row, col)) {
      if (isBlue(papers[row][col])) {
        blues++;
      } else {
        whites++;
      }
      return;
    }

    int half = size / 2;
    slicePapers(half, row, col);
    slicePapers(half, row, col + half);
    slicePapers(half, row + half, col);
    slicePapers(half, row + half, col + half);
  }

  static boolean isAllPapersEqual(int size, int row, int col) {
    boolean standard = papers[row][col];
    for (int i = row; i < row + size; i++) {
      for (int j = col; j < col + size; j++) {
        if (papers[i][j] != standard) {
          return false;
        }
      }
    }
    return true;
  }

  static boolean isBlue(int paper) {
    return paper == 1;
  }
  
  static boolean isBlue(boolean paper) {
    return paper;
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