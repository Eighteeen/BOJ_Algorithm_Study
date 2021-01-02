import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class Main {
  public static void main(String[] args) throws Exception {
    int[] oneToTwenty = IntStream.range(1, 21).toArray();
    
    for (int i = 0; i < 10; i++) {
      int startReverse = Input.nextInt() - 1;
      int endReverse = Input.nextInt() - 1;
      reverseArrayOrder(oneToTwenty, startReverse, endReverse);
    }

    printArray(oneToTwenty);
  }
    ////Reverse하는 메소드를 활용하면 더 간단하게 할 수도 있을거 같아요!
  private static void reverseArrayOrder(int[] array, int startReverse, int endReverse) {
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
