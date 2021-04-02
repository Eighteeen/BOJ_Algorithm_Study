import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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