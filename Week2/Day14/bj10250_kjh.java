import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔합니다! :22 간단하게 잘 짜셨어요
class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < T; i++) {
      int floors = Input.nextInt();
      int rooms = Input.nextInt();
      int nthGuest = Input.nextInt();
        ////오와 이렇게 하면 되는군요
      int guestFloor = ((nthGuest - 1) % floors) + 1;
      int guestRoom = nthGuest / floors;
      guestRoom += (nthGuest % floors > 0) ? (1) : (0);
      
      sb.append(guestFloor);
      if (guestRoom < 10) sb.append("0");
      sb.append(guestRoom)
        .append('\n');
    }
    
    System.out.print(sb);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}