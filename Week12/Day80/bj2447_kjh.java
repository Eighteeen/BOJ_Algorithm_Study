import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] stars;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    stars = new boolean[N][N];

    makeStars(N, 0, 0);
    printStars(stars);
  }

  //// 와우 이렇게 많은 호출은 처음 봅니다. 정말 깔끔해졌지만 만약 문제의 3^k에서 k 범위가 높아진다면 오버플로우가 일어나진 않을까 살짝 걱정됩니다.
  //// => 많아보이지만 호출횟수는 O(N^2)로 N<=10000까지는 충분히 감당가능한 알고리즘입니당
  static void makeStars(int size, int y, int x) {
    if (size == 1) {
      stars[y][x] = true;
      return;
    }

    //// prevSize * 2 연산이 많이 쓰이는데 변수 저장 후 사용은 어때요?
    //// => 피드백 감사합니다! 한번 적용해봤는데 개인적으로 느끼기에 가독성이 살짝 떨어지게 되는 것 같아 이대로 유지하려 합니다!
    int prevSize = size / 3;
    makeStars(prevSize, y, x);
    makeStars(prevSize, y, x + prevSize);
    makeStars(prevSize, y, x + prevSize * 2);

    makeStars(prevSize, y + prevSize, x);
    makeStars(prevSize, y + prevSize, x + prevSize * 2);
    
    makeStars(prevSize, y + prevSize, x);
    makeStars(prevSize, y + prevSize * 2, x + prevSize);
    makeStars(prevSize, y + prevSize * 2, x + prevSize * 2);
  }

  static void printStars(boolean[][] stars) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < stars.length; i++) {
      for (int j = 0; j < stars[0].length; j++) {
        sb.append(stars[i][j] ? '*' : ' ');
      }
      sb.append('\n');
    }

    System.out.print(sb);
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