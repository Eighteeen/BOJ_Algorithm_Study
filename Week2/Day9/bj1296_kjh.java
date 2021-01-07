import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final String HIS_NAME = Input.nextLine();
    final int N = Input.nextInt();
    //// 서윗하시네요 로맨틱한 변수명 ^^
    String lover = "";
    int maxLove = -1;

    for(int i = 0; i < N; i++) {
      String herName = Input.nextLine();
      //// HIS_NAME은 계속 쓰이니 따로 저장하는 게 효율성면으로 좋을 것 같습니다!
      ////밑에 메소드의 매개변수ch를 각각 LOVE를 넣고 str부분에는 첫번째 입력값을 넣고 + 두번째 이름값을 넣습니다
      ////메소드 안에 if문이 true가 되면 LOVE의 두 입력값의 count가 증가하는 for문 같습니다. 
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
  ////이 메소드는 매개 변수를 ch와 str을 받아서 for-each 문을 사용해 str을 char형으로 쪼개서 partOfStr에 담고
  ////그 담은거랑 매개변수 ch가 같으면 count를1씩 증가시키는 메소드 같습니다.
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