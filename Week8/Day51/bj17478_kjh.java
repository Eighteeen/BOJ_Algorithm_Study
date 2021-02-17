import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 전체적으로 깔끔합니다
class Main {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();

    //// 해당 문장도 함수가 시작될 때 나와야 하는 문장이라서 main에 따로 있는 게 아쉽습니다 (예를 들어 N의 범위가 0까지 확대된다면 main을 고쳐야 해서 아쉽네요)
    sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
    whatIsRecursiveFunction(0, N);

    System.out.print(sb);
  }

  static void whatIsRecursiveFunction(int depth, int maxDepth) {
    StringBuilder underlines = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      underlines.append("____");
    }

    sb.append(underlines).append("\"재귀함수가 뭔가요?\"\n");
    if (depth == maxDepth) {
      sb.append(underlines).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
      sb.append(underlines).append("라고 답변하였지.\n");
      return;
    }

    sb.append(underlines).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
    sb.append(underlines).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
    sb.append(underlines).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
    
    whatIsRecursiveFunction(depth + 1, maxDepth);

    sb.append(underlines).append("라고 답변하였지.\n");
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