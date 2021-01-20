import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 구현 간단 깔끔합니다
class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < T; i++) {
      int distance = -Input.nextInt() + Input.nextInt();
      sb.append(getLeastMove(distance)).append('\n');
    }
    System.out.print(sb);
  }
  
  // '루트한 것이 ~1이면 이동횟수 1, 루트한 것이 ~1.5면 이동횟수는 2' 식으로 이동횟수가 1 늘어날때마다 0.5씩 늘어나는 규칙을 찾아냈음
  static int getLeastMove(int distance) {
    //// 절삭하는 방법 괜찮네요! 저는 round로 처리하려고 하다가 틀려서 다른 방법을 채택했거든요
    // 0.5 단위로 비교하긴 더러운거같아서 2를 곱하고 소수점 절삭했음. 이러면 비슷하게 구해짐
    double rootAndMultiplyTwo = Math.sqrt(distance) * 2;
    //// 변수가 어디로 날라갔을까요?
    double roundDown = Math.floor(leastMove);

    //// 변수가 어디로 날라갔을까요..?
    // 다만 1 4 9 등 제곱수에서는 1 크게 구해져서 그땐 -1을 해줌
    if (Double.compare(leastMove, abandoned) == 0) {
      return (int) abandoned - 1;
    }
    return (int) abandoned;
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