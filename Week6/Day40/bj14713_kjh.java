import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  private static List<Queue<String>> parrots;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    parrots = new ArrayList<Queue<String>>();
    
    for(int i = 0; i < N; i++) {
      Queue<String> parrot = new LinkedList<>();
      Arrays.stream(Input.nextLine().split(" "))
        .forEach(word -> parrot.offer(word));

      parrots.add(parrot);
    }

    String mixedWords = Input.nextLine();
    for (String word : mixedWords.split(" ")) {
      if (canParrotSay(word) == false) {
        System.out.print("Impossible");
        return;
      }
    }
    
    if (allParrotsSaidWholeWord() == false) {
      System.out.print("Impossible");
      return;
    }
      
    System.out.print("Possible");
  }

  public static boolean canParrotSay(String word) {
    for (Queue<String> parrot : parrots) {
      if (!parrot.isEmpty() && parrot.peek().equals(word)) {
        parrot.poll();
        return true;
      }
    }
    return false;
  }

  public static boolean allParrotsSaidWholeWord() {
    for (Queue<String> parrot : parrots) {
      if (!parrot.isEmpty()) {
        return false;
      }
    }
    return true;
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