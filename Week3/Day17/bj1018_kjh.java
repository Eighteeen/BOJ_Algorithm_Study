import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int ROWS = Input.nextInt();
    final int COLUMNS = Input.nextInt();
    
    char[][] chessBoard = new char[ROWS][COLUMNS];

    for (int i = 0; i < ROWS; i++) {
      String oneRowOfBoard = Input.nextLine();
      char[] tiles = oneRowOfBoard.toCharArray();

      for(int j = 0; j < COLUMNS; j++) {
        chessBoard[i][j] = tiles[j];
      }
    }

    int rowCompareCount = ROWS - 8 + 1;
    int columnCompareCount = COLUMNS - 8 + 1;

    int minForAllBoard = ROWS * COLUMNS;
    for (int i = 0; i < rowCompareCount; i++) {
      for (int j = 0; j < columnCompareCount; j++) {
        int minForABoard = calcMinForABoard(chessBoard, i, j);
        minForAllBoard = (minForABoard < minForAllBoard) ? (minForABoard) : (minForAllBoard);
      }
    }
    System.out.print(minForAllBoard);
  }

  private static int calcMinForABoard(char[][] chessBoard, int startRowIndex, int startColumnIndex) {
    char standardTile = chessBoard[startRowIndex][startColumnIndex];

    int needRepaints = 0;
    int needRepaintsOppositeStandard = 0;
    for (int i = startRowIndex; i < startRowIndex + 8; i++) {
      for (int j = startColumnIndex; j < startColumnIndex + 8; j++) {
        boolean isEvenForBothIndicies = ( (i - startRowIndex) + (j - startColumnIndex) ) % 2 == 0;
        boolean isEqualToStandard = chessBoard[i][j] == standardTile;
        
        // 타일이 다시 칠해져야 할 때는 '인덱스가 짝수(true)인데 기준과 다른(false) 경우'와 '인덱스가 홀수(false)인데 기준과 같은(true) 경우'
        boolean isNeedRepaint = isEvenForBothIndicies ^ isEqualToStandard;
        needRepaints += (isNeedRepaint) ? (1) : (0);

        // 기준 타일을 반대로 했을 때 오히려 다시 칠해야 할 양이 더 적을 수 있음
        boolean isNeedRepaintOppositeStandard = isEvenForBothIndicies ^ !isEqualToStandard;
        needRepaintsOppositeStandard += (isNeedRepaintOppositeStandard) ? (1) : (0);
      }
    }

    return Math.min(needRepaints, needRepaintsOppositeStandard);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}