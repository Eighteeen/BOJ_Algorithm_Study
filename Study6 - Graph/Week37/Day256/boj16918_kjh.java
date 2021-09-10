import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();
    final int TARGET_MINUTE = Input.nextInt();

    boolean[][] map = new boolean[ROW_SIZE][COLUMN_SIZE];
    for (int i = 0; i < ROW_SIZE; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < COLUMN_SIZE; j++) {
        map[i][j] = row.charAt(j) == 'O';
      }
    }

    BomberMan bomberMan = new BomberMan(map);
    if (TARGET_MINUTE == 1) {
      System.out.print(bomberMan.getVisualizedMap());
      return;
    }
    
    //// 이런 방법도 있네요! 알아가요! 신기방기
    int[] iterateMinute = new int[] { 2, 3, 2, 5 };
    int optimizedMinute = iterateMinute[(TARGET_MINUTE - 2) % 4];

    for (int i = 0; i < optimizedMinute; i++) {
      bomberMan.nextMinute();
    }

    System.out.print(bomberMan.getVisualizedMap());
  }
}

class BomberMan {
  boolean[][] map;
  int currentMinute;
  int nextInstallMinute;
  int nextBombMinute;
  List<Coordinate> currentBombs;
  List<Coordinate> nextBombs;

  static final boolean BOMB_IN_BLOCK = true;
  static final boolean NOTHING_IN_BLOCK = false;

  public BomberMan(boolean[][] map) {
    this.map = map;
    currentMinute = 0;
    nextInstallMinute = 2;
    nextBombMinute = 3;

    currentBombs = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == BOMB_IN_BLOCK) {
          currentBombs.add(new Coordinate(i, j));
        }
      }
    }

    nextBombs = new ArrayList<>();
  }

  public void nextMinute() {
    currentMinute++;

    if (currentMinute == nextInstallMinute) {
      installBombs();
      nextInstallMinute += 2;
    }
    if (currentMinute == nextBombMinute) {
      bomb();
      nextBombMinute += 2;
    }
  }

  private void installBombs() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == NOTHING_IN_BLOCK) {
          nextBombs.add(new Coordinate(i, j));
          map[i][j] = BOMB_IN_BLOCK;
        }
      }
    }
  }

  private void bomb() {
    int[] dy = new int[] { 0, 0, -1, 1 };
    int[] dx = new int[] { -1, 1, 0, 0 };

    for (Coordinate theBomb : currentBombs) {
      map[theBomb.y][theBomb.x] = NOTHING_IN_BLOCK;

      for (int i = 0; i < 4; i++) {
        int aroundY = theBomb.y + dy[i];
        int aroundX = theBomb.x + dx[i];

        boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= map.length || aroundX >= map[0].length;
        if (outOfIndex) {
          continue;
        }
        
        map[aroundY][aroundX] = NOTHING_IN_BLOCK;
        nextBombs.remove(new Coordinate(aroundY, aroundX));
      }
    }

    currentBombs = nextBombs;
    nextBombs = new ArrayList<>();
  }

  public String getVisualizedMap() {
    StringBuilder visualizedMap = new StringBuilder();

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        visualizedMap.append(map[i][j] == BOMB_IN_BLOCK ? 'O' : '.');
      }
      visualizedMap.append('\n');
    }

    return visualizedMap.toString();
  }
}

class Coordinate {
  public int y;
  public int x;

  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Coordinate == false) {
      return false;
    }

    Coordinate coordinate = (Coordinate) o;
    return coordinate.y == this.y && coordinate.x == this.x;
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