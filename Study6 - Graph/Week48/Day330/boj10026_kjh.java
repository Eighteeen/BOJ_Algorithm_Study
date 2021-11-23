import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 클린코드에서 boolean 매개변수는 치졸하다고 해서 나눠봤는데 영.. 좋은지 잘 모르겠네요
//// boolean 매개변수를 사용하지 않고도 깔끔하게 풀 수 있었을 것 같아요! 중복되는 느낌이 강하네요 : 22 좀 더 정리되면 좋을 것 같아요
//// 그래도 깔끔해요 : 22 그럼에도 불구하고 엄청나게 거슬리는 점은 없었습니다
class Main {
  static final int[] dy = { 0, -1, 0, 1 };
  static final int[] dx = { -1, 0, 1, 0 };
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    char[][] bitmap = new char[N][N];
    for (int i = 0; i < N; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < N; j++) {
        bitmap[i][j] = row.charAt(j);
      }
    }

    int numberOfZones = getNumberOfZones(bitmap);
    int numberOfZonesRedGreenWeakness = getNumberOfZonesRedGreenWeakness(bitmap);

    System.out.printf("%d %d", numberOfZones, numberOfZonesRedGreenWeakness);
  }

  static int getNumberOfZones(char[][] bitmap) {
    visited = new boolean[bitmap.length][bitmap.length];
    
    int numberOfZones = 0;
    for (int i = 0; i < bitmap.length; i++) {
      for (int j = 0; j < bitmap[0].length; j++) {
        if (visited[i][j] == false) {
          visitZoneBits(bitmap, i, j);
          numberOfZones++;
        }
      }
    }

    return numberOfZones;
  }

  static int getNumberOfZonesRedGreenWeakness(char[][] bitmap) {
    visited = new boolean[bitmap.length][bitmap.length];
    
    int numberOfZones = 0;
    for (int i = 0; i < bitmap.length; i++) {
      for (int j = 0; j < bitmap[0].length; j++) {
        if (visited[i][j] == false) {
          visitZoneBitsRedGreenWeakness(bitmap, i, j);
          numberOfZones++;
        }
      }
    }

    return numberOfZones;
  }

  static void visitZoneBits(char[][] bitmap, int y, int x) {
    if (visited[y][x]) {
      return;
    }
    visited[y][x] = true;

    for (int i = 0; i < 4; i++) {
      int aroundY = dy[i] + y;
      int aroundX = dx[i] + x;
      
      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= bitmap.length || aroundX >= bitmap[0].length;
      if (outOfIndex) {
        continue;
      }
      if (visited[aroundY][aroundX]) {
        continue;
      }
      if (bitmap[y][x] == bitmap[aroundY][aroundX]) {
        visitZoneBits(bitmap, aroundY, aroundX);
      }
    }
  }

  static void visitZoneBitsRedGreenWeakness(char[][] bitmap, int y, int x) {
    if (visited[y][x]) {
      return;
    }
    visited[y][x] = true;

    for (int i = 0; i < 4; i++) {
      int aroundY = dy[i] + y;
      int aroundX = dx[i] + x;
      
      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= bitmap.length || aroundX >= bitmap[0].length;
      if (outOfIndex) {
        continue;
      }
      if (visited[aroundY][aroundX]) {
        continue;
      }
      if (
        bitmap[y][x] == bitmap[aroundY][aroundX] ||
        (bitmap[y][x] != 'B' && bitmap[aroundY][aroundX] != 'B')
      ) {
        visitZoneBitsRedGreenWeakness(bitmap, aroundY, aroundX);
      }
    }
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