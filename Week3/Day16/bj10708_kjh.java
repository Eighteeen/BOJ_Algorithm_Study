import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

//// 깔꼼~ : 22 잘짰어유~
class Main {
  public static void main(String[] args) throws Exception {
    final int FRIENDS = Input.nextInt();
    final int GAMES = Input.nextInt();
    
    int[] targets = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] points = new int[FRIENDS];
    Arrays.fill(points, 0);

    for (int i = 0; i < GAMES; i++) {
      //// 종이에 적어서 papers ㅋㅋㅋㅋㅋㅋ 되게 현실 친화적?이네여
      //// => ㅋㅋㅋㅋ 직관적이면서 글자수도 더 적은 것 같아서 택했어요!
      int[] papers = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      for(int j = 0; j < FRIENDS; j++) {
        if (papers[j] != targets[i]) {
          int targetIdx = targets[i] - 1;
          points[targetIdx]++;
          continue;
        }
        points[j]++;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < FRIENDS; i++) {
      sb.append(points[i])
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
