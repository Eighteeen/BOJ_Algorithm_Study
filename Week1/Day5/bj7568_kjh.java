import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    int[][] bodies = new int[N][2];
    int[] ranks = new int[N];
    Arrays.fill(ranks, 1);
    //// 확실히 가독성이 좋아지네요 이런 변수선언이.
    final int WEIGHT = 0;
    final int HEIGHT = 1;
    for(int i = 0; i < N; i++) {
      bodies[i][WEIGHT] = Input.nextInt();
      bodies[i][HEIGHT] = Input.nextInt();
    //// 입력받으면서 처리할 수 있는게 좋은거 같아요 이코드는.
      for(int j = 0; j < i; j++) {
        boolean biggerBody = (bodies[i][WEIGHT] > bodies[j][WEIGHT])
                              && (bodies[i][HEIGHT] > bodies[j][HEIGHT]);
        if (biggerBody) {
          ranks[j] += 1;
        }
        
        boolean smallerBody = (bodies[i][WEIGHT] < bodies[j][WEIGHT])
                              && (bodies[i][HEIGHT] < bodies[j][HEIGHT]);
        if (smallerBody) {
          ranks[i] += 1;
        }
      }
    }
    printArray(ranks);
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
