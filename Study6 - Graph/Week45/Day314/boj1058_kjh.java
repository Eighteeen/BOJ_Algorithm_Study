import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    List<Integer>[] friends = Stream.generate(ArrayList<Integer>::new).limit(N + 1).toArray(ArrayList[]::new);

    for (int i = 1; i <= N; i++) {
      String friendsInfo = Input.nextLine();
      for (int j = 0; j < N; j++) {
        if (friendsInfo.charAt(j) == 'Y') {
          friends[i].add(j + 1);
        }
      }
    }

    int maxTwoDashFriends = 0;
    for (int i = 1; i <= N; i++) {
      int twoDashFriends = getTwoDashFriends(friends, i);
      maxTwoDashFriends = Math.max(maxTwoDashFriends, twoDashFriends);
    }

    System.out.print(maxTwoDashFriends);
  }

  //// 왕 깔끔해여 : 22
  static int getTwoDashFriends(List<Integer>[] friends, int index) {
    int twoDashFriends = 0;
    
    for (int i = 1; i < friends.length; i++) {
      if (i == index) continue;

      if (friends[i].contains(index)) {
        twoDashFriends++;
        continue;
      }

      for (int j = 0; j < friends[index].size(); j++) {
        if (friends[i].contains(friends[index].get(j))) {
          twoDashFriends++;
          break;
        }
      }
    } 
    
    return twoDashFriends;
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