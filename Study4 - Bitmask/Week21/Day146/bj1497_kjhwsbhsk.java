import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_OF_GUITARS = Input.nextInt();
    final int NUMBER_OF_SONGS = Input.nextInt();

    long[] guitarsSpecBitmask = new long[NUMBER_OF_GUITARS];
    for (int i = 0; i < NUMBER_OF_GUITARS; i++) {
      String uselessName = Input.next();
      char[] songsCanPlay = Input.next().toCharArray();

      for (int songNumber = 0; songNumber < songsCanPlay.length; songNumber++) {
        if (songsCanPlay[songNumber] == 'N') continue;
        guitarsSpecBitmask[i] |= 1L << songNumber;
      }
    }

    int leastGuitarsForManySongs = calcLeastGuitarsForManySongs(guitarsSpecBitmask);
    System.out.print(leastGuitarsForManySongs);
  }

  static int calcLeastGuitarsForManySongs(long[] guitarsSpecBitmask) {
    int guitarAmount = guitarsSpecBitmask.length;
    final int USING_ALL_GUITARS = (1 << guitarAmount) - 1;

    int minAmountOfUsedGuitars = guitarAmount;
    int maxSongsCanPlay = 0;
    for (int usingGuitars = 1; usingGuitars <= USING_ALL_GUITARS; usingGuitars++) {
      long songsBitmask = 0;
      int amountOfUsedGuitars = 0;
      for (int guitar = 0; guitar < guitarAmount; guitar++) {
        if ((usingGuitars & (1 << guitar)) == 0) continue;
        songsBitmask |= guitarsSpecBitmask[guitar];
        amountOfUsedGuitars++;
      }

      int songsCanPlay = Long.bitCount(songsBitmask);
      if (songsCanPlay < maxSongsCanPlay) continue;
      if (songsCanPlay == maxSongsCanPlay) {
        minAmountOfUsedGuitars = Math.min(amountOfUsedGuitars, minAmountOfUsedGuitars);
        continue;
      }
      
      maxSongsCanPlay = songsCanPlay;
      minAmountOfUsedGuitars = amountOfUsedGuitars;
    }

    return (maxSongsCanPlay == 0) ? (-1) : (minAmountOfUsedGuitars);
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