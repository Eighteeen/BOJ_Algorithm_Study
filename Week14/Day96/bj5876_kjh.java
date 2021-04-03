import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔합니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int SHOES_BOARD_SIZE = Input.nextInt();
    ShoesBoard shoesBoard = new ShoesBoard(SHOES_BOARD_SIZE);

    for (int i = 0; i < SHOES_BOARD_SIZE; i++) {
      String shoesRow = Input.nextLine();
      shoesBoard.addShoesInRow(i, shoesRow);
    }

    System.out.print(shoesBoard.getMaxBalanced());
  }
}

class ShoesBoard {
  private final boolean OPEN = true;
  private final boolean CLOSE = false;

  //// isOpening, isClosing과 함께 할 때에 뜻을 알아보긴 하지만 shoes 자체 변수명을 보고 무엇을 저장하는지 한번에 알기 어려웠어요.
  //// open을 기준으로 true값을 저장하니 단순히 shoes보다는 openShoes, isLeftShoes 등으로 정의하면 알아보기 더 편할 것 같습니다.
  private boolean[][] shoes;
  private int size;

  public ShoesBoard(int size) {
    shoes = new boolean[size][size];
    this.size = size;
  }

  public void addShoesInRow(int row, String shoesRow) {
    for (int i = 0; i < shoesRow.length(); i++) {
      shoes[row][i] = shoesRow.charAt(i) == '(';
    }
  }

  public int getMaxBalanced() {
    if (isClosing(shoes[0][0])) return 0;
    return getMaxBalanced(0, 0, new boolean[size][size], 0, 0);
  }

  private int getMaxBalanced(int row, int col, boolean[][] visited, int opening, int closing) {
    if (outOfShoesBoard(row, col)) return 0;
    if (visited[row][col]) return 0;
    if (closing > 0 && isOpening(shoes[row][col])) return 0;

    visited[row][col] = true; 
    opening += isOpening(shoes[row][col]) ? 1 : 0;
    closing += isClosing(shoes[row][col]) ? 1 : 0;
    
    if (opening == closing) {
      visited[row][col] = false;
      return opening + closing;
    }
    
    int maxBalanced = getMaxBalanced(row + 1, col, visited, opening, closing);
    maxBalanced = Math.max(maxBalanced, getMaxBalanced(row - 1, col, visited, opening, closing));
    maxBalanced = Math.max(maxBalanced, getMaxBalanced(row, col + 1, visited, opening, closing));
    maxBalanced = Math.max(maxBalanced, getMaxBalanced(row, col - 1, visited, opening, closing));

    visited[row][col] = false;
    return maxBalanced;
  }
  
  private boolean outOfShoesBoard(int row, int col) {
    return row < 0 || col < 0 || row >= size || col >= size;
  }

  private boolean isOpening(boolean shoe) {
    return shoe == OPEN;
  }

  private boolean isClosing(boolean shoe) {
    return shoe == CLOSE;
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