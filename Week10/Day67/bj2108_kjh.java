import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();

    int[] numbers = new int[N];
    for (int i = 0; i < N; i++) {
      numbers[i] = Input.nextInt();
    }

    System.out.println(calcAverage(numbers));
    System.out.println(calcMedian(numbers));
    System.out.println(calcMode(numbers));
    System.out.print(calcRange(numbers));
  }

  static int calcAverage(int[] numbers) {
    int len = numbers.length;
    double sum = 0.0;

    for (int i = 0; i < len; i++) {
      sum += numbers[i];
    }
    
    return (int) Math.round(sum / len);
  }

  static int calcMedian(int[] numbers) {
    int[] sortedNumbers = Arrays.stream(numbers)
      .sorted()
      .toArray();
    
    int half = sortedNumbers.length / 2;
    boolean multiMedians = sortedNumbers.length % 2 == 0;
    if (multiMedians) {
      int mediansSum = sortedNumbers[half - 1] + sortedNumbers[half];
      return round(mediansSum / 2.0);
    }

    return sortedNumbers[half];
  }

  static int calcMode(int[] numbers) {
    int[] frequents = new int[8001];
    Arrays.fill(frequents, 0);

    int maxFrequent = 0;
    for (int number : numbers) {
      frequents[number + 4000]++;
      if (frequents[number + 4000] > maxFrequent) maxFrequent = frequents[number + 4000];
    }
    
    List<Integer> maxFrequentList = new ArrayList<>();
    for (int i = 0; i < 8001; i++) {
      if (frequents[i] == maxFrequent) maxFrequentList.add(i - 4000);
    }

    Collections.sort(maxFrequentList);

    int mode = (maxFrequentList.size() == 1) ? maxFrequentList.get(0) : maxFrequentList.get(1);
    return mode;
  }

  static int calcRange(int[] numbers) {
    int max = numbers[0];
    int min = numbers[0];

    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i] > max) max = numbers[i];
      if (numbers[i] < min) min = numbers[i];
    }

    return max - min;
  }

  static int round(double realNumber) {
    return (int) Math.round(realNumber);
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