import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  static int[] seatsBitmask;

  public static void main(String[] args) throws Exception {
    final int NUMBER_OF_TRAINS = Input.nextInt();
    final int NUMBER_OF_COMMANDS = Input.nextInt();

    seatsBitmask = new int[NUMBER_OF_TRAINS];

    for (int i = 0; i < NUMBER_OF_COMMANDS; i++) {
      int command = Input.nextInt();
      int train = Input.nextInt();
      int seat = 0;

      switch (command) {
        case 1:
          seat = Input.nextInt();
          getIn(train, seat); break;
        case 2:
          seat = Input.nextInt();
          getOut(train, seat); break;
        case 3:
          moveAllBack(train); break;
        case 4:
          moveAllFront(train); break;
      }
    }
    System.out.print(Arrays.stream(seatsBitmask)
      .distinct()
      .count()
    );
  }

  static void getIn(int train, int seat) {
    seatsBitmask[train - 1] |= 1 << (seat - 1);
  }

  static void getOut(int train, int seat) {
    seatsBitmask[train - 1] &= ~(1 << (seat - 1));
  }

  static void moveAllBack(int train) {
    seatsBitmask[train - 1] <<= 1;
    if (seatsBitmask[train - 1] >= (1 << 20)) seatsBitmask[train - 1] -= (1 << 20);
  }

  static void moveAllFront(int train) {
    seatsBitmask[train - 1] >>= 1;
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