import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static List<WhereIsM> mLoopList;
  static int N;

  public static void main(String[] args) throws Exception {
    N = Input.nextInt();
    mLoopList = new ArrayList<>();
    System.out.print(isM(1) ? "m" : "o");
  }

  static boolean isM(int n) {
    if (n == N) return true;
    if (n > N) return false;

    int player = 0;

    for (WhereIsM mLoop : mLoopList) {
      mLoop.nextOrder();
      if (mLoop.canJoin()) {
        player = mLoop.getPlayer();
        mLoop.join();
      }
    }
    
    if (player == 0) {
      player = mLoopList.size() + 3;
      mLoopList.add(new WhereIsM(player));
    }

    return isM(n + player);
  }
}

class WhereIsM {
  private int player;
  private int ruleSeq;
  private int orderIdx;

  public WhereIsM(int player) {
    this.player = player;
    ruleSeq = (int) Math.pow(2, player - 2);
    orderIdx = 0;
  }

  public boolean canJoin() {
    return ruleSeq == orderIdx;
  }

  public void nextOrder() {
    orderIdx++;
  }

  public void join() {
    orderIdx = 0;
  }

  public int getPlayer() {
    return player;
  }

  @Override
  public String toString() {
    return "[" + player + ", " + ruleSeq + ", " + orderIdx + "]";
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