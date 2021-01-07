import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
   //// 굉장히 어려운 문제라 느껴졌는데 이렇게 깔끔하고 효율적으로 짜다니 대단하요!
class Main {
  ////변수명 덕분에 읽기가 편했습니다.
  public static void main(String[] args) throws Exception {
    final String HIS_NAME = Input.nextLine();
    final int N = Input.nextInt();
    //// 서윗하시네요 로맨틱한 변수명 ^^
    String lover = "";
    int maxLove = -1;

    for(int i = 0; i < N; i++) {
      String herName = Input.nextLine();
      //// HIS_NAME은 계속 쓰이니 따로 저장하는 게 효율성면으로 좋을 것 같습니다!
     
      int l = countCharFromString('L', HIS_NAME) + countCharFromString('L', herName);
      int o = countCharFromString('O', HIS_NAME) + countCharFromString('O', herName);
      int v = countCharFromString('V', HIS_NAME) + countCharFromString('V', herName);
      int e = countCharFromString('E', HIS_NAME) + countCharFromString('E', herName);
      int love = ((l+o)*(l+v)*(l+e)*(o+v)*(o+e)*(v+e)) % 100;

      if (love > maxLove) {
        maxLove = love;
        lover = herName;
      } else if (love == maxLove) {
        lover = (lover.compareTo(herName) <= 0) ? (lover) : (herName);
      }
    }
    
    System.out.print(lover);
  }
  
  //// 깔끔하네요
  
  private static int countCharFromString(char ch, String str) {
    int count = 0;
    for(char partOfStr : str.toCharArray()) {
      if (partOfStr == ch) {
        count += 1;
      }
    }
    return count;
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
