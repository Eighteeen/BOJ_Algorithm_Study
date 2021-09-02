import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

// 머리 굴릴 필요없이 그냥 문제에서 주어진 그대로 구현하면 됐구나 싶었던 문제
//// 깔끔해요. 변수명이 이해가 잘됐어요 : 22 변수명이 직관적이라서 이해가 빠르게 됐네요.
class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_OF_PEOPLE = Input.nextInt();
    final int NUMBER_OF_PAIRS = Input.nextInt();

    //// 오 이렇게 한번에 초기화 가능하군요 배워갑니다.
    List<Integer>[] friendsFor = Stream.generate(ArrayList<Integer>::new).limit(NUMBER_OF_PEOPLE + 1).toArray(List[]::new);
    int totalNumberOfPairs = 0;
    final int MAX_TOTAL_NUMBER_OF_PAIRS = NUMBER_OF_PEOPLE * (NUMBER_OF_PEOPLE - 1) / 2;

    for (int i = 0; i < NUMBER_OF_PAIRS; i++) {
      int personA = Input.nextInt();
      int personB = Input.nextInt();

      friendsFor[personA].add(personB);
      friendsFor[personB].add(personA);
      totalNumberOfPairs++;
    }

    StringBuilder newPairsLog = new StringBuilder();
    int days = 0;

    //// 다중 반복문으로 쉽게 지저분해보일 수 있었는데 다중 반복문 임에도 깔끔하게 잘 짜신 것 같아요.
    //// => 감사합니다 ㅎㅎ;
    while (totalNumberOfPairs < MAX_TOTAL_NUMBER_OF_PAIRS) {
      Set<Pair> newPairsThisDay = new HashSet<>();

      for (int me = 1; me <= NUMBER_OF_PEOPLE; me++) {
        List<Integer> myFriends = friendsFor[me];
        for (Integer myFriend : myFriends) {
          List<Integer> friendsOfMyFriend = friendsFor[myFriend];
          for (Integer friendOfMyFriend : friendsOfMyFriend) {
            if (friendOfMyFriend == me) {
              continue;
            }
            newPairsThisDay.add(new Pair(friendOfMyFriend, me));
          }
        }
      }

      int previousTotalNumberOfPairs = totalNumberOfPairs;
      for (Pair pair : newPairsThisDay) {
        if (friendsFor[pair.personA].contains(pair.personB)) {
          continue;
        }
        friendsFor[pair.personA].add(pair.personB);
        friendsFor[pair.personB].add(pair.personA);
        totalNumberOfPairs++;
      }
      newPairsLog.append(totalNumberOfPairs - previousTotalNumberOfPairs).append('\n');
      days++;
    }

    System.out.println(days);
    System.out.print(newPairsLog);
  }
}

class Pair {
  public int personA;
  public int personB;

  public Pair(int personA, int personB) {
    this.personA = personA;
    this.personB = personB;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Pair == false) {
      return false;
    }

    Pair pair = (Pair) o;
    return (pair.personA == this.personA && pair.personB == this.personB) ||
      (pair.personA == this.personB && pair.personB == this.personA);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}