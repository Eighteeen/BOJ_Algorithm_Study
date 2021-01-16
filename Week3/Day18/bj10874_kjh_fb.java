import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 깔끔합니다 ~~~
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    //// 어짜피 입력과 규격이 정해져 있는 문제라 int배열로 선언할 필요 없어보여요..!
    //// => 렬루 맞는 말씀이십니다.. 이 생각을 못했었네요. 감사합니다!
    final String ANSWERS = "1 2 3 4 5 1 2 3 4 5";
    
    final int STUDENTS = Input.nextInt();
    for (int i = 0; i < STUDENTS; i++) {
      String studentAnswers = Input.nextLine();
      if (ANSWERS.equals(studentAnswers)) {
        sb.append(i + 1)
          .append("\n");
      }
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
