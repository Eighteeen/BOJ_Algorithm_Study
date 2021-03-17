import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] pixels;

  public static void main(String[] args) throws Exception {
    final int N = Integer.parseInt(Input.nextLine().split(" ")[2]);
    Input.skipLine(2);

    pixels = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      String hexStrs = Input.nextLine();
      hexStrsToPixels(i, hexStrs);
    }

    System.out.println(N);
    System.out.print(encrypt(N, 0, 0));
  }

  static void hexStrsToPixels(int y, String hexStrs) {
    StringTokenizer st = new StringTokenizer(hexStrs, ",");
    int hexStrCount = st.countTokens();
    
    for (int i = 0; i < hexStrCount; i++) {
      String hexStr = st.nextToken().substring(2);
      int decimal = Integer.parseInt(hexStr, 16);
      
      for (int j = 0; j < 8; j++) {
        if (decimal == 0) break;

        pixels[y][(i * 8) + j] = ((decimal % 2) == 1);
        decimal /= 2;
      }
    }
  }

  static String encrypt(int size, int y, int x) {
    if (isAllEquals(size, y, x)) {
      return pixelToStr(pixels[y][x]);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Q");

    int half = size / 2;
    sb.append(encrypt(half, y, x));
    sb.append(encrypt(half, y, x + half));
    sb.append(encrypt(half, y + half, x));
    sb.append(encrypt(half, y + half, x + half));

    return sb.toString();
  }
  
  static String pixelToStr(boolean pixel) {
    return pixel ? "B" : "W";
  }

  static boolean isAllEquals(int size, int y, int x) {
    boolean head = pixels[y][x];

    for (int i = y; i < y + size; i++) {
      for (int j = x; j < x + size; j++) {
        if (pixels[i][j] != head) return false;
      }
    }

    return true;
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