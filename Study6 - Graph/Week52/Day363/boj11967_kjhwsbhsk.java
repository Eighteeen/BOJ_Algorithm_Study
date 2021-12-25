import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

class Main {
  static final boolean LIGHT_ON = true;
  static final boolean LIGHT_OFF = false;

  public static void main(String[] args) throws Exception {
    final int ROOM_SIZE = Input.nextInt();
    final int BUTTON_AMOUNT = Input.nextInt();

    Map<Coordinate, List<Coordinate>> buttonsRoomHas = new HashMap<>();

    for (int i = 0; i < BUTTON_AMOUNT; i++) {
      Coordinate room = new Coordinate(Input.nextInt(), Input.nextInt());
      Coordinate button = new Coordinate(Input.nextInt(), Input.nextInt());

      if (!buttonsRoomHas.containsKey(room)) {
        buttonsRoomHas.put(room, new ArrayList<Coordinate>());
      }
      buttonsRoomHas.get(room).add(button);
    }

    int maxRoomsLightOn = getMaxRoomsLightOn(ROOM_SIZE, buttonsRoomHas);
    System.out.print(maxRoomsLightOn);
  }

  static int getMaxRoomsLightOn(int roomSize, Map<Coordinate, List<Coordinate>> buttonsRoomHas) {
    boolean[][] rooms = new boolean[roomSize + 1][roomSize + 1];
    boolean[][] visited = new boolean[roomSize + 1][roomSize + 1];
    List<Coordinate> notVisitedRoomsLightOn = new ArrayList<>();

    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(new Coordinate(1, 1));
    rooms[1][1] = LIGHT_ON;

    int[] dy = { -1, 1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };

    while (queue.size() > 0) {
      Coordinate currentRoom = queue.poll();

      visited[currentRoom.y][currentRoom.x] = true;
      notVisitedRoomsLightOn.remove(currentRoom);

      // 킬 수 있는 불을 다 킨다
      if (buttonsRoomHas.containsKey(currentRoom)) {
        for (Coordinate button : buttonsRoomHas.get(currentRoom)) {
          rooms[button.y][button.x] = LIGHT_ON;
          // notVisi~ 에 그놈들 추가
          notVisitedRoomsLightOn.add(button);
        }
      }

      // 갈 수 있는 곳이 있다면
      for (int i = 0; i < 4; i++) {
        int aroundY = currentRoom.y + dy[i];
        int aroundX = currentRoom.x + dx[i];

        boolean outOfIndex = aroundY < 1 || aroundX < 1 || aroundY > roomSize || aroundX > roomSize;
        if (outOfIndex || visited[aroundY][aroundX] || rooms[aroundY][aroundX] == LIGHT_OFF) {
          continue;
        }

        // 다 queue에 추가한다
        queue.add(new Coordinate(aroundY, aroundX));
      }

      // 갈 수 있는 곳이 없다면
      if (queue.size() > 0) {
        continue;
      }

      
      for (Coordinate notVisitedRoom : notVisitedRoomsLightOn) {
        // 지가 visited=true라면 지움
        // if (visited[notVisitedRoom.y][notVisitedRoom.x]) {
        //   it.remove();
        //   continue;
        // }

        // notVisi~ 에서 주변이 visited=true가 된놈이 있다면
        for (int i = 0; i < 4; i++) {
          int aroundY = notVisitedRoom.y + dy[i];
          int aroundX = notVisitedRoom.x + dx[i];

          boolean outOfIndex = aroundY < 1 || aroundX < 1 || aroundY > roomSize || aroundX > roomSize;
          if (outOfIndex) {
            continue;
          }
          
          if (visited[aroundY][aroundX]) {
            queue.add(notVisitedRoom);
            it.remove();
            break;
          }
        }
      }
    }

    // 몇 방 켜져있냐?
    int roomsLightOn = 0;
    for (int y = 1; y <= roomSize; y++) {
      for (int x = 1; x <= roomSize; x++) {
        if (rooms[y][x] == LIGHT_ON) {
          roomsLightOn++;
        }
      }
    }

    return roomsLightOn;
  }
}

class Coordinate {
  public int y;
  public int x;
  
  public Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
  }

  // equals
  @Override
  public boolean equals(Object o) {
    if (o instanceof Coordinate == false) {
      return false;
    }

    Coordinate coord = (Coordinate) o;
    return coord.y == y && coord.x == x;
  }

  @Override
  public int hashCode() {
      return Objects.hash(y, x);
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