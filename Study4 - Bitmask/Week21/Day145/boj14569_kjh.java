import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 무난무난
class Main {
  public static void main(String[] args) throws Exception {
    int numberOfSubjects = Input.nextInt();
    long[] requiredPeriodsOfSubjects = new long[numberOfSubjects];
    for (int i = 0; i < numberOfSubjects; i++) {
      int numberOfPeriods = Input.nextInt();
      for (int j = 0; j < numberOfPeriods; j++) {
        int requiredPeriod = Input.nextInt();
        requiredPeriodsOfSubjects[i] |= (1L << (requiredPeriod - 1));
      }
    }

    int numberOfStudents = Input.nextInt();
    long[] freePeriodsOfStudents = new long[numberOfStudents];
    for (int i = 0; i < numberOfStudents; i++) {
      int numberOfFreePeriods = Input.nextInt();
      for (int j = 0; j < numberOfFreePeriods; j++) {
        int freePeriod = Input.nextInt();
        freePeriodsOfStudents[i] |= (1L << (freePeriod - 1));
      }
    }

    solve(requiredPeriodsOfSubjects, freePeriodsOfStudents);
  }

  static void solve(long[] requiredPeriodsOfSubjects, long[] freePeriodsOfStudents) {
    StringBuilder sb = new StringBuilder();

    for (long freePeriodsOfAStudent : freePeriodsOfStudents) {
      int amountOfCanTake = 0;
      for (long requiredPeriods : requiredPeriodsOfSubjects) {
        if (canTake(freePeriodsOfAStudent, requiredPeriods)) {
          amountOfCanTake++;
        }
      }
      sb.append(amountOfCanTake)
        .append('\n');
    }

    System.out.print(sb);
  }

  static boolean canTake(long freePeriods, long requiredPeriods) {
    long periodsCanTake = freePeriods & requiredPeriods;
    return periodsCanTake == requiredPeriods;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
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
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}