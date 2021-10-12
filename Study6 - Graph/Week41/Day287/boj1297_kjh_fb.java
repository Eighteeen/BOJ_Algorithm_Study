import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int DIAGNOAL_LENGTH = Input.nextInt();
    final int HEIGHT_RATIO = Input.nextInt();
    final int WIDTH_RATIO = Input.nextInt();

    double oneRatio = square(DIAGNOAL_LENGTH);
    oneRatio /= square(HEIGHT_RATIO) + square(WIDTH_RATIO);
    oneRatio = Math.sqrt(oneRatio);

    int height = (int) (HEIGHT_RATIO * oneRatio);
    int width = (int) (WIDTH_RATIO * oneRatio);
    System.out.printf("%d %d", height, width);
  }

  //// square은 함수 이름으로 오해가 있을 수 있을 것 같아요. : 22
  //// square 단어를 이용하고 싶다면 getSquared나, power 단어를 이용하는 것도 좋을 것 같아요.
  //// => 올.. getSquared 피드백 고마워요!
  static int getSquared(int num) {
    return num * num;
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