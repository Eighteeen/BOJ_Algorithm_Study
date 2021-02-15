import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.IntStream;

//// 무난하게 잘 짜신 것 같습니다
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int BALLOONS = Input.nextInt();
    
    String[] papersInBalloons = Input.nextLine().split(" ");
    Balloons balloons = new Balloons(papersInBalloons);

    for (int i = 0; i < BALLOONS; i++) {
      sb.append(balloons.getCurrentBalloon())
        .append(' ');
      balloons.nextBalloon();
    }

    System.out.print(sb);
  }
}

class Balloons {
  private Deque<Balloon> balloons = new ArrayDeque<>();
  private int nextPaper;

  public Balloons(String[] papersInBalloons) {
    for (int i = 0; i < papersInBalloons.length; i++) {
      int number = i + 1;
      int paper = Integer.parseInt(papersInBalloons[i]);
      balloons.offerLast(new Balloon(number, paper));
    }
  }

  //// 지금 풍선의 순서를 반환하는데 next보다는 current, now등이 더 자연스러울 것 같아요
  //// => 반영할겸 좀 더 함수를 분리해봤습니다!
  public int getCurrentBalloon() {
    Balloon burstedBalloon = balloons.pollFirst();
    nextPaper = burstedBalloon.getPaper();

    return burstedBalloon.getNumber();
  }

  public void nextBalloon() {
    if (balloons.isEmpty()) return;

    if (nextPaper > 0) {
      int nextPlace = (nextPaper - 1) % balloons.size();
      nextNth(nextPlace);
    } else {
      int nextPlace = -nextPaper % balloons.size();
      previousNth(nextPlace);
    }
  }

  private void nextNth(int n) {
    for (int i = 0; i < n; i++) {
      balloons.offerLast(balloons.pollFirst());
    }
  }
  
  private void previousNth(int n) {
    for (int i = 0; i < n; i++) {
      balloons.offerFirst(balloons.pollLast());
    }
  }
}

class Balloon {
  private int number;
  private int paper;

  public Balloon(int number, int paper) {
    this.number = number;
    this.paper = paper;
  }

  public int getNumber() {
    return number;
  }

  public int getPaper() {
    return paper;
  }

  @Override
  public String toString() {
    return "{" + number + "번 " + paper + "}";
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