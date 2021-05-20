import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

//// 전체적으로 깔끔하고 간략하네요
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int K = Input.nextInt();

    List<Integer> cards = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      cards.add(Input.nextInt());
    }

    List<String> mixByK = getMixByK(cards, K); 
    System.out.print(mixByK.size());
  }

  static List<String> getMixByK(List<Integer> cards, int K) {
    if (K == 1) return cards.stream().map(String::valueOf).collect(toList());

    //// list가 아니라 set을 이용하면 밑에서 contains 체크를 힐 필요가 없을 것 같습니다!
    List<String> mixByK = new ArrayList<>();
    for (int i = 0; i < cards.size(); i++) {
      Integer card = cards.remove(0);
      List<String> mixByKMinus1 = getMixByK(cards, K - 1);
      cards.add(card);

      for (int j = 0; j < mixByKMinus1.size(); j++) {
        String mix = card + mixByKMinus1.get(j);
        if (!mixByK.contains(mix)) mixByK.add(mix);
      }
    }

    return mixByK;
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