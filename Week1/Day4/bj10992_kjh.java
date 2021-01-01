import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    //// 무난하게 잘 짜신거 같아요 변수 이름들이 아주 인상깊네요
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    //// n-1값을 넣어주는게 더 좋았을 수도 있을거 같아요. : 22 lastStars도 따로있고 굳이 이 변수가 있는 이유를 잘 모르겠어요
    final int LAST_ONE_LINE = 1;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N - LAST_ONE_LINE; i++) {
      int indentWidth = N - i - 1;
      int spacesWidth = Math.max(2 * i - 1, 0);

      String indent = " ".repeat(indentWidth);
      String spaces = " ".repeat(spacesWidth);
      //// 전체적으로 변수명명이 좋은데 여기는 굳이 But을 썼어야 했나 싶어요 But이 아예 없거나 Is로 넣는게 빠른 의미 전달이 될 것 같아요
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
