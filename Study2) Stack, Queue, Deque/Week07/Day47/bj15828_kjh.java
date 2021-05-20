import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

//// 무난하게 잘 짜신 것 같습니다
class Main {
  public static void main(String[] args) throws Exception {
    Queue<Integer> buffer = new LinkedList<>();
    final int BUFFER_SIZE = Input.nextInt();
  
    int input;
    while ((input = Input.nextInt()) != -1) {
      if (input == 0) {
        buffer.poll();
        continue;
      }

      if (buffer.size() >= BUFFER_SIZE) continue;
      buffer.offer(input);
    }

    if (buffer.isEmpty()) {
      System.out.print("empty");
      return;
    }

    StringBuilder result = new StringBuilder();
    buffer.stream()
      .forEach(packet -> result.append(packet).append(' '));
    
    System.out.print(result);
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