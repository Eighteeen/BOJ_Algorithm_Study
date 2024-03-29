import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//// 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int STONE_AMOUNT = Input.nextInt();
    List<Integer>[] adjacencyList = Stream.generate(ArrayList<Integer>::new).limit(STONE_AMOUNT).toArray(List[]::new);

    for (int i = 0; i < STONE_AMOUNT; i++) {
      int jumpDistance = Input.nextInt();
      int leftIdx = i - jumpDistance;
      int rightIdx = i + jumpDistance;

      if (leftIdx >= 0) {
        adjacencyList[i].add(leftIdx);
      }
      if (rightIdx < STONE_AMOUNT) {
        adjacencyList[i].add(rightIdx);
      }
    }

    final int STONE_START_IDX = Input.nextInt() - 1;
    System.out.print(countStonesItCanJump(adjacencyList, STONE_START_IDX));
  }

  static int countStonesItCanJump(List<Integer>[] adjacencyList, int stoneStartIdx) {
    boolean[] visited = new boolean[adjacencyList.length];
    
    visited[stoneStartIdx] = true;
    int count = 1;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(stoneStartIdx);
    visited[stoneStartIdx] = true;
    //// 굳이 adjacencyList[stoneStartIdx]에 있는 걸 미리 큐에 넣어놓을 필요가 있나? 라고 느껴져요.
    //// 그냥 queue.add(stoneStartIdx)로 간단히 추가해주고 while문으로 로직을 수행하는 게 나을 것 같습니다.
    //// => ㅋㅋㅋㅋㅋ 왜 저렇게 했지..? 감사합니다 반영했어요

    while (queue.size() > 0) {
      Integer current = queue.poll();
      
      for (Integer adjacency : adjacencyList[current]) {
        if (visited[adjacency]) {
          continue;
        }
        visited[adjacency] = true;
        queue.add(adjacency);
        count++;
      }
    }

    return count;
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