import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    
    String input;
    while ((input = Input.nextLine()) != null) {
      int numberOfStudents = Integer.parseInt(input);
      int[] flightsBitmask = new int[numberOfStudents];

      for (int i = 0; i < numberOfStudents; i++) {
        String flightLog = Input.nextLine();
        flightsBitmask[i] = flightLogToBitmask(flightLog);
      }

      sb.append(countDistinct(flightsBitmask))
        .append('\n');
    }

    System.out.print(sb);
  }

  static int flightLogToBitmask(String flightLog) {
    int flightBitmask = 0;
    for (char flight : flightLog.toCharArray()) {
      int flightNumber = Character.getNumericValue(flight);
      flightBitmask |= 1 << (flightNumber - 1);
    }
    return flightBitmask;
  }
  
  static long countDistinct(int[] array) {
    return Arrays.stream(array)
      .distinct()
      .count();
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