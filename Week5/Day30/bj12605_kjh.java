import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
//// 깔꼼합니당 : 22
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    Stack<String> words = new Stack<>();
    final int N = Input.nextInt();
    
    for (int i = 1; i <= N; i++) {
      sb.append("Case #").append(i).append(": ");

      //// 여기 for문은 출력과 상관없이 스택을 구성하는 코드라서 위에 Case문이 있는 게 좀 어색하게 느껴져요. 출력과 함께 쓰여있다면 걸리는 거 없이 쭉 읽힐 것 같아요!
      for (String word : Input.nextLine().split(" ")) {
        words.push(word);
      }

      while (!words.empty()) {
        sb.append(words.pop())
          .append(' ');
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
