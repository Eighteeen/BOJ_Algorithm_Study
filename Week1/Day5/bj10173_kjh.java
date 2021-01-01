import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    String sentence;

    StringBuilder sb = new StringBuilder();
    while( !(sentence = Input.nextLine()).equals("EOI") ) {
      if (existsNemo(sentence)) {
        sb.append("Found")
          .append("\n");
        continue;
      }
      
      sb.append("Missing")
        .append("\n");
    }
    System.out.print(sb);
  }

  // 문자열 검색까지 직접 구현하려했는데 귀찮아서 포기 ㅎㅎ
  private static boolean existsNemo(String sentence) {
    sentence = sentence.toLowerCase();
    if (sentence.indexOf("nemo") >= 0) {
      return true;
    }
    return false;
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