import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
  public static void main(String[] args) throws Exception {
    int YARD_ROWS = Input.nextInt();
    int YARD_COLS = Input.nextInt();

    char[][] yardArr = new char[YARD_ROWS][YARD_COLS];

    for (int i = 0; i < YARD_ROWS; i++) {
      String row = Input.nextLine();
      for (int j = 0; j < YARD_COLS; j++) {
        yardArr[i][j] = row.charAt(j);
      }
    }

    Yard yard = new Yard(yardArr);
    yard.countWolvesAndSheep();
    
    System.out.printf("%d %d", yard.getSheep(), yard.getWolves());
  }
}

class Yard {
  private char[][] yard;
  private int wolves;
  private int sheep;

  private boolean[][] visited;

  private final int[] dy = { 0, 0, -1, 1 };
  private final int[] dx = { -1, 1, 0, 0 };

  public Yard(char[][] yard) {
    this.yard = yard;
    wolves = 0;
    sheep = 0;

    visited = new boolean[yard.length][yard[0].length];
  }

  public void countWolvesAndSheep() {
    for (int y = 0; y < yard.length; y++) {
      for (int x = 0; x < yard[0].length; x++) {
        countWolvesAndSheepInArea(y, x);
      }
    }
  }

  private void countWolvesAndSheepInArea(int y, int x) {
    int field = yard[y][x];
    if (field == '.' || field == '#' || visited[y][x]) {
      return;
    }

    Queue<Coordinate> bfsQueue = new LinkedList<>();
    bfsQueue.add(new Coordinate(y, x));
    visited[y][x] = true;

    int wolvesInArea = 0;
    int sheepInArea = 0;
    while (bfsQueue.size() > 0) {
      Coordinate current = bfsQueue.poll();
      char currentField = yard[current.y][current.x];
      
      if (currentField == 'o') {
        sheepInArea++;
      } else if (currentField == 'v') {
        wolvesInArea++;
      }

      for (int i = 0; i < 4; i++) {
        int aroundY = current.y + dy[i];
        int aroundX = current.x + dx[i];

        boolean outOfIndex = aroundY < 0 || aroundY >= yard.length || aroundX < 0 || aroundX >= yard[0].length;
        if (outOfIndex) {
          continue;
        }
        if (yard[aroundY][aroundX] == '#') {
          continue;
        }
        if (visited[aroundY][aroundX]) {
          continue;
        }
        
        bfsQueue.add(new Coordinate(aroundY, aroundX));
        visited[aroundY][aroundX] = true;
      }
    }


    if (sheepInArea > wolvesInArea) {
      sheep += sheepInArea;
    } else {
      wolves += wolvesInArea;
    }
  }
  
  public int getWolves() {
    return wolves;
  }

  public int getSheep() {
    return sheep;
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