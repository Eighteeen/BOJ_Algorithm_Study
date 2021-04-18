import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    int[] levels = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int totalLevel = sumIntArray(levels);
    
    int minDifference = 10000;
    //// 사람 수에 맞춰서 확장성을 고려한 거라면 지금 풀이도 좋지만
    //// 4명만을 생각한 풀이라면 totalLevel을 구하거나 for문으로 하나씩 비교하지 않고
    //// 그저 제일 차이가 큰 레벨을 한 팀으로 만들고 나머지를 구하면 되기 때문에 이 방법도 고려해보면 좋을 것 같아요!
    //// => si gan bok jap do lo bo myeon
    //// => jeong ryeol un O(NlogN) i go, jeo neun O(N) i yeo seo i ge deo nat ji a neo yo?
    for (int i = 1; i < levels.length; i++) {
        int teamLevel = levels[0] + levels[i];
        int anotherTeamLevel = totalLevel - teamLevel;
        
        int difference = Math.abs(teamLevel - anotherTeamLevel);
        minDifference = difference < minDifference ? difference : minDifference;
    }

    System.out.print(minDifference);
  }

  static int sumIntArray(int[] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum;
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