import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();

    boolean[][] isWater = new boolean[ROW_SIZE][COLUMN_SIZE];
    for (int i = 0; i < ROW_SIZE; i++) {
      String line = Input.nextLine();
      for (int j = 0; j < COLUMN_SIZE; j++) {
        isWater[i][j] = line.charAt(j) == '.';
      }
    }

    int beachLength = getBeachLength(isWater);
    System.out.print(beachLength);
  }

  //// dy 를 2차원으로 선언할 필요는 없어보입니다.
  //// => 그러네요! 땡큐합니다
  static int getBeachLength(boolean[][] isWater) {
    int[] dy = { -1, -1, 0, 1, 1, 0 };
    int[][] dx = { { -1, 0, 1, 0, -1, -1 }, { 0, 1, 1, 1, 0, -1 } };

    int beachLength = 0;
    for (int y = 0; y < isWater.length; y++) {
      for (int x = 0; x < isWater[0].length; x++) {
        if (isWater[y][x]) {
          continue;
        }

        for (int i = 0; i < 6; i++) {
          int aroundY = y + dy[i];
          int aroundX = x + dx[y % 2][i];

          boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= isWater.length || aroundX >= isWater[0].length;
          if (outOfIndex) {
            continue;
          }

          if (isWater[aroundY][aroundX]) {
            beachLength++;
          }
        }
      }
    }

    return beachLength;
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