import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int maxDiff = Input.nextInt();

    Queue<Character> queue = new LinkedList<>();
    for (char gender : Input.nextLine().toCharArray()) {
      queue.offer(gender);
    }

    Club club = new Club(maxDiff);
    while (queue.size() > 0) {
      char currentGender = queue.poll();
      
      if (club.isAbleToEnter(currentGender)) {
        club.enter(currentGender);
        continue;
      }
      
      if (queue.size() == 0) break;

      char nextGender = queue.poll();
      if (!club.isAbleToEnter(nextGender)) break;
    
      club.enter(nextGender);
      club.enter(currentGender);
    }

    System.out.print(club.getTotalPeople());
  }
}

class Club {
  private int maxDiff;
  private int men;
  private int women;

  public Club(int maxDiff) {
    this.maxDiff = maxDiff;
  }

  public boolean isAbleToEnter(char gender) {
    if (gender == 'M') {
      return (men - women) < maxDiff;
    }
    return (women - men) < maxDiff;
  }

  public void enter(char gender) {
    if (gender == 'M') {
      men++;
      return;
    }
    women++;
  }

  public int getTotalPeople() {
    return men + women;
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