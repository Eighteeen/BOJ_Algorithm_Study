import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
  //무난하게 잘 짠거 같습니다.
class Main {
  public static void main(String[] args) throws Exception {
    final String N = Input.nextLine();
    int[] needNumbers = new int[9];
    ////배워갑니다.
    Arrays.fill(needNumbers, 0);

    final int SIX_OR_NINE = 6;
    for(int i = 0; i < N.length(); i++) {
      ////이것도 배워갑니다.
      int num = charToInt(N.charAt(i));
      //// 삼항연산자 하나하나마다 괄호를 넣은 이유가 뭔가요??
      //// => 가독성을 위함입니다!
      //// => 괄호로 묶음으로써 각 항이 명확히 구별하고
      //// => ? : 양옆을 띄움으로써 삼항연산자가 눈에 띄게 했어요
      int needNumberIndex = (num == 9) ? (SIX_OR_NINE) : (num);
      needNumbers[needNumberIndex] += 1;
    }

    //// 구현 관점이 달라서 나타나는 문제지만 9일때도 6으로 들어가게 해서 나중에 2로 나누는 게 저는 좀 어색해보이네요 ㅜ
    //// 하지만 이런 방법도 있구나 하고 배워가기도 합니다!
    //// => 요건 신기한 관점의 차이인걸로 ㅎㅎ
    needNumbers[SIX_OR_NINE] = (int) Math.ceil(needNumbers[SIX_OR_NINE] / 2.0);

    int needSet = calcMax(needNumbers);
    System.out.print(needSet);
  }

  private static int charToInt(char ch) {
    return ch - '0';
  }

  private static int calcMax(int[] intArr) {
    int max = intArr[0];
    for(int i = 1; i < intArr.length; i++) {
      if (intArr[i] > max) {
        max = intArr[i];
      }
    }
    return max;
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