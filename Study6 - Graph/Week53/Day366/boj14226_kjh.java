import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해욧 : 22
class Main {
  public static void main(String[] args) throws Exception {
    final int REQUIRED_EMOJIS = Input.nextInt();

    int optimalTime = getOptimalTimeToGetEmoji(REQUIRED_EMOJIS);
    System.out.print(optimalTime);
  }

  static int getOptimalTimeToGetEmoji(int requiredEmojis) {
    Queue<TypingInfo> queue = new LinkedList<>();
    queue.add(new TypingInfo(0, 1, 0));
    boolean[][] visited = new boolean[1001][1001]; // 클립보드, 이모지

    while (queue.size() > 0) {
      TypingInfo current = queue.poll();
      visited[current.clips][current.emojis] = true;

      if (current.emojis == requiredEmojis) {
        return current.requiredTime; 
      }

      //// 조건문이 무엇을 나타나는지 변수를 지정했으면 알아보기 쉬웠을 것 같아요 : 22
      if (!visited[current.emojis][current.emojis]) {
        queue.add(new TypingInfo(current.emojis, current.emojis, current.requiredTime + 1));
      }
      if (current.emojis + current.clips <= 1000 && !visited[current.clips][current.emojis + current.clips]) {
        queue.add(new TypingInfo(current.clips, current.emojis + current.clips, current.requiredTime + 1));
      }
      if (current.emojis >= 1 && !visited[current.clips][current.emojis - 1]) {
        queue.add(new TypingInfo(current.clips, current.emojis - 1, current.requiredTime + 1));
      }
    }
    
    return -1;
  }
}

class TypingInfo {
  public int clips;
  public int emojis;
  public int requiredTime;

  public TypingInfo(int clips, int emojis, int requiredTime) {
    this.clips = clips;
    this.emojis = emojis;
    this.requiredTime = requiredTime;
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