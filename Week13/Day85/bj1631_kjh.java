import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  static final int TARGET_MOVE_INDEX;
  static int moveIndex = 0;
  static Stack[] rods;
  static char[] discLocations;

  public static void main(String[] args) throws Exception {
    final int DISC = Input.nextInt();
    TARGET_MOVE_INDEX = Input.nextInt();

    final String DESTINATION = Input.nextLine();
    
    discLocations = new char[DISC];
    rods = new Stack[3];
    for (int i = DISC; i > 0; i--) {
      rods[0].push(i);
      rodsLocation[i - 1] = 'A';
    }
    
    // AAA -> CBA 등 특정상태로 이동
  }

  static void moveDiscs(int amount, char from, char tmp, char to) {
    if (moveIndex > TARGET_MOVE_INDEX) return;
    if (amount == 1) {
      int disc = rods[from - 'A'].pop();
      rods[to - 'A'].push(disc);
      discLocations[disc - 1] = to;

      if (moveIndex == TARGET_MOVE_INDEX) {
        printDiscs();
      }
      moveIndex++;
      return;
    } 

    moveDiscs(amount - 1, from, to, tmp);
    movesInfo.append(from).append(' ').append(to).append('\n');
    moveDiscs(amount - 1, tmp, from, to);
  }

  static void printDiscs() {
    StringBuilder sb = new StringBuilder();
    discLocations.forEach(discLocation -> sb.append(discLocation));
    System.out.print(sb);
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