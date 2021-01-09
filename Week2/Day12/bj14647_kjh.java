import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  ////깔끔합니다. :22 읽기도 쉽고 효율성도 갖춘거 같아요. : 333 낄끔!!
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    final int M = Input.nextInt();

    int wholeNine = 0;
    int[] nineInRow = new int[N];
    int[] nineInColumn = new int[M];
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        int nineInNumber = countCharFromString('9', Input.next());
        wholeNine += nineInNumber;
        nineInRow[i] += nineInNumber;
        nineInColumn[j] += nineInNumber;
      }
    }

    int maxNineInRow = Arrays.stream(nineInRow).max().getAsInt();
    int maxNineInColumn = Arrays.stream(nineInColumn).max().getAsInt();
    
    int hitCount = wholeNine - ( maxNineInRow > maxNineInColumn ? maxNineInRow : maxNineInColumn);
    System.out.print(hitCount);
  }
  
  private static int countCharFromString(char ch, String str) {
    int count = 0;
    for(char partOfStr : str.toCharArray()) {
      if (partOfStr == ch) {
        count += 1;
      }
    }
    return count;
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
