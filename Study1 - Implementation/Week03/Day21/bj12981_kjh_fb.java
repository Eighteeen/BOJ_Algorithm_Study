import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
//// 구현이 섬세하네요 메인은 짧아졌지만 함수가 좀 길어진 느낌이 있어요. : 22 문제에 비해서 구현 코드가 긴 느낌이 있네요
//// => 줄여봤습니다! 이런 규칙 찾기 문제에 제가 좀 약하긴 한거같네요 ㅠㅠㅋ
class Main {
  public static void main(String[] args) throws Exception {
    int[] colors = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int boxes = 0;

    boxes += packByThree(colors);
    boxes += packByTwoAndOne(colors);

    System.out.print(boxes);
  }

  static int packByThree(int[] colors) {
    int boxes = 0;

    int minColor = (colors[0] < colors[1]) ? colors[0] : colors[1];
    minColor = (minColor < colors[2]) ? minColor : colors[2];

    for (int i = 0; i < 3; i++) {
      colors[i] -= minColor;
    }
    boxes += minColor;

    for (int i = 0; i < 3; i++) {
      if (colors[i] < 3) continue;
    
      boxes += colors[i] / 3;
      colors[i] %= 3;
    }
    
    return boxes;
  }

  static int packByTwoAndOne(int[] colors) {
    int maxColorBalls = Arrays.stream(colors).max().getAsInt();
    int sumColorBalls = (int) Arrays.stream(colors).sum();
    
    return (sumColorBalls == 2) ? (1) : (maxColorBalls);
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

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
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
    return new StringTokenizer(nextLine(), " ");
  }
}
