import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  ////깔끔해서 보기가 좋습니다.
  public static void main(String[] args) throws Exception {
    final String N = Input.next();
    int length = N.length();

    String leftDigits = N.substring(0, length / 2);
    String rightDigits = N.substring(length / 2);

    int sumLeftDigits = calcSumOfDigits(leftDigits);
    int sumRightDigits = calcSumOfDigits(rightDigits);

    if (sumLeftDigits == sumRightDigits) {
      System.out.println("LUCKY");
      return;
    }
    System.out.println("READY");
  }

  private static int calcSumOfDigits(String digits) {
    int sum = 0;
    for(int i = 0; i < digits.length(); i++) {
      int digit = charToInt(digits.charAt(i));
      sum += digit;
    }
    return sum;
  }

  //// 가벼운 구현 문제에서 너무 작은 부분까지 메소드로 따로 빼셔서 오히려 보기에 힘들다는 생각이 듭니다 :22 동의합니다
  //// => ch - '0'은 아스키코드를 숫자로 바꾸는 코드라는건 아마 대부분 알아보긴 할 것 같습니다.
  //// => 그렇다해도 ch - '0'이 무엇을 의미하는지 해석하는데 시간은 분명 걸립니다. 이런 사소한 것도 쌓이면 피로가 되겠죠.
  //// => 메소드로 charToInt라는 이름을 붙임으로써 가독성이 더 높아진다고 생각합니다.
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
