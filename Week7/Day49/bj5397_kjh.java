import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int INPUTS = Input.nextInt();

    for (int i = 0; i < INPUTS; i++) {
      String input = Input.nextLine();

      KeyLogger keyLogger = new KeyLogger();
      for (char ch : input.toCharArray()) {
        keyLogger.log(ch);
      }
      sb.append(keyLogger.getPassword())
        .append('\n');
    }
    System.out.print(sb);
  }
}

class KeyLogger {
  private Deque<Character> deque = new ArrayDeque<>();
  private int cursor = 0;

  public void log(char ch) {
    switch (ch) {
      case '<':
        if (cursor == 0) break;
        deque.offerFirst(deque.pollLast());
        cursor--;
        break;
      case '>':
        if (deque.isEmpty()) break;
        if (cursor == deque.size()) break;
        deque.offerLast(deque.pollFirst());
        cursor++;
        break;
      case '-':
        if (deque.isEmpty()) break;
        if (cursor == 0) break;
        deque.pollLast();
        cursor--;
        break;
      default:
        deque.offerLast(ch);
        cursor++;
    }
  }

  public StringBuilder getPassword() {
    moveCursorToLast();

    StringBuilder sb = new StringBuilder();
    deque.stream()
      .forEach(ch -> sb.append(ch));
    
    return sb;
  }

  private void moveCursorToLast() {
    for (int i = cursor; i < deque.size(); i++) {
      deque.offerLast(deque.pollFirst());
      cursor++;
    }
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