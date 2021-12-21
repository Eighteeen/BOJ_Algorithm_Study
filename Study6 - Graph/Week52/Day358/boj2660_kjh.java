import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

//// 깔끔해요 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int MEMBER_AMOUNT = Input.nextInt();
    
    List<Integer>[] adjacencyList = Stream.generate(ArrayList::new).limit(MEMBER_AMOUNT + 1).toArray(ArrayList[]::new);
    while (true) {
      int nodeA = Input.nextInt();
      int nodeB = Input.nextInt();
      if (nodeA + nodeB == -2) {
        break;
      }

      adjacencyList[nodeA].add(nodeB);
      adjacencyList[nodeB].add(nodeA);
    }

    List<Integer> presidentCandidates = new ArrayList<>();
    int presidentScore = Integer.MAX_VALUE;

    for (int i = 1; i <= MEMBER_AMOUNT; i++) {
      int score = getScore(adjacencyList, i);
      if (score > presidentScore) {
        continue;
      }
      if (score < presidentScore) {
        presidentCandidates = new ArrayList<>();
        presidentScore = score;
      }
      presidentCandidates.add(i);
    }

    System.out.printf("%d %d\n", presidentScore, presidentCandidates.size());
    System.out.print(presidentCandidates.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }

  static int getScore(List<Integer>[] adjacencyList, int member) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(member);

    int[] distance = new int[adjacencyList.length + 1];
    Arrays.fill(distance, -1);
    distance[member] = 0;

    int score = 1;
    while (queue.size() > 0) {
      int current = queue.poll();

      for (int adjacency : adjacencyList[current]) {
        if (distance[adjacency] > -1) {
          continue;
        }
        distance[adjacency] = distance[current] + 1;
        score = Math.max(score, distance[adjacency]);
        queue.add(adjacency);
      }
    }

    return score;
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