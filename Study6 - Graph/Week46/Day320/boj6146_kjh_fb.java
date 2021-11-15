import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

//// 오 왕깔끔
//// 처음부터 사이즈를 전부 잡고 시작하니 간단하게 구현할 수 있군요. 또 다른 방법 알아갑니다!
class Main {
  static int MAP_SIZE = 1001;
  //// DRY_GROUND는 지워도 될 듯 해요
  //// => 실사용 여부와 상관없이 false true의 의미를 명확하게 해두고 싶어요!
  static boolean DRY_GROUND = false;
  static boolean WET_GROUND = true;
  
  static int COMPLEMENT = 500;

  //// 함수로 분리했으면 더 좋았겠지만,
    //// 변수명이랑 로직들이 명확해서 함수 분리가 없는데도 술술 읽히고 깔끔한 느낌이 났어요!
  //// => 감사합니다 ㅎㅎ; 메인으로도 충분할거같더라고요
  public static void main(String[] args) throws Exception {
    final int DESTINATION_Y = Input.nextInt() + COMPLEMENT;
    final int DESTINATION_X = Input.nextInt() + COMPLEMENT;
    final int AMOUNT_OF_WET_GROUND = Input.nextInt();

    boolean[][] map = new boolean[MAP_SIZE][MAP_SIZE];
    for (int i = 0; i < AMOUNT_OF_WET_GROUND; i++) {
      int wetY = Input.nextInt() + COMPLEMENT;
      int wetX = Input.nextInt() + COMPLEMENT;
      map[wetY][wetX] = WET_GROUND;
    }

    int[] dy = { 0, -1, 0, 1 };
    int[] dx = { -1, 0, 1, 0 };

    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(new Coordinate(500, 500));

    int[][] distance = new int[MAP_SIZE][MAP_SIZE];
    while (queue.size() > 0) {
      Coordinate current = queue.poll();
      int y = current.y;
      int x = current.x;
      if (y == DESTINATION_Y && x == DESTINATION_X) {
        break;
      }
      
      for (int i = 0; i < 4; i++) {
        int aroundY = y + dy[i];
        int aroundX = x + dx[i];

        boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= MAP_SIZE || aroundX >= MAP_SIZE;
        if (outOfIndex) {
          continue;
        }
        if (map[aroundY][aroundX] == WET_GROUND) {
          continue;
        }
        boolean visited = distance[aroundY][aroundX] > 0;
        if (visited) {
          continue;
        }

        distance[aroundY][aroundX] = distance[y][x] + 1;
        queue.add(new Coordinate(aroundY, aroundX));
      }
    }

    System.out.print(distance[DESTINATION_Y][DESTINATION_X]);
  }
}

class Coordinate {
  public int y;
  public int x;

  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
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