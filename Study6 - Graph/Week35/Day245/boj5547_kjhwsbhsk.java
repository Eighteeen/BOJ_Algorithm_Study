import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static int[][] blocks;
  static boolean[][] visited;

  static int[] ODD_DX = new int[] { 0, -1, 0, 1, 1, 1 };
  static int[] ODD_DY = new int[] { -1, 0, 1, 1, 0, -1 };
  static int[] EVEN_DX = new int[] { -1, -1, -1, 0, 1, 0 };
  static int[] EVEN_DY = new int[] { -1, 0, 1, 1, 0, -1 };

  public static void main(String[] args) throws Exception {
    final int X_SIZE = Input.nextInt();
    final int Y_SIZE = Input.nextInt();

    blocks = new int[X_SIZE + 2][Y_SIZE + 2];
    for (int i = 1; i <= Y_SIZE; i++) {
      for (int j = 1; j <= X_SIZE; j++) {
        int block = Input.nextInt();
        blocks[j][i] = block;
      }
    }
    
    visited = new boolean[X_SIZE + 2][Y_SIZE + 2];
    int lengthOfWall = getLengthOfWall(0, 0);
    System.out.print(lengthOfWall);
  }

  
  static int getLengthOfWall(int x, int y) {
    if (visited[x][y]) return 0;
    visited[x][y] = true;

    int[] dx;
    int[] dy;

    if (y % 2 == 0) {
      dx = EVEN_DX;
      dy = EVEN_DY;
    } else {
      dx = ODD_DX;
      dy = ODD_DY;
    }

    int lengthOfWall = 0;
    for (int i = 0; i < 6; i++) {
      int aroundX = x + dx[i];
      int aroundY = y + dy[i];
      if (outOfBoard(aroundX, aroundY)) continue;

      int aroundBlock = blocks[aroundX][aroundY];

      lengthOfWall += aroundBlock;
      if (aroundBlock == 0) {
        lengthOfWall += getLengthOfWall(aroundX, aroundY);
      }
    }

    return lengthOfWall;
  }

  static boolean outOfBoard(int x, int y) {
    return x < 0 || y < 0 || x >= blocks.length || y >= blocks[0].length;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}