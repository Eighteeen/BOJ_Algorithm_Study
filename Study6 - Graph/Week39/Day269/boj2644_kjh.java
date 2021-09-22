import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.LinkedList;

class Main {
  public static void main(String[] args) throws Exception {
    final int PEOPLE_AMOUNT = Input.nextInt();
    final int[] TARGET_TWO_PEOPLE = { Input.nextInt(), Input.nextInt() };

    List<Integer>[] adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(PEOPLE_AMOUNT + 1).toArray(List[]::new);

    final int RELATION_AMOUNT = Input.nextInt();
    for (int i = 0; i < RELATION_AMOUNT; i++) {
      int personA = Input.nextInt();
      int personB = Input.nextInt();

      adjacencyList[personA].add(personB);
      adjacencyList[personB].add(personA);
    }

    int degreeOfKinship = getShortestDistance(adjacencyList, TARGET_TWO_PEOPLE[0], TARGET_TWO_PEOPLE[1]);
    System.out.print(degreeOfKinship);
  }

  static int getShortestDistance(List<Integer>[] adjacencyList, int from, int to) {
    int[] shortestDistance = new int[adjacencyList.length];
    Arrays.fill(shortestDistance, -1);
    shortestDistance[from] = 0;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(from);

    while (queue.size() > 0) {
      int current = queue.poll();

      for (int relative : adjacencyList[current]) {
        if (shortestDistance[relative] == -1) {
          queue.add(relative);
          shortestDistance[relative] = shortestDistance[current] + 1;
          continue;
        }
        shortestDistance[relative] = Math.min(shortestDistance[relative], shortestDistance[current] + 1);
      }
    }

    return shortestDistance[to];
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