import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

//// 무난하고 깔끔하게 잘 짜신 것 같습니다
class Main {
  public static void main(String[] args) throws Exception {
    Stack<Character> stack;

    final int WORDS = Input.nextInt();
    int goodWordCount = 0;

    for (int i = 0; i < WORDS; i++) {
      stack = new Stack<>();
      String word = Input.nextLine();
      
      for (char ch : word.toCharArray()) {
        if (!stack.isEmpty() && stack.peek() == ch) {
          stack.pop();
          continue;
        }
        stack.push(ch);
      }

      //// isGoodWord로 의도는 알겠으나 이도 empty인 것을 알아야 하니 if에 한번에 쓰는 것도 나쁘지 않을 것 같습니다
      boolean isGoodWord = stack.isEmpty();
      if (isGoodWord) goodWordCount++;
    }

    System.out.print(goodWordCount);
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