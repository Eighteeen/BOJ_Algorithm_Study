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

  //// while문 안의 if문에서 current를 연산해주는 부분이 꽤나 있어서 변수로 저장하고 써주는 것은 어떨까요?
  //// 연산도 줄여지고 소스도 더 정돈된 느낌일 것 같아요
  //// => 시간도 줄었음 굳ㅋ 피드백 고마워요!
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

      boolean[] isExistAdjacency = { current % 3 == 0, current % 2 == 0, true };
      int[] adjacencies = { current / 3, current / 2, current - 1 };

      for (int i = 0; i < 3; i++) {
        if (!isExistAdjacency[i]) {
          continue;
        }
        
        int adjacency = adjacencies[i];
        if (visited[adjacency]) {
          continue;
        }
        from[adjacency] = current;
        queue.add(adjacency);
        visited[adjacency] = true;
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