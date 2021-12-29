import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
  static int MAP_SIZE;

  static boolean[][] map;
  static final boolean WALL = true;

  static final int TO_RIGHT = 0;
  static final int TO_RIGHT_BOTTOM = 1;
  static final int TO_BOTTOM = 2;
  
  static int[][][] numberOfWaysToGetToTheDestination;
  static final int NOT_CALCULATED = -1;

  public static void main(String[] args) throws Exception {
    MAP_SIZE = Input.nextInt();
    map = new boolean[MAP_SIZE][MAP_SIZE];

    for (int i = 0; i < MAP_SIZE; i++) {
      for (int j = 0; j < MAP_SIZE; j++) {
        map[i][j] = Input.nextInt() == 1;
      }
    }
    
    numberOfWaysToGetToTheDestination = new int[MAP_SIZE][MAP_SIZE][3];
    for (int i = 0; i < MAP_SIZE; i++) {
      for (int j = 0; j < MAP_SIZE; j++) {
        Arrays.fill(numberOfWaysToGetToTheDestination[i][j], NOT_CALCULATED);
      }
    }

    int numberOfWays = getNumberOfWaysToGetToTheDestination(0, 0, TO_RIGHT);
    System.out.print(numberOfWays);
  }

  static int getNumberOfWaysToGetToTheDestination(int pipeY, int pipeX, int pipeDirection) {
    if (numberOfWaysToGetToTheDestination[pipeY][pipeX][pipeDirection] > NOT_CALCULATED) {
      return numberOfWaysToGetToTheDestination[pipeY][pipeX][pipeDirection];
    }
    if (isHitTheWall(pipeY, pipeX, pipeDirection)) {
      numberOfWaysToGetToTheDestination[pipeY][pipeX][pipeDirection] = 0;
      return 0;
    }
    if (isArrivedAtTheDestination(pipeY, pipeX, pipeDirection)) {
      return 1;
    }

    int nextY = getNextPipeY(pipeY, pipeDirection);
    int nextX = getNextPipeX(pipeX, pipeDirection);

    int numberOfWays = getNumberOfWaysToGetToTheDestination(nextY, nextX, TO_RIGHT_BOTTOM);
    if (pipeDirection == TO_RIGHT || pipeDirection == TO_RIGHT_BOTTOM) {
      numberOfWays += getNumberOfWaysToGetToTheDestination(nextY, nextX, TO_RIGHT);
    }
    if (pipeDirection == TO_BOTTOM || pipeDirection == TO_RIGHT_BOTTOM) {
      numberOfWays += getNumberOfWaysToGetToTheDestination(nextY, nextX, TO_BOTTOM);
    }

    numberOfWaysToGetToTheDestination[pipeY][pipeX][pipeDirection] = numberOfWays;
    return numberOfWays;
  }

  static boolean isHitTheWall(int pipeY, int pipeX, int pipeDirection) {
    if (isWallOrOutOfIndex(pipeY, pipeX)) {
      return true;
    }

    int nextY = getNextPipeY(pipeY, pipeDirection);
    int nextX = getNextPipeX(pipeX, pipeDirection);
    if (isWallOrOutOfIndex(nextY, nextX)) {
      return true;
    }
    if (
      pipeDirection == TO_RIGHT_BOTTOM &&
      (isWallOrOutOfIndex(nextY-1, nextX) || isWallOrOutOfIndex(nextY, nextX-1))
    ) {
      return true;
    }

    return false;
  }
  
  static boolean isWallOrOutOfIndex(int pipeY, int pipeX) {
    if (pipeY < 0 || pipeY >= MAP_SIZE || pipeX < 0 || pipeX >= MAP_SIZE) {
      return true;
    }
    if (map[pipeY][pipeX] == WALL) {
      return true;
    }
    return false;
  }

  static boolean isArrivedAtTheDestination(int pipeY, int pipeX, int pipeDirection) {
    int nextY = getNextPipeY(pipeY, pipeDirection);
    int nextX = getNextPipeX(pipeX, pipeDirection);

    return (nextY == MAP_SIZE-1) && (nextX == MAP_SIZE-1);
  }

  static int getNextPipeY(int pipeY, int pipeDirection) {
    if (pipeDirection == TO_RIGHT) {
      return pipeY;
    }
    return pipeY + 1;
  }

  static int getNextPipeX(int pipeX, int pipeDirection) {
    if (pipeDirection == TO_BOTTOM) {
      return pipeX;
    }
    return pipeX + 1;
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