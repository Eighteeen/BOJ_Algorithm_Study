import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 전체적으로 깔끔하지만 main에서 updateMinMaxResult에 대한
  //// 로직을 파악하고 있어야 하는 점이 (operator가 꼭 + 혹은 - 이여야 한다는 점이 어색해보임) 아쉽고,
    //// => 상수 이름으로,
  //// updateMinMaxResult 에서 함수분리가 이루어 지면 좋을 것 같습니다. : 22 분리되면 한눈에 이해될 것 같아요
    //// => operate로 피드백 반영헀습니다!
  //// => 피드백 고마워요들!
class Main {
  static int maxResult = Integer.MIN_VALUE;
  static int minResult = Integer.MAX_VALUE;

  static int mapSize;
  static char[][] map;

  static int[] dy = { 0, 1 };
  static int[] dx = { 1, 0 };

  public static void main(String[] args) throws Exception {
    mapSize = Input.nextInt();

    map = new char[mapSize][mapSize];
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        map[i][j] = Input.nextChar();
      }
    }
  
    final char ADD_HOME_VALUE = '+';
    updateMinMaxResult(0, 0, ADD_HOME_VALUE, 0);

    System.out.printf("%d %d", maxResult, minResult);
  }

  static int operate(int operand1, char operator, int operand2) {
    int result = 0;
    if (operator == '+') {
      result = operand1 + operand2;
    } else if (operator == '-') {
      result = operand1 - operand2;
    } else if (operator == '*') {
      result = operand1 * operand2;
    }
    return result;
  }

  static void updateMinMaxResult(int y, int x, char operator, int result) {
    char current = map[y][x];

    if (current >= '0' && current <= '5') {
      result = operate(result, operator, Character.getNumericValue(current));
    } else {
      operator = current;
    }

    if (y == mapSize - 1 && x == mapSize - 1) {
      maxResult = Math.max(maxResult, result);
      minResult = Math.min(minResult, result);
      return;
    }


    for (int i = 0; i < 2; i++) {
      int aroundY = y + dy[i];
      int aroundX = x + dx[i];

      boolean outOfIndex = aroundY < 0 || aroundX < 0 || aroundY >= mapSize || aroundX >= mapSize;
      if (outOfIndex) {
        continue;
      }

      updateMinMaxResult(aroundY, aroundX, operator, result);
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
}class boj17265_kjh_fb {
    
}
