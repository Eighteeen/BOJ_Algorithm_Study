import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이것도 그래프로 하면 오히려 먼 길 돌아가는 느낌
class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();

    char[][] tiles = new char[ROW_SIZE][COLUMN_SIZE];
    StringBuilder tilesProtected = new StringBuilder();
    boolean isSheepSafe = true;

    row_for:
    for (int i = 0; i < ROW_SIZE; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < COLUMN_SIZE; j++) {
        tiles[i][j] = row.charAt(j);
        tilesProtected.append(tiles[i][j] == '.' ? 'D' : tiles[i][j]);
        
        boolean canWolfEatSheep = (tiles[i][j] == 'W') && (
            (i - 1 >= 0 && tiles[i - 1][j] == 'S') ||
            (j - 1 >= 0 && tiles[i][j - 1] == 'S')
        ) ||
        (tiles[i][j] == 'S') && (
          (i - 1 >= 0 && tiles[i - 1][j] == 'W') ||
          (j - 1 >= 0 && tiles[i][j - 1] == 'W')
        );
  
        if (canWolfEatSheep) {
          isSheepSafe = false;
          break row_for;
        }
      }
      tilesProtected.append('\n');
    }

    if (!isSheepSafe) {
      System.out.print(0);
      return;
    }

    System.out.print("1\n");
    System.out.print(tilesProtected);
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