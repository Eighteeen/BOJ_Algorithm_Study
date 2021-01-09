import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//// 깔끔합니다!
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    //// 약간의 취향차이인 것 같기도 합니다만 입력 받는 루프 - 1과 출력 루프에서도 + 1을 하는데
    //// counts 배열에 크기 하나만 늘어나면 연산을 계속 할 필요가 없으니 배열 new int[10001]은 어떤가요?
    //// => 4바이트의 메모리를 더 쓰냐, N+10000번의 덧셈 연산을 더 하느냐의 문제인데
    //// => 이건 전자가 더 좋은 것 같습니다. 훨씬 효율적이에요.
    //// => 좋은 피드백 감사합니다! wsb 코드 읽을때는 왜 10001로 했나했는데 이제 알겠네요.
    
    ////문제의 숫자를 이용하라는게 이 말이였군요 배워갑니다.
    int[] counts = new int[10001];

    final int N = Input.nextInt();
    for (int i = 0; i < N; i++) {
      int numberIndex = Input.nextInt();
      counts[numberIndex] += 1;
    }

    for (int i = 1; i <= 10000; i++) {
      for(int j = 0; j < counts[i]; j++) {
        int number = i;
        sb.append(number).append('\n');
      }
    }

    System.out.print(sb);
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