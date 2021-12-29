import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 : 22
class Main {
  //// 주석보다는 visited를 "몇번째 날, 몇번째 떡"의 의미를 담을 수 있도록 작성했으면 좋았을 것 같습니다. : 22
  //// (주석을 써서 말한거고 안썼으면 피드백 안달았을 것 같아요) : 22
  static int[] choosingCakes;
  static boolean[][] visited;
  static int[] chosenCakes;
  
  public static void main(String[] args) throws Exception {
    final int LAST_DAY = Input.nextInt();

    List<Integer>[] dailyCakes = new ArrayList[LAST_DAY + 1];
    for (int i = 1; i <= LAST_DAY; i++) {
      dailyCakes[i] = new ArrayList<>();

      int cakes = Input.nextInt(); 
      for (int j = 0; j < cakes; j++) {
        int cake = Input.nextInt();
        dailyCakes[i].add(cake);
      }
    }
    
    choosingCakes = new int[LAST_DAY + 1];
    visited = new boolean[LAST_DAY + 2][10];
    chosenCakes = null;

    chooseCakes(dailyCakes, 0, 1, LAST_DAY);

    if (chosenCakes == null) {
      System.out.print(-1);
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= LAST_DAY; i++) {
      sb.append(chosenCakes[i]).append('\n');
    }

    System.out.print(sb);
  }

  static void chooseCakes(List<Integer>[] dailyCakes, int prevCake, int day, int lastDay) {
    if (day == lastDay + 1) {
      chosenCakes = Arrays.copyOf(choosingCakes, choosingCakes.length);
      return;
    }

    for (int cake : dailyCakes[day]) {
      if (visited[day + 1][cake] || cake == prevCake) {
        continue;
      }
      visited[day + 1][cake] = true;

      choosingCakes[day] = cake;
      chooseCakes(dailyCakes, cake, day + 1, lastDay);
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
}