import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

//// 깔끔합니다.
//// 비트마스킹의 특징을 이용한 부분을 찾아볼 수 없었어요 ㅠ 제가 못 찾는 걸까요? 진행 주제 풀이 특징을 찾을 수 없어서 아쉽습니다.
//// => 톡으로 답변 드려서 일단 패스하겠읍니다
class Main {
  static Set<Integer> possibleCase = new HashSet<>();
  static final int FIRST = 0;
  static final int SECOND = 1;

  public static void main(String[] args) throws Exception {
    Barn[] barns = new Barn[2];
    barns[FIRST] = new Barn(Input.nextLine());
    barns[SECOND] = new Barn(Input.nextLine());

    final int TUESDAY = 2;
    pickPossibleCases(TUESDAY, barns);

    System.out.print(possibleCase.size());
  }

  static void pickPossibleCases(int day, Barn[] barns) {
    if (day == 6) {
      possibleCase.add(barns[FIRST].getMilk());
      return;
    }

    int outBarnIdx = day % 2;
    int inBarnIdx = (day + 1) % 2;

    int leftBucket = barns[outBarnIdx].getLeftBucket();
    for (int i = 0; i < leftBucket; i++) {
      int carriedOut = barns[outBarnIdx].carryOutNthBucket(i);
      barns[inBarnIdx].carryInABucket(carriedOut);
      
      pickPossibleCases(day + 1, barns);
      
      barns[outBarnIdx].cancelCarryingOut(i, carriedOut);
      barns[inBarnIdx].cancelCarryingIn();
    }
  }
}

class Barn {
  private int milk;
  private List<Integer> buckets;

  private final int EXTRA_ONE = 11;
  private final int EXTRA_TWO = 10;
  
  public Barn(String strBuckets) {
    milk = 1000;

    //// buckets를 list로 하신다면 확장성있게 arrBuckets의 length를 이용하면 좋을 것 같아요. 
    String[] arrBuckets = strBuckets.split(" ");
    buckets = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int bucket = Integer.parseInt(arrBuckets[i]);
      buckets.add(bucket);
    }
  }

  public void carryInABucket(int bucket) {
    buckets.add(bucket);
    milk += bucket;
  }

  public void cancelCarryingIn() {
    milk -= buckets.remove(buckets.size() -1);
  }

  public int carryOutNthBucket(int nth) {
    int nthBucket = buckets.remove(nth);
    milk -= nthBucket;

    return nthBucket;
  }

  public void cancelCarryingOut(int nth, int bucket) {
    buckets.add(nth, bucket);
    milk += bucket;
  }

  public int getMilk() {
    return milk;
  }

  public int getLeftBucket() {
    return buckets.size();
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