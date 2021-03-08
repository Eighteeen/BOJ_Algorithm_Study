import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Main {
  public static void main(String[] args) throws Exception {
    final int unseen = Input.nextInt();
    final int unheard = Input.nextInt();

    Set<String> unseenList = new HashSet<>();
    for (int i = 0; i < unseen; i++) {
      String unseenName = Input.nextLine();
      unseenList.add(unseenName);
    }

    List<String> unheardseenList = new ArrayList<>();
    for (int i = 0; i < unheard; i++) {
      String unheardName = Input.nextLine();
      if (unseenList.contains(unheardName)) {
        unheardseenList.add(unheardName);
      }
    }

    StringBuilder sb = new StringBuilder();

    int unheardseens = unheardseenList.size();
    sb.append(unheardseens).append('\n');

    unheardseenList.stream()
      .sorted()
      .forEach(unheardseenName -> sb.append(unheardseenName).append('\n'));

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