import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//// 전체적으로 무난하게 잘 짜신 것 같습니다!
// 네이밍 개어렵네요.. 추천 네이밍 떠오르는거 있으면 적어주시면 감사하겠읍니다
//// 딱히 엄청 거슬린다 싶은 네이밍은 없어서 지금도 충분한 것 같습니다!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    String inputs;

    while ((inputs = Input.nextLine()) != null) {
      String[] inputArr = inputs.split(" ");
      String baseStr = inputArr[0];
      int targetNth = Integer.parseInt(inputArr[1]);

      sb.append(inputs).append(" = ");

      if (targetNth > fact(baseStr.length())) {
        sb.append("No permutation").append('\n');
        continue;
      }

      String nthPermutation = findNthPermutation(baseStr, targetNth);
      sb.append(nthPermutation).append('\n');
    }
    System.out.print(sb);
  }

  static String findNthPermutation(String baseStr, int nth) {
    int len = baseStr.length();
    if (len == 1 && nth == 1) return baseStr;

    int prevPermutationSize = fact(len - 1);
    int letterIdx = (nth - 1) / prevPermutationSize;

    char currentChar = baseStr.charAt(letterIdx);
    baseStr = excludeCharOfSpecificIndex(baseStr, letterIdx);
    nth -= (prevPermutationSize * (letterIdx));

    return currentChar + findNthPermutation(baseStr, nth);
  }

  static int fact(int x) {
    if (x == 1) return 1;
    return x * fact(x - 1);
  }

  static String excludeCharOfSpecificIndex(String str, int index) {
    return str.substring(0, index) + str.substring(index + 1);
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