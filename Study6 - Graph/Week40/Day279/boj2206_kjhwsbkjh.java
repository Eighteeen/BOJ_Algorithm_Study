//// 문제풀이 실패 : 메모리 초과 or 틀렸습니다를 해결할 방법을 찾지 못함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
  public static void main(String[] args) throws Exception {
    final int ROW_SIZE = Input.nextInt();
    final int COLUMN_SIZE = Input.nextInt();
    
    boolean[][] map = new boolean[ROW_SIZE][COLUMN_SIZE];

    for (int y = 0; y < ROW_SIZE; y++) {
      String row = Input.nextLine();
      
      for (int x = 0; x < COLUMN_SIZE; x++) {
        map[y][x] = row.charAt(x) == '1';
      }
    }

    int shortestDistance = solve(map);
    System.out.print(shortestDistance);
  }

  static int solve(boolean[][] map) {
    Queue<WallMapPoint> queue = new LinkedList<>();
    queue.add(new WallMapPoint(0, 0));

    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};

    int[][] shortestDistance = new int[map.length][map[0].length];
    for (int i = 0; i < shortestDistance.length; i++) {
      Arrays.fill(shortestDistance[i], 1000000);
    }

    shortestDistance[0][0] = 1;

    while (queue.size() > 0) {
      WallMapPoint current = queue.poll();
      int y = current.y;
      int x = current.x;
      
      int nextDistance = shortestDistance[y][x] + 1;

      List<WallMapPoint> candidates = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        int aroundY = y + dy[i];
        int aroundX = x + dx[i];

        boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= map.length || aroundX >= map[0].length;
        if (outOfIndex) {
          continue;
        }
        if (current.brokeWall && map[aroundY][aroundX] == true) {
          continue;
        }
        // 더 짧은 거리가 될때만 queue에 추가하면 반례가 발생함
          // 벽을 부술 수 있을 때는 나중에 거리를 줄일 수 있는 잠재력이 있어서
        // 같은 거리를 무조건 처리하면 => 메모리 초과
        // 벽이 아닌거 먼저 방문하고, 안부쉈고 같은 거리라면 방문 안하게 했는데도 => 메모리 초과

        if (shortestDistance[aroundY][aroundX] < nextDistance) {
          continue;
        }
        if (!current.brokeWall && shortestDistance[aroundY][aroundX] == nextDistance) {
          continue;
        }
        
        shortestDistance[aroundY][aroundX] = nextDistance;
        candidates.add(new WallMapPoint(aroundY, aroundX, current.brokeWall || map[aroundY][aroundX]));
      }

      candidates = candidates.stream()
        .sorted((a, b) -> (a.brokeWall ? 1 : 0) - (b.brokeWall ? 1 : 0))
        .collect(Collectors.toList());

      for (WallMapPoint candidate : candidates) {
        queue.add(candidate);
      }
    }

    int destinationDistance = shortestDistance[map.length - 1][map[0].length - 1];
    return destinationDistance == 1000000 ? -1 : destinationDistance;
  } 
}

class WallMapPoint {
  int y;
  int x;
  boolean brokeWall;

  public WallMapPoint(int y, int x) {
    this(y, x, false);
  }

  public WallMapPoint(int y, int x, boolean brokeWall) {
    this.y = y;
    this.x = x;
    this.brokeWall = brokeWall;
  }

  public String toString() {
    return String.format("(%d %d %s)", y, x, brokeWall);
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