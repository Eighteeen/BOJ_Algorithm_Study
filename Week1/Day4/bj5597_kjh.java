import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class Main {
  public static void main(String[] args) throws Exception {
    int[] oneToThirty = IntStream.range(1, 31).toArray();
    List<Integer> studentNumbers = Arrays.stream(oneToThirty).boxed().collect(Collectors.toList());

    for(int i = 0; i < 28; i++) {
      Integer studentNumber = Input.nextInt();
      studentNumbers.remove(studentNumber);
    }

    StringBuilder sb = new StringBuilder();
    sb.append(studentNumbers.get(0).toString())
      .append('\n')
      .append(studentNumbers.get(1).toString());
    
    System.out.print(sb);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}