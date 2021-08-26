import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();

    final String[][] blocks = new String[ROW_SIZE][COLUMN_SIZE];
    for (int row = 0; row < ROW_SIZE; row++) {
      blocks[row] = Input.nextLine().split("");
    }

    int blocksSize = ROW_SIZE * COLUMN_SIZE;
    for (int row = 0; row < ROW_SIZE; row++) {
      String prevBlock = "";
      for (int col = 0; col < COLUMN_SIZE; col++) {
        String currentBlock = blocks[row][col];
        if (prevBlock.equals("-") && currentBlock.equals("-")) {
          blocksSize--;
        }
        prevBlock = currentBlock;
      }
    }

    for (int col = 0; col < COLUMN_SIZE; col++) {
      String prevBlock = "";
      for (int row = 0; row < ROW_SIZE; row++) {
        String currentBlock = blocks[row][col];
        if (prevBlock.equals("|") && currentBlock.equals("|")) {
          blocksSize--;
        }
        prevBlock = currentBlock;
      }
    }

    System.out.print(blocksSize);
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