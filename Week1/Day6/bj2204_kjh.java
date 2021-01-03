import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  ////깔끔하게 잘 짠거 같슴니다. : 22
class Main {
  public static void main(String[] args) throws Exception {
    int testCases;
    StringBuilder sb = new StringBuilder();
    //// 선언부와 구현부는 개행이 있어야 한번에 쭉 읽기 좋을 것 같아요
    while( (testCases = Input.nextInt()) != 0 ) {
      String frontWord = Input.nextLine();
      for(int i = 1; i < testCases; i++) {
        String word = Input.nextLine();
        
        boolean isFrontWord = word.toLowerCase().compareTo(frontWord.toLowerCase()) < 0;
        if (isFrontWord) {
          frontWord = word;
        }
      }
      sb.append(frontWord).append('\n');
    }
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