import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Main {
  static int[][][] tomatoBoxes;

  static int COLUMN_SIZE;
  static int ROW_SIZE;
  static int HEIGHT_SIZE;

  final static int RIPEN_TOMATO_IN_THE_BOX = 1;
  final static int TOMATO_IN_THE_BOX = 0;
  final static int NOTHING_IN_THE_BOX = -1; 

  public static void main(String[] args) throws Exception {
    COLUMN_SIZE = Input.nextInt();
    ROW_SIZE = Input.nextInt();
    HEIGHT_SIZE = Input.nextInt();

    tomatoBoxes = new int[HEIGHT_SIZE][ROW_SIZE][COLUMN_SIZE];
    List<Coordinate> tomatoLocations = new ArrayList<>();

    for (int z = 0; z < HEIGHT_SIZE; z++) {
      for (int y = 0; y < ROW_SIZE; y++) {
        for (int x = 0; x < COLUMN_SIZE; x++) {
          tomatoBoxes[z][y][x] = Input.nextInt();
          if (tomatoBoxes[z][y][x] == RIPEN_TOMATO_IN_THE_BOX) {
            tomatoLocations.add(new Coordinate(z, y, x));
          }
        }
      }
    }

    System.out.print(getDaysAllRipen(tomatoLocations));
  }

  static int getDaysAllRipen(List<Coordinate> tomatoLocations) {
    int[][][] eachMinDaysRipen = new int[HEIGHT_SIZE][ROW_SIZE][COLUMN_SIZE];
    for (int z = 0; z < HEIGHT_SIZE; z++) {
      for (int y = 0; y < ROW_SIZE; y++) {
        Arrays.fill(eachMinDaysRipen[z][y], 1000000);
      }
    }

    int[] dz = { 0, 0, 0, 0, -1, 1 };
    int[] dy = { 0, 0, -1, 1, 0, 0 };
    int[] dx = { -1, 1, 0, 0, 0, 0 };
    
    Queue<Coordinate> queue = new LinkedList<>();
    for (Coordinate tomatoLocation : tomatoLocations) {
      queue.add(tomatoLocation);
      eachMinDaysRipen[tomatoLocation.z][tomatoLocation.y][tomatoLocation.x] = 0;
    }

    while (queue.size() > 0) {
      Coordinate currentTomato = queue.poll();
      
      for (int i = 0; i < 6; i++) {
        int aroundZ = currentTomato.z + dz[i];
        int aroundY = currentTomato.y + dy[i];
        int aroundX = currentTomato.x + dx[i];
        
        boolean isOutOfIndex = aroundZ < 0 || aroundY < 0 || aroundX < 0 || aroundZ >= HEIGHT_SIZE || aroundY >= ROW_SIZE || aroundX >= COLUMN_SIZE;
        if (isOutOfIndex) {
          continue;
        }
        if (tomatoBoxes[aroundZ][aroundY][aroundX] == NOTHING_IN_THE_BOX) {
          continue;
        }
        if (eachMinDaysRipen[aroundZ][aroundY][aroundX] <= eachMinDaysRipen[currentTomato.z][currentTomato.y][currentTomato.x] + 1) {
          continue;
        }
        queue.add(new Coordinate(aroundZ, aroundY, aroundX));
        eachMinDaysRipen[aroundZ][aroundY][aroundX] = eachMinDaysRipen[currentTomato.z][currentTomato.y][currentTomato.x] + 1;
      }
    }

    final int CAN_NOT_ALL_RIPEN = -1;
    int daysAllRipen = 0;
    for (int z = 0; z < HEIGHT_SIZE; z++) {
      for (int y = 0; y < ROW_SIZE; y++) {
        for (int x = 0; x < COLUMN_SIZE; x++) {
          if (tomatoBoxes[z][y][x] == -1) {
            continue;
          }
          if (eachMinDaysRipen[z][y][x] == 1000000) {
            return CAN_NOT_ALL_RIPEN;
          }
          daysAllRipen = Math.max(daysAllRipen, eachMinDaysRipen[z][y][x]);
        }
      }
    }

    return daysAllRipen;
  }
}

class Coordinate {
  public int z;
  public int y;
  public int x;

  public Coordinate(int z, int y, int x) {
    this.z = z;
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