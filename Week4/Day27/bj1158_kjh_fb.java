import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.IntStream;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder(); 

    final int NUMBER_OF_PEOPLE = Input.nextInt();
    final int REMOVE_NTH_PERSON = Input.nextInt();
    
    Queue<Integer> queue = new LinkedList<>();
    IntStream.range(1, NUMBER_OF_PEOPLE + 1)
      .boxed()
      .forEach(item -> queue.add(item));

    sb.append('<');
    for (int i = 0; i < NUMBER_OF_PEOPLE - 1; i++) {
      for (int j = 0; j < REMOVE_NTH_PERSON - 1; j++) {
        queue.add(queue.remove());
      }
      sb.append(queue.remove())
        .append(", ");
    }
    sb.append(queue.remove())
    .append('>');
    
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