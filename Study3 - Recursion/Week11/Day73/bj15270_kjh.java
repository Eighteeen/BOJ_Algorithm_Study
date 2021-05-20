//// 문제 실패 : 재귀 형태를 유지하면서 시간초과의 늪을 벗어날 방법이 생각나지 않음

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    final int MATES_AMOUNT = Input.nextInt();
    final int RELATIONS = Input.nextInt();

    Mates mates = new Mates(MATES_AMOUNT);
    
    List<Pair> pairs = new ArrayList<>();
    for (int i = 0; i < RELATIONS; i++) {
      int friendA = Input.nextInt();
      int friendB = Input.nextInt();
      pairs.add(new Pair(friendA, friendB));
    }

    int maxAvailable = calcMaxAvailableToBeStage(mates, pairs);
    System.out.print(maxAvailable);
  }

  static int calcMaxAvailableToBeStage(Mates mates, List<Pair> pairs) {
    if (pairs.isEmpty() || mates.getAmount() <= 1) {
      return mates.isAnyoneLeft() ? 1 : 0;
    }
    
    int maxAvailable = 0;
    for (int i = 0; i < pairs.size(); i++) {
      Pair pair = pairs.get(i);
      if (!mates.isAvailablePair(pair)) continue;
      int available = 2;
      
      pairs.remove(pair);
      mates.exludePair(pair);
      available += calcMaxAvailableToBeStage(mates, pairs);
      mates.includePair(pair);
      pairs.add(i, pair);

      maxAvailable = Math.max(available, maxAvailable);
    }

    return maxAvailable;
  }
}

class Mates {
  private boolean[] mates;

  public Mates(int matesAmount) {
    mates = new boolean[matesAmount];
    Arrays.fill(mates, true);
  }

  public boolean isAvailablePair(Pair pair) {
    return mates[pair.getFriendA() - 1]
      && mates[pair.getFriendB() - 1];
  }

  public void exludePair(Pair pair) {
    mates[pair.getFriendA() - 1] = false;
    mates[pair.getFriendB() - 1] = false;
  }

  public void includePair(Pair pair) {
    mates[pair.getFriendA() - 1] = true;
    mates[pair.getFriendB() - 1] = true;
  }

  public boolean isAnyoneLeft() {
    for (boolean isExist : mates) {
      if (isExist) return true;
    }

    return false;
  }

  public int getAmount() {
    int amount = 0;
    for (boolean isExist : mates) {
      if (isExist) amount++;
    }

    return amount;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    for (int i = 0; i < mates.length; i++) {
      if (!mates[i]) continue;
      sb.append(i + 1).append(", ");
    }
    sb.append("]");
    
    return sb.toString();
  }
}

class Pair {
  private int friendA;
  private int friendB;

  public Pair(int friendA, int friendB) {
    this.friendA = friendA;
    this.friendB = friendB;
  }

  public int getFriendA() {
    return friendA;
  }

  public int getFriendB() {
    return friendB;
  }

  @Override
  public String toString() {
    return "{" + friendA + ", " + friendB + "}";
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