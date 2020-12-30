import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception{
    final int N = Input.nextInt();
    int height = 2 * N - 1; // 출력할 결과의 높이
    int width = 2 * N; // 출력할 결과의 너비
      ////for문 쓰는거 보다 훨씬 효율적인 방법인거 같아요. 배워갑니다
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height; i++) {
      int stars = calcStars(i, height);
      int spaces = width - (stars * 2);
      sb.append("*".repeat(stars))
        .append(" ".repeat(spaces))
        .append("*".repeat(stars))
        .append("\n");
    }
    System.out.print(sb);
  }

  private static int calcStars(int currentIndex, int length) {
    boolean earlyPhase = currentIndex < (length / 2);
    if (earlyPhase) {
      return currentIndex + 1;
    }
    return length - currentIndex;
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
