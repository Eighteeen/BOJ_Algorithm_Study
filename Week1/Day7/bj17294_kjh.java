import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔하네요
class Main {
  public static void main(String[] args) throws Exception {
    final String K = Input.nextLine();
    boolean isCute = isCute(K);

    if (isCute) {
      System.out.print("◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!");
      return;
    }
    System.out.print("흥칫뿡!! <(￣ ﹌ ￣)>");
  }

  ////메소드를 잘 활용하시는거 같아서 부럽습니다.
  private static boolean isCute(String strNum) {
    if (strNum.length() == 1) {
      return true;
    }

    int commonDifference = charToInt(strNum.charAt(0)) - charToInt(strNum.charAt(1));
    for(int i = 1; i < strNum.length() - 1; i++) {
      int current = charToInt(strNum.charAt(i));
      int next = charToInt(strNum.charAt(i + 1));

      if( (current - next) != commonDifference) {
        return false;
      }
    }
    return true;
  }

  private static int charToInt(char ch) {
    return ch - '0';
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