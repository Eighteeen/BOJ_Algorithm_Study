import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
  static int[] groupNumberOfEachCity;
  static int groupNumbering;
  static boolean[][] adjacencyMatrix;

  public static void main(String[] args) throws Exception {
    final int CITIES = Input.nextInt();
    final int PLANS = Input.nextInt();

    adjacencyMatrix = new boolean[CITIES][CITIES];
    for (int i = 0; i < CITIES; i++) {
      for (int j = 0; j < CITIES; j++) {
        adjacencyMatrix[i][j] = Input.nextInt() == 1;
      }
    }

    int[] plans = new int[PLANS];
    for (int i = 0; i < PLANS; i++) {
      int cityIndex = Input.nextInt() - 1;
      plans[i] = cityIndex;
    }

    groupNumberOfEachCity = new int[CITIES];
    groupNumbering = 1;
    int firstPlanGroupNumber = getGroupNumberOfCity(plans[0]);
    for (int i = 1; i < PLANS; i++) {
      if (firstPlanGroupNumber != getGroupNumberOfCity(plans[i])) {
        System.out.print("NO");
        return;
      }
    }
    System.out.print("YES");
  }

  static int getGroupNumberOfCity(int cityIndex) {
    if (groupNumberOfEachCity[cityIndex] > 0) {
      return groupNumberOfEachCity[cityIndex];
    }
    
    int groupNumber = groupNumbering++;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(cityIndex);

    while (queue.size() > 0) {
      int current = queue.poll();
      groupNumberOfEachCity[current] = groupNumber;
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
        if (adjacencyMatrix[current][i] && groupNumberOfEachCity[i] == 0) {
          queue.add(i);
        }
      }
    }

    return groupNumber;
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