import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    int height = N * 2;

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < height; i++) {
      boolean starFirst = ( (i % 2) == 0 );
      sb.append(buildWaterDots(N, starFirst))
        .append('\n');
    }
    System.out.print(sb);
  }

  //// 굳이 하나하나씩 조건문을 돌릴 필요 없을 것 같아요. 좀 더 효율성 좋은 구현 방법이 있다고 생각합니다
  //// => 좋은 피드백 감사합니다.
  private static String buildWaterDots(int width, boolean starFirst) {
    if (starFirst) {
      String addStarIfOdd = (width % 2) == 1 ? "*" : "";
      return "* ".repeat(width / 2) + addStarIfOdd;
    }
    return " *".repeat(width / 2);
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
