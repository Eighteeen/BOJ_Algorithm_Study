import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int[][] sittingList;
  static int waitingNumber;

  public static void main(String[] args) throws Exception {
    // 시계방향으로 90도 회전하여 생각하였음
    final int ROW_SIZE = Input.nextInt();
    final int COL_SIZE = Input.nextInt();
    final int K = Input.nextInt();

    if (K > (ROW_SIZE * COL_SIZE)) {
      System.out.print(0);
      return;
    }

    waitingNumber = 1;
    sittingList = new int[ROW_SIZE + 1][COL_SIZE + 1];
    fillSittingList(ROW_SIZE, COL_SIZE, 1, 1);

    for (int i = 1; i <= ROW_SIZE; i++) {
      for (int j = 1; j <= COL_SIZE; j++) {
        if (sittingList[i][j] == K) {
          System.out.print(i + " " + j);
          return;
        } 
      }
      
    }
  }

  static void fillSittingList(int rowSize, int colSize, int rowToBeMade, int colToBeMade) {
    if (rowSize <= 0 || colSize <= 0) return;

    int row = rowToBeMade;
    int col = colToBeMade - 1;

    for (int i = 0; i < colSize; i++) {
      sittingList[row][++col] = waitingNumber++;
    }
    rowSize--;
    if (rowSize == 0) return;

    for (int i = 0; i < rowSize; i++) {
      sittingList[++row][col] = waitingNumber++;
    }
    colSize--;
    if (colSize == 0) return;

    for (int i = 0; i < colSize; i++) {
      sittingList[row][--col] = waitingNumber++;
    }
    rowSize--;
    if (rowSize == 0) return;

    for (int i = 0; i < rowSize; i++) {
      sittingList[--row][col] = waitingNumber++;
    }
    colSize--;

    fillSittingList(rowSize, colSize, rowToBeMade + 1, colToBeMade + 1);
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