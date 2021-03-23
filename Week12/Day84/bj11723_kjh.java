import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 전체적으로 깔끔합니다.
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    Set set = new Set(20);
    for (int i = 0; i < N; i++) {
      String methodName = Input.next();
      if (methodName.equals("all")) {
        set.all();
        continue;
      }
      if (methodName.equals("empty")) {
        set.empty();
        continue;
      }
      
      //// 이 밑에 있는 조건문에는 continue를 사용하지 않은 이유가 뭔가요?
      //// => methodName이 add이면서 remove일 가능성은 없으니까요!
      //// => 위에서 continue를 쓴건 nextInt()를 하지 않게 하기 위해섭니다
      int x = Input.nextInt();
      if (methodName.equals("add")) {
        set.add(x);
      }
      if (methodName.equals("remove")) {
        set.remove(x);
      }
      if (methodName.equals("toggle")) {
        set.toggle(x);
      }
      if (methodName.equals("check")) {
        sb.append(set.check(x)).append('\n');
      }
    }

    System.out.print(sb);
  }
}

class Set {
  boolean[] elements;

  public Set(int size) {
    elements = new boolean[size];
  }

  public void add(int x) {
    elements[x - 1] = true;
  }

  public void remove(int x) {
    elements[x - 1] = false;
  }

  public void toggle(int x) {
    elements[x - 1] = !elements[x - 1];
  }

  public int check(int x) {
    return elements[x - 1] ? 1 : 0;
  }

  public void all() {
    Arrays.fill(elements, true);
  }

  public void empty() {
    Arrays.fill(elements, false);
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