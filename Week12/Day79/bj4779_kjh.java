import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static List<StringBuilder> cantors;

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    cantors = new ArrayList<>();

    String input;
    while ( (input = Input.nextLine()) != null ) {
      int size = Integer.parseInt(input);
      makeCantor(size);
      sb.append(cantors.get(size)).append('\n');
    }

    System.out.print(sb);
  }

  // 승빈씨 재활용 본받아 작업했더니 랭킹 1위 등극
  static void makeCantor(int size) {
    if (cantors.size() > size) return;
    if (size == 0) {
      cantors.add(new StringBuilder("-"));
      return;
    }

    makeCantor(size - 1);
    StringBuilder prevCantor = cantors.get(size - 1);
    int prevLen = prevCantor.toString().length();
    
    StringBuilder currentCantor = new StringBuilder();
    currentCantor.append(prevCantor)
      .append(" ".repeat(prevLen))
      .append(prevCantor);

    cantors.add(currentCantor);
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