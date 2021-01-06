import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int SEMESTERS = Input.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < SEMESTERS; i++) {
      int subjects = Input.nextInt();
      int creditSum = 0;
      double gradeSum = 0.0;
      for (int j = 0; j < subjects; j++) {
        int credit = Input.nextInt();
        creditSum += credit;
        gradeSum += Input.nextDouble() * credit;
      }

      sb.append(creditSum)
        .append(' ')
        .append(String.format("%.1f", gradeSum / creditSum))
        .append('\n');
    }
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
  
  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
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