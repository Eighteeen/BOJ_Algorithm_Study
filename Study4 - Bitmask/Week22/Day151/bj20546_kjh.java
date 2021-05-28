import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 무난깔끔해요:-D
//// 객체지향적으로 깔끔하게 잘 푸신 것 같습니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int CASH = Input.nextInt();
    
    //// 오호 추상클래스와 자식 클래스를 매우 잘 활용했네요
    Strategy bnpStrategy = new BNPStrategy(CASH);
    Strategy timingStrategy = new TimingStrategy(CASH);

    int todayPrice = 0;
    for (int i = 0; i < 14; i++) {
      todayPrice = Input.nextInt();
      
      bnpStrategy.run(todayPrice);
      timingStrategy.run(todayPrice);
    }

    String result = getResult(todayPrice, bnpStrategy, timingStrategy);
    System.out.print(result);
  }

  static String getResult(int todayPrice, Strategy bnpStrategy, Strategy timingStrategy) {
    int assetsForBNP = bnpStrategy.calcAssets(todayPrice);
    int assetsForTiming = timingStrategy.calcAssets(todayPrice);

    if (assetsForBNP > assetsForTiming) {
      return "BNP";
    }

    if (assetsForTiming > assetsForBNP) {
      return "TIMING";
    }

    return "SAMESAME";
  }
}

class BNPStrategy extends Strategy {
  public BNPStrategy(int initialCash) {
    cash = initialCash;
  }

  @Override
  public void run(int todayPrice) {
    stocks += cash / todayPrice;
    cash %= todayPrice;
  }
}

class TimingStrategy extends Strategy {
  private int yesterdayStock = -1;
  private int upStrike;
  private int downStrike;

  public TimingStrategy(int initialCash) {
    cash = initialCash;
  }

  @Override
  public void run(int todayPrice) {
    updateStrikes(todayPrice);
    
    if (upStrike >= 3) {
      cash += stocks * todayPrice;
      stocks = 0;
    }
    
    if (downStrike >= 3) {
      stocks += cash / todayPrice;
      cash %= todayPrice;
    }

    yesterdayStock = todayPrice;
  }

  private void updateStrikes(int todayPrice) {
    if (yesterdayStock == -1) return;

    if (todayPrice < yesterdayStock) {
      downStrike++;
      upStrike = 0;
    }
    else if (todayPrice > yesterdayStock) {
      upStrike++;
      downStrike = 0;
    }
    else {
      upStrike = 0;
      downStrike = 0;
    }
  }
}

//// 와우 추상 클래스까지!
abstract class Strategy {
  protected int stocks;
  protected int cash;

  abstract void run(int todayPrice);

  public int calcAssets(int todayPrice) {
    return (stocks * todayPrice) + cash;
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