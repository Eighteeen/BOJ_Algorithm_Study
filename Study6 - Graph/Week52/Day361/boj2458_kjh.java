import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    final int STUDENT_AMOUNT = Input.nextInt();
    boolean[][] comparableMatrix = new boolean[STUDENT_AMOUNT + 1][STUDENT_AMOUNT + 1];

    final int COMPARISON_AMOUNT = Input.nextInt();
    for (int i = 0; i < COMPARISON_AMOUNT; i++) {
      int shortStudent = Input.nextInt(); 
      int tallStudent = Input.nextInt();
      comparableMatrix[shortStudent][tallStudent] = true;
    }

    updateComparableMatrixByFloyd(comparableMatrix);

    int allComparableStudents = 0;
    for (int i = 1; i <= STUDENT_AMOUNT; i++) {
      int comparableCount = 0;
      for (int j = 1; j <= STUDENT_AMOUNT; j++) {
        if (comparableMatrix[i][j]) {
          comparableCount++;
        }
        if (comparableMatrix[j][i]) {
          comparableCount++;
        }
      }

      if (comparableCount == STUDENT_AMOUNT - 1) {
        allComparableStudents++;
      }
    }

    System.out.print(allComparableStudents);
  }

  //// 이런 방법도 있었네요 배워가요
  static void updateComparableMatrixByFloyd(boolean[][] comparableMatrix) {
    int STUDENT_AMOUNT = comparableMatrix.length - 1;

    for (int waypoint = 1; waypoint <= STUDENT_AMOUNT; waypoint++) {
      for (int begin = 1; begin <= STUDENT_AMOUNT; begin++) {
        for (int end = 1; end <= STUDENT_AMOUNT; end++) {
          if (comparableMatrix[begin][waypoint] && comparableMatrix[waypoint][end]) {
            comparableMatrix[begin][end] = true;
          }
        }
      }
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