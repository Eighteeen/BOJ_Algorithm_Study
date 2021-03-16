import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] stars;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    stars = new boolean[N][N];

    makeStars(N, 0, 0);
    printStars(stars);
  }

  static void makeStars(int size, int y, int x) {
    if (size == 1) {
      stars[y][x] = true;
      return;
    }

    int prevSize = size / 3;
    makeStars(prevSize, y, x);
    makeStars(prevSize, y, x + prevSize);
    makeStars(prevSize, y, x + prevSize * 2);

    makeStars(prevSize, y + prevSize, x);
    makeStars(prevSize, y + prevSize, x + prevSize * 2);
    
    makeStars(prevSize, y + prevSize * 2, x);
    makeStars(prevSize, y + prevSize * 2, x + prevSize);
    makeStars(prevSize, y + prevSize * 2, x + prevSize * 2);
  }

  static void printStars(boolean[][] stars) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < stars.length; i++) {
      for (int j = 0; j < stars[0].length; j++) {
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