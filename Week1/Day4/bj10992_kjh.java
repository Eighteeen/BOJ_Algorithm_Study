import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    
    final int LAST_ONE_LINE = 1;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N - LAST_ONE_LINE; i++) {
      int indentWidth = N - i - 1;
      int spacesWidth = Math.max(2 * i - 1, 0);

      String indent = " ".repeat(indentWidth);
      String spaces = " ".repeat(spacesWidth);
      String starButNotInFirst = (i == 0) ? ("") : ("*");
      sb.append(indent)
        .append('*').append(spaces).append(starButNotInFirst)
        .append('\n');
    }

    String lastStars = "*".repeat(2 * N - 1);
    sb.append(lastStars);
    
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