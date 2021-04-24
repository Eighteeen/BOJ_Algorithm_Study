import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int tileNumber = 1;
  static int tiles[][];

  public static void main(String[] args) throws Exception {
    final int K = Input.nextInt();
    final int COL = Input.nextInt();
    final int ROW = Input.nextInt();

    int floorSize = (int) Math.pow(2, K);
    tiles = new int[floorSize][floorSize];

    tiles[floorSize - ROW][COL - 1] = -1;

    fillTiles(floorSize, 0, 0);
    printTiles();
  }

  static void fillTiles(int size, int row, int col) {
    if (isWholeFilled(size, row, col)) return;
    if (isWholeNotFilled(size, row, col)) return;

    if (size == 2) {
      fillOneTile(row, col);
      return;
    }

    int half = size / 2;
    fillTiles(half, row, col);
    fillTiles(half, row, col + half);
    fillTiles(half, row + half, col);
    fillTiles(half, row + half, col + half);

    fillTiles(half, row + half / 2, col + half / 2);

    fillTiles(half, row, col);
    fillTiles(half, row, col + half);
    fillTiles(half, row + half, col);
    fillTiles(half, row + half, col + half);
  }

  static boolean isWholeFilled(int size, int row, int col) {
    for (int i = row; i < row + size; i++) {
      for (int j = col; j < col + size; j++) {
        if (tiles[i][j] == 0) return false;
      }
    }
    return true;
  }
  static boolean isWholeNotFilled(int size, int row, int col) {
    for (int i = row; i < row + size; i++) {
      for (int j = col; j < col + size; j++) {
        if (tiles[i][j] != 0) return false;
      }
    }
    return true;
  }

  static void fillOneTile(int row, int col) {
    for (int i = row; i < row + 2; i++) {
      for (int j = col; j < col + 2; j++) {
        if (tiles[i][j] != 0) continue;
        tiles[i][j] = tileNumber;
      }
    }

    tileNumber++;
  }

  static void printTiles() {
    StringBuilder sb = new StringBuilder();
    for (int[] tileLine : tiles) {
        for (int tile : tileLine) sb.append(tile).append(' ');
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