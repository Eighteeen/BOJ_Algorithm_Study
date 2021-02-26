import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] stars;

  public static void main(String[] args) {
    final int STEP = Input.nextInt();
    int size = 4 * STEP - 3;
    stars = new boolean[size][size];

    boxInBox(0, 0, STEP);
    printStars(size);
  }

  static void boxInBox(int row, int col, int step) {
    if (step == 1) {
      stars[row][col] = true;
      return;
    }

    int size = 4 * step - 3;
    int lastRow = row + size - 1;
    int lastCol = col + size - 1;

    for (int i = 0; i < size; i++) {
      stars[row][col+i] = true;
      stars[lastRow][col+i] = true;
      stars[row+i][col] = true; 
      stars[row+i][lastCol] = true;
    }
    
    boxInBox(row + 2, col + 2, step - 1);
  }

  static void printStars(int size) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        sb.append(stars[i][j] ? '*' : ' ');
      }
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