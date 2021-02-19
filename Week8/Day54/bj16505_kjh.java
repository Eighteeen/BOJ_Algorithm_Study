import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();
    char[][] stars = makeStars(N);

    int square = (int) Math.pow(2, N);
    for (int i = 0; i < square; i++) {
      for (int j = 0; j < (square - i); j++) {
        sb.append(stars[i][j] == '\u0000' ? ' ' : stars[i][j]);
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  static char[][] makeStars(int size) {
    if (size == 0) {
      char[][] stars = {{'*'}};
      return stars;
    }

    int square = (int) Math.pow(2, size);
    char[][] stars = new char[square][square];

    char[][] previousStars = makeStars(size - 1);
    int previousSquare = (int) Math.pow(2, size - 1);
    copyArray(previousStars, stars, 0, 0);
    copyArray(previousStars, stars, previousSquare, 0);
    copyArray(previousStars, stars, 0, previousSquare);

    return stars;
  }
  
  static void copyArray(char[][] from, char[][] to, int toStartX, int toStartY) {
    int toX = toStartX, toY = toStartY;
    for (int i = 0; i < from.length; i++) {
      for (int j = 0; j < from[0].length; j++) {
        to[toX][toY++] = from[i][j];
      }
      toX++;
      toY = toStartY;
    }
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