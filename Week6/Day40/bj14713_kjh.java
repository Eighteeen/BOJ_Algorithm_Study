import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//// 전체적으로 무난하게 잘 짜신 것 같습니다
class Main {
  private static List<Queue<String>> parrots;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    parrots = new ArrayList<Queue<String>>();
    
    for(int i = 0; i < N; i++) {
      Queue<String> parrot = new LinkedList<>();
      Arrays.stream(Input.nextLine().split(" "))
        .forEach(word -> parrot.offer(word));

      parrots.add(parrot);
    }

    //// mixedWords 자체를 배열로 만드는 게 더 자연스러울 것 같아요
    String mixedWords = Input.nextLine();
    for (String word : mixedWords.split(" ")) {
      //// 반환값이 함수명을 보고 충분히 예상이 가는데도 ! 연산자를 쓰지 않고 == 연산자를 쓰신 이유가 있나요?
      //// == false 로 사용하시면 끝까지 코드를 읽어야 하는데 비해 ! 연산자를 사용하면 직관적이고 간결하다고 생각합니다!
      if (canParrotSay(word) == false) {
        System.out.print("Impossible");
        return;
      }
    }
    
    if (allParrotsSaidWholeWord() == false) {
      System.out.print("Impossible");
      return;
    }
      
    System.out.print("Possible");
  }

  public static boolean canParrotSay(String word) {
    for (Queue<String> parrot : parrots) {
      if (!parrot.isEmpty() && parrot.peek().equals(word)) {
        parrot.poll();
        return true;
      }
    }
    return false;
  }

  public static boolean allParrotsSaidWholeWord() {
    for (Queue<String> parrot : parrots) {
      if (!parrot.isEmpty()) {
        return false;
      }
    }
    return true;
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