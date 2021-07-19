import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔해요
//// 일단 통과된 풀이이지만 문제의 조건으로 봤을 때 NEW의 조건을 체크하지 않아서
////    White와 Yellow의 결과가 맞지 않다고 생각합니다.
////    (데이터 추가에 대한 문의 답변이 나와야 확실해지겠지만요.)
//// => 피드백 고마워용!
class Main {
  public static void main(String[] args) throws Exception {
    final int NEW = Input.nextInt();
    final int LIP_ONE = Input.nextInt();

    if (NEW <= 50 && IP_ONE <= 10) {
      System.out.print("White");
    } else if (LIP_ONE >= 30) {
      System.out.print("Red");
    } else {
      System.out.print("Yellow");
    }
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