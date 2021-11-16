import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

//// 깔끔해요 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int PLAYER_AMOUNT = Input.nextInt();
    final int HIS_NUMBER = Input.nextInt();

    int[] nominatedPlayer = new int[PLAYER_AMOUNT];
    for (int i = 0; i < PLAYER_AMOUNT; i++) {
      nominatedPlayer[i] = Input.nextInt();
    }

    System.out.print(getOrderToMakeHimDrink(nominatedPlayer, HIS_NUMBER));
  }

  static int getOrderToMakeHimDrink(int[] nominatedPlayer, int target) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(0);

    boolean[] visited = new boolean[nominatedPlayer.length];

    int order = 0;
    while (queue.size() > 0) {
      int current = queue.poll();
      if (visited[current]) {
        break;
      }
      visited[current] = true;

      if (current == target) {
        return order;
      }

      int next = nominatedPlayer[current];
      queue.add(next);
      order++;
    }

    return -1;
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