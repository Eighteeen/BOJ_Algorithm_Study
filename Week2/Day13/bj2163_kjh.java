import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int M = Input.nextInt();

    System.out.print(cutChocolate(N, M));
  }
    ////계산 식이 더 간단해질 수 있을 거 같아요! : 22 굳이 풀어 쓸 필요없을 것 같아요
  private static int cutChocolate(int N, int M) {
    if (N + M == 2) return 0;
    
    if (N % 2 == 0) {
      return 1 + (cutChocolate(N / 2, M) * 2);
    } else if (M % 2 == 0) {
      return 1 + (cutChocolate(N, M / 2) * 2);
    } else if (N > 1) {
      return (N - 1) + (cutChocolate(1, M) * N);
    } else if (M > 1) {
      return (M - 1) + (cutChocolate(N, 1) * N);
    }
    
    return 0;
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