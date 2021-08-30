//// 문제풀이 실패 - 직접 품
//// 페프 때 로직 설명듣고 직접 풀었음 (+hsk 코드도 참고했음)
//// 단방향 트리 기본적인 문제같은데 이걸 못풀었네

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  static int maxTrustedAmount;
  static Computer[] computers;

  public static void main(String[] args) throws Exception {
    final int COMPUTER_AMOUNT = Input.nextInt();
    final int TRUST_AMOUNT = Input.nextInt();

    computers = Stream.generate(Computer::new).limit(COMPUTER_AMOUNT + 1).toArray(Computer[]::new);

    for (int i = 0; i < TRUST_AMOUNT; i++) {
      int trusting = Input.nextInt();
      int trusted = Input.nextInt();

      computers[trusting].number = trusting;
      computers[trusted].number = trusted;

      computers[trusted].trustedBy(computers[trusting]);
    }

    for (int i = 1; i <= COMPUTER_AMOUNT; i++) {
      fillTrustedAmount(computers[i]);
    }

    StringBuilder result = new StringBuilder();
    for (int i = 1; i <= COMPUTER_AMOUNT; i++) {
      if (computers[i].trustedAmount == maxTrustedAmount) {
        result.append(i).append(' ');
      }
    }

    System.out.print(result);
  }

  static void fillTrustedAmount(Computer computer) {
    Queue<Computer> queue = new LinkedList<>();
    queue.add(computer);
    
    boolean[] isHacked = new boolean[computers.length];
    isHacked[computer.number] = true;

    while (queue.size() > 0) {
      Computer current = queue.poll();
      isHacked[current.number] = true;

      for (Computer computerTrustsIt : current.computersTrustMe) {
        if (isHacked[computerTrustsIt.number]) continue;

        queue.add(computerTrustsIt);
        isHacked[computerTrustsIt.number] = true;
      }
    }

    int trustedAmount = 0;
    for (int i = 0; i < isHacked.length; i++){
      trustedAmount += isHacked[i] ? 1 : 0;
    }
    
    computer.trustedAmount = trustedAmount;
    maxTrustedAmount = Math.max(maxTrustedAmount, trustedAmount);
  }
}

class Computer {
  public List<Computer> computersTrustMe;
  public int number;
  public int trustedAmount;

  public Computer() {
    computersTrustMe = new ArrayList<>();
  }

  public void trustedBy(Computer computer) {
    computersTrustMe.add(computer);
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