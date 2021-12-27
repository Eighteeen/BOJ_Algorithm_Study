// 문제풀이 실패
// 시간초과 해결할 방법 모르겠음
// 존나 완벽한 로직이라 생각했는데 대체 왜..

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  static final boolean LIGHT_ON = true;
  static final boolean LIGHT_OFF = false;

  static int roomSize;
  
  static int[] dy = { 0, 0, -1, 1 };
  static int[] dx = { -1, 1, 0, 0 };

  public static void main(String[] args) throws Exception {
    roomSize = Input.nextInt();
    final int BUTTON_AMOUNT = Input.nextInt();

    List<Coordinate>[][] buttonsRoomHas = new ArrayList[roomSize + 1][roomSize + 1];

    for (int i = 0; i < BUTTON_AMOUNT; i++) {
      Coordinate room = new Coordinate(Input.nextInt(), Input.nextInt());
      Coordinate button = new Coordinate(Input.nextInt(), Input.nextInt());

      if (buttonsRoomHas[room.y][room.x] == null) {
        buttonsRoomHas[room.y][room.x] = new ArrayList<Coordinate>();
      }
      buttonsRoomHas[room.y][room.x].add(button);
    }

    int maxRoomsLightOn = getMaxRoomsLightOn(buttonsRoomHas);
    System.out.print(maxRoomsLightOn);
  }

  static int getMaxRoomsLightOn(List<Coordinate>[][] buttonsRoomHas) {
    boolean[][] visited = new boolean[roomSize + 1][roomSize + 1];
    boolean[][] rooms = new boolean[roomSize + 1][roomSize + 1];

    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(new Coordinate(1, 1));
    rooms[1][1] = LIGHT_ON;

    while (queue.size() > 0) {
      Coordinate currentRoom = queue.poll();
      visited[currentRoom.y][currentRoom.x] = true;
      
      for (int i = 0; i < 4; i++) {
        int aroundY = currentRoom.y + dy[i];
        int aroundX = currentRoom.x + dx[i];

        boolean outOfIndex = aroundY < 1 || aroundX < 1 || aroundY > roomSize || aroundX > roomSize;
        if (outOfIndex || visited[aroundY][aroundX]) {
          continue;
        }
        if (rooms[aroundY][aroundX] == LIGHT_ON) {
          queue.add(new Coordinate(aroundY, aroundX));
        }
      }

      if (buttonsRoomHas[currentRoom.y][currentRoom.x] == null) {
        continue;
      }

      for (Coordinate button : buttonsRoomHas[currentRoom.y][currentRoom.x]) {
        rooms[button.y][button.x] = LIGHT_ON;
        
        if (visited[button.y][button.x]) {
          continue;
        }

        if (hasEverVisitedUpDownLeftRight(button, visited)) {
          queue.add(button);
        }
      }
    }

    int maxRooms = 0;
    for (int i = 1; i <= roomSize; i++) {
      for (int j = 1; j <= roomSize; j++) {
        if (rooms[i][j] == LIGHT_ON) {
          maxRooms++;
        }
      }
    }
    return maxRooms;
  }

  static boolean hasEverVisitedUpDownLeftRight(Coordinate room, boolean[][] visited) {
    for (int i = 0; i < 4; i++) {
      int aroundY = room.y + dy[i];
      int aroundX = room.x + dx[i];

      boolean outOfIndex = aroundY < 1 || aroundX < 1 || aroundY > roomSize || aroundX > roomSize;
      if (outOfIndex) {
        continue;
      }

      if (visited[aroundY][aroundX]) {
        return true;
      }
    }
    return false;
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