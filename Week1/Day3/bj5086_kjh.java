import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    while (true) {
      int num1 = Input.nextInt();
      int num2 = Input.nextInt();
      if (num1 == 0 && num2 == 0) break;

      if (isValidFactor(num1, num2)) {
        sb.append("factor\n");
        continue;
      }
      if (isValidMultiple(num1, num2)) {
        sb.append("multiple\n");
        continue;
      }
      sb.append("neither\n");
    }
    System.out.print(sb);
  }

  //// 메소드 활용도 좋지만 너무 세부적으로 나눈 것 같아요. 오히려 주석 한줄이 더 나을 수도 있다는 생각입니다 :22 if문으로만 짜는게 더 보기 편할
  //// => 코드를 처음 보는 사람이 (num1 % num2 == 0)이라는 조건식이 "num1이 num2의 배수다"라는 의미라는걸 바로 알아볼 수 있을까요?
  //// => isValidFactor라는 이름을 붙여 의도를 표현하는 쪽이 가독성이 높다고 생각합니다.
  private static boolean isValidFactor(int factor, int num) {
    return ( (num % factor) == 0 );
  }
  
  private static boolean isValidMultiple(int multiple, int num) {
    return ( (multiple % num) == 0 );
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
