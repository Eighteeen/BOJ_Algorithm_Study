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
    for (int operSubset = operSet; operSubset > 0; operSubset = (operSubset - 1) & operSet) {
      int theCase = 0;
      int curMinutes = 0;
      for (int oper = OPER_ALL; oper >= OPER_3K_1; oper >>= 1) {
        if ((operSubset & oper) == 0) continue;
        
        //// 왜 switch문의 theCase 계산에서는 OPER_ALL, OPER_EVEN, OPER_ODD, OPER_3K_1 상수를 사용하지 않으셨는지 궁금해요.
        //// 의미전달면에서 선언하신 상수를 사용하는 게 좋았을 것 같아요.
        switch (oper) {
          case OPER_ALL:
            theCase ^= (1+2+4+8);
            curMinutes += floors;
            break;
          case OPER_EVEN:
            theCase ^= (2+8);
            curMinutes += floors / 2;
            break;
          case OPER_ODD:
            theCase ^= (1+4);
            curMinutes += floors / 2;
            curMinutes += floors % 2;
            break;
          case OPER_3K_1:
            theCase ^= (1+8);
            curMinutes += (floors - 1) / 3 + 1;
        }
      }
      if (curMinutes > minutes) continue;
      
      //// floors < 3 하셔도 돼요.
      if (floors < 4) theCase %= 1 << floors;
      casesSet.add(theCase);
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