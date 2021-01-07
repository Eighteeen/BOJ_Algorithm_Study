import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 전체적으로 깔끔하고 딱 필요한 부분들만 보이도록 잘 짜신 것 같습니다:22
class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    StringBuilder sb = new StringBuilder();
    
    for(int i = N; i >= 0; i--) {
      sb.append(howManyBottles(i, N));
    }
    System.out.print(sb);
  }

  private static String howManyBottles(int leftBottles, int wholeBottles) {
    if (leftBottles == 0) {
      return "No more bottles of beer on the wall, no more bottles of beer.\n" +
             "Go to the store and buy some more, " + bottles(wholeBottles) + " of beer on the wall.";
    }
    return bottles(leftBottles) + " of beer on the wall, " + bottles(leftBottles) + " of beer.\n" +
           "Take one down and pass it around, " + bottles(leftBottles - 1) + " of beer on the wall.\n\n";
  }

  private static String bottles(int count) {
    if (count == 0) {
      return "no more bottles";
    }
    if (count == 1) {
      return "1 bottle";
    }
    return count + " bottles";
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