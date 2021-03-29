import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();

    //// 아예 수를 계산하여 변수 설정을 해야하는 점이 아쉽습니다.
    //// 특히 MAX_RANGE_END 변수의 경우에 정확히 수를 계산해야하고 문제없이 코드만 봤을 때 무슨 수인지 쉽게 유추할 수 없다는 점이 아쉽습니다.
    //// 미리 한계치를 계산하지 않는 방법으로도 풀이해보시면 좋을 것 같습니다.
    final int MAX_STEP = 27;
    final int MAX_RANGE_START = 1;
    final int MAX_RANGE_END = 1073741792;
    System.out.print(searchNthMoo(N, MAX_STEP, MAX_RANGE_START, MAX_RANGE_END));
  }

  static char searchNthMoo(int target, int step, int rangeStart, int rangeEnd) {
    int numberOfChars = rangeEnd - rangeStart + 1;
    
    //// 해당 조건문 없이 솔브됩니다.
    if (numberOfChars == 3) {
      return (rangeStart == target) ? 'm' : 'o';
    }
  
    int previousStepNumberOfChars = (numberOfChars - (step + 3)) / 2;
    int startMiddle = rangeStart + previousStepNumberOfChars;
    int endMiddle = startMiddle + step + 2;
    if (target == startMiddle) {
      return 'm';
    }
    if (target > startMiddle && target <= endMiddle) {
      return 'o';
    }

    if (target < startMiddle) {
      return searchNthMoo(target, step - 1, rangeStart, startMiddle - 1);
    } else {
      return searchNthMoo(target, step - 1, endMiddle + 1, rangeEnd);
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