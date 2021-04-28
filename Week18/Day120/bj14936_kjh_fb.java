import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

class Main {
  static final int OPER_ALL = 8;
  static final int OPER_EVEN = 4;
  static final int OPER_ODD = 2;
  static final int OPER_3K_1 = 1;

  public static void main(String[] args) throws Exception {
    final int FLOORS = Input.nextInt();
    final int MINUTES = Input.nextInt();

    System.out.print(countCases(FLOORS, MINUTES));
  }

  static int countCases(int floors, int minutes) {
    Set<Integer> casesSet = new HashSet<>();
    casesSet.add(0);

    int operSet = OPER_ALL + OPER_EVEN + OPER_ODD + OPER_3K_1;
    //// operSubset-- 으로 간단하게 나타낼 수 있는데 operSubset = (operSubset - 1) & operSet 은 복잡해보여요.
    //// => 전체에 대한 부분집합은 어차피 그냥 -1한것과 똑같네요 참
    //// => 비트마스크 부분집합 구하는 공식의 간결함에 푹 빠져서 그걸 간과했어요 ㅎㅎㅋ 
    for (int operSubset = operSet; operSubset > 0; operSubset--) {
      int enabledFloorBtns = 0;
      int curMinutes = 0;
      for (int oper = OPER_ALL; oper >= OPER_3K_1; oper >>= 1) {
        if ((operSubset & oper) == 0) continue;
        
        //// 왜 switch문의 theCase 계산에서는 OPER_ALL, OPER_EVEN, OPER_ODD, OPER_3K_1 상수를 사용하지 않으셨는지 궁금해요.
        //// 의미전달면에서 선언하신 상수를 사용하는 게 좋았을 것 같아요.
        //// => 완전히 다른 뜻으로 쓰입니다. theCase는 4층 중 몇층을 눌렀는지를 나타내요. OPER_???은 취할 수 있는 동작 네가지이고요
        //// => (4층인 이유는, 5층, 100층, 1000층이여도 4층으로 치고 계산해도 경우의 수는 같음을 이용했습니다)

        //// => 변수명을 좀 더 직관적으로 변경했습니다!
        switch (oper) {
          case OPER_ALL:
            enabledFloorBtns ^= (1+2+4+8);
            curMinutes += floors;
            break;
          case OPER_EVEN:
            enabledFloorBtns ^= (2+8);
            curMinutes += floors / 2;
            break;
          case OPER_ODD:
            enabledFloorBtns ^= (1+4);
            curMinutes += floors / 2;
            curMinutes += floors % 2;
            break;
          case OPER_3K_1:
            enabledFloorBtns ^= (1+8);
            curMinutes += (floors - 1) / 3 + 1;
        }
      }
      if (curMinutes > minutes) continue;
      
      //// floors < 3 하셔도 돼요.
      //// => 아앗ㅋㅋ 감사합니다
      if (floors < 3) enabledFloorBtns %= 1 << floors;
      casesSet.add(enabledFloorBtns);
    }

    return casesSet.size();
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }

  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
  }

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
  }

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
  }

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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
    return new StringTokenizer(nextLine(), " ");
  }
}