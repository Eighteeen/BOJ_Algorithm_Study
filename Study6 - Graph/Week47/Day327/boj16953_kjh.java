import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

//// 깔끔 : 22 역시 이런 문제는 bfs가 빠르네요
class Main {
  public static void main(String[] args) throws Exception {
    final int A = Input.nextInt();
    final int B = Input.nextInt();
    
    System.out.print(getLeastOperationToMakeAIntoB(A, B));
  }

  static int getLeastOperationToMakeAIntoB(int a, int b) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(a);

    Map<Integer, Integer> distance = new HashMap<>();
    distance.put(a, 1);

    while (queue.size() > 0) {
      int current = queue.poll();
      int currentDistance = distance.get(current);
      if (current == b) {
        return currentDistance;
      }

      long multiplied = current * 2;
      if (multiplied <= b && !distance.containsKey(multiplied)) {
        queue.add((int) multiplied);
        distance.put((int) multiplied, currentDistance + 1);
      }
      long added = addOneInLastDigit(current);
      if (added <= b && !distance.containsKey(added)) {
        queue.add((int) added);
        distance.put((int) added, currentDistance + 1);
      }
    }

    return -1;
  }

  static long addOneInLastDigit(long num) {
    return num * 10 + 1;
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