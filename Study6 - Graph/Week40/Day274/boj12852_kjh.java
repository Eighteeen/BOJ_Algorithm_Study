import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

//// 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_TO_MAKE_ONE = Input.nextInt();
    
    List<Integer> leastOperated = getLeastOperated(NUMBER_TO_MAKE_ONE);
    
    StringBuilder solution = new StringBuilder();
    solution.append(leastOperated.size() - 1).append('\n');
    for (int i = 0; i < leastOperated.size(); i++) {
      solution.append(leastOperated.get(i)).append(' ');
    }

    System.out.print(solution);
  }

  static List<Integer> getLeastOperated(int number) {
    int[] from = new int[number + 1];

    Queue<Integer> queue = new LinkedList<>();
    queue.add(number);

    boolean[] visited = new boolean[number + 1];

    while (queue.size() > 0) {
      int current = queue.poll();
      if (current == 1) {
        break;
      }

      if (current % 3 == 0 && !visited[current / 3]) {
        from[current / 3] = current;
        queue.add(current / 3);
        visited[current / 3] = true;
      }
      if (current % 2 == 0 && !visited[current / 2]) {
        from[current / 2] = current;
        queue.add(current / 2);
        visited[current / 2] = true;
      }
      if (!visited[current - 1]) {
        from[current - 1] = current;
        queue.add(current - 1);
        visited[current - 1] = true;
      }
    }

    List<Integer> leastOperated = new ArrayList<>();
    int current = 1;
    while (current < number) {
      leastOperated.add(0, current);
      current = from[current];
    }
    leastOperated.add(0, current);

    return leastOperated;
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