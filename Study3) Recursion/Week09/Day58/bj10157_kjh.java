import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 구동방식이 직관적으로 보이는 점이 좋아요
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

    //// 문제 자체로는 번호가 하나만 주어지니 상관없지만 계속해서 번호를 추출할 수 있는 sittingList 배열을 이용하시니,
    //// 대기 번호 자리를 추출하는 함수를 따로 만든다면 메인도 깔끔해지고 더욱 확장성있는 프로그램이 될 것 같습니다!
    for (int i = 1; i <= ROW_SIZE; i++) {
      for (int j = 1; j <= COL_SIZE; j++) {
        if (sittingList[i][j] == K) {
          System.out.print(i + " " + j);
          return;
        } 
      }
      
    }
  }

  //// 무조건 모든 번호를 저장하기 위해서 모든 루프를 돌아야 하는 게 아쉽게 느껴지기도 하지만 한번 구해놓으면 해당 배열을 계속 사용할 수 있다는 장점이 있네요!
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