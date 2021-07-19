import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 무난 깔끔
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int T = Input.nextInt();
    for (int i = 0; i < T; i++) {
      int numberOfUsers = Input.nextInt();
      int[] exposureBitmask = new int[numberOfUsers];

      for (int j = 0; j < numberOfUsers; j++) {
        int numberOfFriends = Input.nextInt();
        exposureBitmask[j] |= (1 << j);
        for (int k = 0; k < numberOfFriends; k++) {
          int friend = Input.nextInt();
          exposureBitmask[j] |= (1 << (friend - 1));
        }
      }

      sb.append(calcLeastAds(numberOfUsers, exposureBitmask))
        .append('\n');
    }

    System.out.print(sb);
  }

  //// 이런 방법도 있군요!
  //// => 저랑 같은 방법으로 푸셨는디요?
  static int calcLeastAds(int numberOfUsers, int[] exposureBitmask) {
    final int EXPOSED_TO_ALL = (1 << numberOfUsers) - 1;

    int leastAds = numberOfUsers;
    for (int adToWho = 1; adToWho < (EXPOSED_TO_ALL - 1); adToWho++) {
      int unioned = 0;
      for (int i = 0; i < numberOfUsers; i++) {
        if ((adToWho & (1 << i)) > 0) {
          unioned |= exposureBitmask[i];
        }
      }

      int ads = Integer.bitCount(adToWho);
      if (unioned == EXPOSED_TO_ALL && ads < leastAds) leastAds = ads;
    }

    return leastAds;
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

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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