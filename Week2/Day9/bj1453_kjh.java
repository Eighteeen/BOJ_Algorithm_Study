import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 무난하고 깔끔하네요
class Main {
  public static void main(String[] args) throws Exception {
    boolean[] seats = new boolean[100];
    ////boolean 형으로 배열 선언하고 false로 초기화하는거 배워갑니다.
    Arrays.fill(seats, false);

    final int N = Input.nextInt();
    int refused = 0;
  
    
    for(int i = 0; i < N; i++) {
      int seatNum = Input.nextInt();
      if (seats[seatNum - 1] == false) {
        seats[seatNum - 1] = true;
      } else {
        refused += 1;
      }
    }

    System.out.print(refused);
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