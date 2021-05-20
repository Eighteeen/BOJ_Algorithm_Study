import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final String BASE_CODE = Input.next();
    final long N = Input.nextLong();

    System.out.print(nthOfCode(BASE_CODE, N, 0));
  }

  //// baseCode가 함수 안에서 가공되는 게 아니라서 static 변수로 len을 사용하는 것도 좋을 것 같습니다.
  //// 하지만 함수 인자로 code를 받는 것 자체를 의도했다면 이것도 좋을 것 같아요.
  //// => 고냥 개인적으로 static 변수 안써보고 싶었어요!
  static char nthOfCode(String baseCode, long n, int step) {
    int len = baseCode.length();
    //// step == 0 조건을 지워도 정상 작동 됩니다.
    //// => 맞다 그르네요 피드백 고마워요!
    if (n <= len) {
      return baseCode.charAt((int) n - 1);
    }

    long curSize = len * (long) Math.pow(2, step);
    if (n > curSize) {
      return nthOfCode(baseCode, n, step + 1);
    }

    long prevSize = curSize / 2;
    if (n <= prevSize) return nthOfCode(baseCode, n, step - 1);

    n -= prevSize;
    if (n == 1) return nthOfCode(baseCode, prevSize, step - 1);
    //// n == 2 조건문 없이도 똑같이 작동합니다.
    //// => ㅋㅋㅋ 맞네요 고마워요!
    return nthOfCode(baseCode, n - 1, step - 1);
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