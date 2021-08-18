import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int NODE_AMOUNT = Input.nextInt();

    SocialTree socialTree = new SocialTree(NODE_AMOUNT);
    for (int i = 0; i < NODE_AMOUNT - 1; i++) {
      int friendA = Input.nextInt();
      int friendB = Input.nextInt();
      socialTree.addPairOfFriends(friendA, friendB);
    }

    System.out.print(socialTree.getMinEarlyAdapterToSpread());
  }
}

class SocialTree {
  private List<Integer>[] friends;
  private int minEarlyAdaptersMemoization[][];

  public SocialTree(int numberOfPeople) {
    friends = Stream.generate(ArrayList<Integer>::new).limit(numberOfPeople + 1).toArray(ArrayList[]::new);

    minEarlyAdaptersMemoization = new int[2][numberOfPeople + 1];
    Arrays.fill(minEarlyAdaptersMemoization[0], -1);
    Arrays.fill(minEarlyAdaptersMemoization[1], -1);
  }

  public void addPairOfFriends(int friendA, int friendB) {
    friends[friendA].add(friendB);
    friends[friendB].add(friendA);
  }

  public int getMinEarlyAdapterToSpread() {
    return getMinEarlyAdapterToSpread(true, 1);
  }

  //// isPrevAdapter를 사용하심에 로직의 이해도는 올라간 느낌이나,
  //// 흐름상으로는 간단해지기 보단 그저 분기처리를 하셔서 불필요한 구문이 늘어난 느낌입니다.
  //// minEarlyAdaptersMemoization 를 이미 이차원 배열로 처리하셨으니 이를 활용하시면 더욱 간단하게 처리할 수 있을 것 같습니다.
  private int getMinEarlyAdapterToSpread(boolean isPrevAdapter, int current) {
    int memoization = minEarlyAdaptersMemoization[isPrevAdapter ? 1 : 0][current];
    if (memoization != -1) {
      return memoization;
    }

    if (friends[current].size() == 0) {
      return isPrevAdapter ? 0 : 1;
    }

    int earlyAdaptersIfCurrentIsEarly = 1;
    int earlyAdaptersIfCurrentIsLate = 0;
    for (int friend : friends[current]) {
      friends[friend].remove(Integer.valueOf(current));
      earlyAdaptersIfCurrentIsEarly += getMinEarlyAdapterToSpread(true, friend);
      if (isPrevAdapter) {
        earlyAdaptersIfCurrentIsLate += getMinEarlyAdapterToSpread(false, friend);
      }
    }

    int minEarlyAdapterToSpread;
    if (isPrevAdapter) {
      minEarlyAdapterToSpread = Math.min(earlyAdaptersIfCurrentIsEarly, earlyAdaptersIfCurrentIsLate);
    } else {
      minEarlyAdapterToSpread = earlyAdaptersIfCurrentIsEarly;
    }

    minEarlyAdaptersMemoization[isPrevAdapter ? 1 : 0][current] = minEarlyAdapterToSpread;
    return minEarlyAdapterToSpread;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}