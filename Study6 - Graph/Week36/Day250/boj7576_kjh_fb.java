import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

//// 깔끔해요 : 22
class Main {
  static int[][] tomatoBoxes;

  final static int RIPEN_TOMATO_IN_THE_BOX = 1;
  final static int TOMATO_IN_THE_BOX = 0;
  final static int IS_NOT_EXIST_IN_THE_BOX = -1; 

  public static void main(String[] args) throws Exception {
    final int COLUMN_AMOUNT = Input.nextInt();
    final int ROW_AMOUNT = Input.nextInt();

    tomatoBoxes = new int[ROW_AMOUNT][COLUMN_AMOUNT];
    List<Coordinate> tomatoLocations = new ArrayList<>();

    for (int y = 0; y < ROW_AMOUNT; y++) {
      for (int x = 0; x < COLUMN_AMOUNT; x++) {
        tomatoBoxes[y][x] = Input.nextInt();
        if (tomatoBoxes[y][x] == RIPEN_TOMATO_IN_THE_BOX) {
          tomatoLocations.add(new Coordinate(y, x));
        }
      }
    }

    System.out.print(getDaysAllRipen(tomatoLocations));
  }

  static int getDaysAllRipen(List<Coordinate> tomatoLocations) {
    int[][] eachMinDaysRipen = new int[tomatoBoxes.length][tomatoBoxes[0].length];
    for (int i = 0; i < eachMinDaysRipen.length; i++) {
      Arrays.fill(eachMinDaysRipen[i], 1000000);
    }

    int[] dy = { -1, 1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };
    
    Queue<Coordinate> queue = new LinkedList<>();
    for (Coordinate tomatoLocation : tomatoLocations) {
      queue.add(tomatoLocation);
      eachMinDaysRipen[tomatoLocation.y][tomatoLocation.x] = 0;
    }

    while (queue.size() > 0) {
      Coordinate currentTomato = queue.poll();
      
      for (int i = 0; i < 4; i++) {
        int aroundY = currentTomato.y + dy[i];
        int aroundX = currentTomato.x + dx[i];
        
        boolean isOutOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= tomatoBoxes.length || aroundX >= tomatoBoxes[0].length;
        if (isOutOfIndex) {
          continue;
        }
        //// isNoBox는 토마토가 들어있지 않은 칸을 말하는 것 같은데 "박스가 없는", "박스가 아닌" 으로 읽혀요! : 22
        //// isNoTomatoInBox나 isNotExistInBox는 어떨까요?
        //// => 피드백과 제안 고마워요! ㅎㅎ 위에 상수명도 오해의 소지가 많은 것 같아서 같이 수정했습니다
        boolean isNotExistInBox = tomatoBoxes[aroundY][aroundX] == -1;
        if (isNotExistInBox) {
          continue;
        }
        if (eachMinDaysRipen[aroundY][aroundX] <= eachMinDaysRipen[currentTomato.y][currentTomato.x] + 1) {
          continue;
        }
        queue.add(new Coordinate(aroundY, aroundX));
        eachMinDaysRipen[aroundY][aroundX] = eachMinDaysRipen[currentTomato.y][currentTomato.x] + 1;
      }
    }

    final int CAN_NOT_ALL_RIPEN = -1;
    int daysAllRipen = 0;
    for (int i = 0; i < eachMinDaysRipen.length; i++) {
      for (int j = 0; j < eachMinDaysRipen[0].length; j++) {
        if (tomatoBoxes[i][j] == -1) {
          continue;
        }
        if (eachMinDaysRipen[i][j] == 1000000) {
          return CAN_NOT_ALL_RIPEN;
        }
        daysAllRipen = Math.max(daysAllRipen, eachMinDaysRipen[i][j]);
      }
    }

    return daysAllRipen;
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