import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  ////약간 복잡해 보이지만 체계적인 코드인거 같습니다.
class Main {
  public static void main(String[] args) throws Exception {
    String sentence = Input.nextLine();
    sentence = sentence.trim();

    if (sentence.equals("")) {
      System.out.print(0);
      return;
    }
    
    int wordCount = calcCharCountFromString(' ', sentence) + 1;
    System.out.print(wordCount);
  }

  //// 활용성이 좋네요 굿
  private static int calcCharCountFromString(char targetChar, String string) {
    int charCount = 0;
    ////string.toCharArray() 배워갑니다!
    for(char ch : string.toCharArray()) {
      if (ch == targetChar) {
        charCount++;
      }
    }
    return charCount;
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
