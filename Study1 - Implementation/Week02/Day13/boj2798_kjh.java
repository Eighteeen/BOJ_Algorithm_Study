import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//// 항상 함수를 잘 분리해서 메인을 되게 깔끔하게 짜주시는거 같아요!
class Main {
  public static void main(String[] args) throws Exception {
    final int CARDS = Input.nextInt();
    final int GOAL = Input.nextInt();
    int[] cards = new int[CARDS];

    for(int i = 0; i < CARDS; i++) {
      cards[i] = Input.nextInt();
    }

    int sumOfBestCombination = findSumOfBestCombination(cards, GOAL);
    System.out.print(sumOfBestCombination);
  }
  
  private static int findSumOfBestCombination(int[] cards, int GOAL) {
    int bestSum = 0;
    int bestDifference = GOAL;
    ////i랑j랑k랑 크기를 다르게 두면 좋을거 같습니다!
    ////=> 현 코드가 더 효율적이라고 생각합니다
    ////=> -1, -2를 하게되면 의도와 달라 '틀렸습니다'가 나와 그대로 뒀습니다
    for (int i = 0; i < cards.length; i++) {
      for (int j = i + 1; j < cards.length; j++) {
        for (int k = j + 1; k < cards.length; k++) {
          int sum = cards[i] + cards[j] + cards[k];
          if (sum > GOAL) continue;

          //// bestSum과 sum이 이미 따로 있어서 difference, bestDifference를 따로 만들어서 비교할 필요가 없어보여요
          //// => 메모리 효율성보단 가독성을 추구했습니다!
          int difference = GOAL - sum;
          if (difference < bestDifference) {
            bestSum = sum;
            bestDifference = difference;
          }
        }
      }
    }
    return bestSum;
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
