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

  private static StringBuilder buildWaterDots(int width, boolean printStar) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < width; i++) {
      if (printStar) {
        sb.append('*');
      }
      else {
        sb.append(' ');
      }
      printStar = !printStar;
    }
    return sb;
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