import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class Main {
  public static void main(String[] args) throws Exception {
    ////이런 방법이있었네요 배워갑니다. : 22 python에만 range를 사용했었는데 java도 이런 메소드가 있는지 몰랐네요. 배워갑니다!
    //// => 뿌-듯
    int[] oneToTwenty = IntStream.range(1, 21).toArray();
    
    for (int i = 0; i < 10; i++) {
      int startReverse = Input.nextInt() - 1;
      int endReverse = Input.nextInt() - 1;
      reverseArrayOrder(oneToTwenty, startReverse, endReverse);
    }

    printArray(oneToTwenty);
  }
    //// Reverse하는 메소드를 활용하면 더 간단하게 할 수도 있을거 같아요! : 22
    //// => 이미 직접 구현해놔서 버리긴 아깝네요 ㅎㅎ;; 다음엔 써먹어볼게요. reverse 메소드 있는 점 알려주셔서 감사합니당!
  private static void reverseArrayOrder(int[] array, int startReverse, int endReverse) {
    //// copyOfRange() 메소드 배워갑니다 처음 봤어요
    int[] copiedTarget = Arrays.copyOfRange(array, startReverse, endReverse + 1);

    int reverseCount = endReverse - startReverse + 1;
    for(int i = 0; i < reverseCount; i++) {
      int reversedOrder = copiedTarget.length - 1 - i;
      array[startReverse + i] = copiedTarget[reversedOrder];
    }
  }

  private static void printArray(int[] array) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < array.length; i++) {
      sb.append(array[i])
        .append(' ');
    }
    System.out.println(sb);
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
