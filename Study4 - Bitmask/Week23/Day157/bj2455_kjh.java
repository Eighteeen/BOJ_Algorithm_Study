import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// perfect~
class Main {
  public static void main(String[] args) throws Exception {
    int maxPassengers = 0;
    int currentPassengers = 0;

    //// 오호 마지막에는 내리기만 하니까 3번만~ 문제 조건을 잘 활용하셨네요!
    for (int i = 0; i < 3; i++) {
      int getOut = Input.nextInt();
      int getIn = Input.nextInt();
      int passengersThisStation = -getOut + getIn;

      currentPassengers += passengersThisStation;
      maxPassengers = Math.max(currentPassengers, maxPassengers);
    }

    System.out.print(maxPassengers);
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